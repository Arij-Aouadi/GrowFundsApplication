package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Notification;
import tn.esprit.spring.DAO.Entities.TypeNotificationSection;
import tn.esprit.spring.DAO.Entities.TypeNotificationStatus;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Classes.UserService;
import tn.esprit.spring.Services.Classes.WSService;
import tn.esprit.spring.Services.Interfaces.INotificationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class NotificationController {
    @Autowired
    private INotificationService iNotificationService;
    private WSService wsService;
    @Autowired
    UserService userService;


    @GetMapping("/admin/notifications") //showing all notifications for admin
    public List<Notification> getAllNotifications() {
        return iNotificationService.getAll();
    }


    @GetMapping("/admin/notifications/u/{id}")
    //showing notifications of a selected user for admin {it includes pending notifications}
    public List<Notification> getUserNotifications(@PathVariable int id) {
        return iNotificationService.getNotificationsByUserId(id);
    }

    @GetMapping("/notifications/")//showing notifications of a selected user {without pending notifications}
    public List<Notification> getNotifications() {
        User connectedUser = userService.getConnectedUser();
        return iNotificationService.getSentNotificationsByUserId(connectedUser.getId());
    }


    @PostMapping("/admin/addInstantNotification")
    public Notification addInstantNotification(
            @RequestParam String message,
            @RequestParam String section,
            @RequestParam long userId
    ) {
        Notification n = new Notification();
        n.setMessage(message);
        n.setSection(TypeNotificationSection.valueOf(section));
        n.setStatus(TypeNotificationStatus.UNREAD);
        n.setSentDate(LocalDateTime.now().plusHours(1));
        User u =userService.getById(userId);
        n.setUser(u);
        wsService.notifyUser(userId + "", section + " - " + message);
        return iNotificationService.add(n);
    }

    @PostMapping("/admin/addScheduledNotification")
    public Notification addScheduledNotification(
            @RequestParam String message,
            @RequestParam String section,
            @RequestParam String date,
            @RequestParam long userId
    ) {
        Notification n = new Notification();
        n.setMessage(message);
        n.setSection(TypeNotificationSection.valueOf(section));
        n.setStatus(TypeNotificationStatus.PENDING);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime newDate = null;
        newDate = LocalDateTime.parse(date, formatter);
        User u =userService.getById(userId);
        n.setUser(u);
        //Sending
        LocalDateTime now = LocalDateTime.now();
        long secondsToWait = now.until(newDate, ChronoUnit.SECONDS);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            wsService.notifyUser(userId + "", section + " - " + message);
            n.setStatus(TypeNotificationStatus.UNREAD);
            iNotificationService.edit(n);
        }, secondsToWait, TimeUnit.SECONDS);


        newDate= newDate.plusHours(1);
        n.setSentDate(newDate);

        return iNotificationService.add(n);
    }

    @PutMapping("/admin/notifications/n/{id}/edit") //editing notification by admin
    public Notification editNotification(@PathVariable long id, @RequestParam String message, @RequestParam String section, @RequestParam String date) {
        Notification n = iNotificationService.selectById(id);
        n.setMessage(message);
        n.setSection(TypeNotificationSection.valueOf(section));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime newDate = null;
        newDate = LocalDateTime.parse(date, formatter);
        newDate= newDate.plusHours(1);
        n.setSentDate(newDate);
        return iNotificationService.edit(n);
    }

    @PutMapping("/notifications/read/{id}") //editing notification by user {change status to Read }
    public void markNotificationAsRead(@PathVariable long id) {
        User connectedUser = userService.getConnectedUser();
        Notification n = iNotificationService.selectById(id);
        if(n.getUser()==connectedUser)iNotificationService.markAsRead(id);
    }

    @DeleteMapping("/admin/ notifications/n/{id}/delete") // deleting notification by admin
    public void deleteNotification(@RequestParam long id) {
        iNotificationService.delete(id);
    }


}
