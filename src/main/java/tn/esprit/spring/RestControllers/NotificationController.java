package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.Services.Classes.UserService;
import tn.esprit.spring.Services.Classes.WSService;
import tn.esprit.spring.Services.Interfaces.INotificationService;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

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

    @GetMapping("/client/notifications/client/{cid}")//showing notifications of a selected user {without pending notifications}
    public List<Notification> getNotifications(@PathVariable long cid) {
        User connectedUser = userService.getById(cid);
        List<Notification> notifs = iNotificationService.getSentNotificationsByUserId(connectedUser.getId());
              notifs.sort(new Comparator<Notification>() {
            public int compare(Notification n1, Notification n2) {
                return n2.getSentDate().compareTo(n1.getSentDate());
            }
        });
return notifs;

    }


    @PostMapping("/admin/addInstantNotification/{id}")
    public List<Notification> addInstantNotification(
            @RequestBody Notification n,
            @PathVariable long id
    ) {
        n.setStatus(TypeNotificationStatus.UNREAD);
        n.setSentDate(LocalDateTime.now());
        User u = userService.getById(id);
        n.setUser(u);
        wsService.notifyUser(id + "", n.getSection() + " - " + n.getMessage());
        iNotificationService.add(n);
        return iNotificationService.getNotificationsByUserId(id);
    }


    @PostMapping("/admin/addScheduledNotification/{uid}")
    public List<Notification> addScheduledNotification(
            @RequestBody Notification n,
            @PathVariable long uid
    ) {
        n.setStatus(TypeNotificationStatus.PENDING);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        User u =userService.getById(uid);
        n.setUser(u);
        LocalDateTime now = LocalDateTime.now();
        long secondsToWait = now.until(n.getSentDate(), ChronoUnit.SECONDS);
        System.out.println(secondsToWait);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            wsService.notifyUser(uid + "", n.getSection() + " - " + n.getMessage());
            n.setStatus(TypeNotificationStatus.UNREAD);
            iNotificationService.edit(n);
        }, secondsToWait, TimeUnit.SECONDS);
         iNotificationService.add(n);
         return iNotificationService.getNotificationsByUserId(uid);
    }

    @PutMapping("/admin/notifications/edit") //editing notification by admin
    public List<Notification>  editNotification(@RequestBody Notification n ) {
        Notification not = iNotificationService.selectById(n.getIdNotification());
        not.setMessage(n.getMessage());
        not.setSection(n.getSection());

         iNotificationService.edit(not);
        return iNotificationService.getNotificationsByUserId(not.getUser().getId());

    }

    @PutMapping("/client/notifications/read/{id}") //editing notification by user {change status to Read }
    public void markNotificationAsRead(@PathVariable long id) {
        Notification n = iNotificationService.selectById(id);
        iNotificationService.markAsRead(id);
    }
    @DeleteMapping("/admin/notifications/n/{id}/delete") // deleting notification by admin
    public List<Notification> deleteNotification(@PathVariable("id") long id) {
        long uid = iNotificationService.selectById(id).getUser().getId();
        iNotificationService.delete(id);


        return iNotificationService.getNotificationsByUserId(uid);
    }


}
