package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Notification;
import tn.esprit.spring.DAO.Entities.TypeNotificationSection;
import tn.esprit.spring.DAO.Entities.TypeNotificationStatus;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Classes.WSService;
import tn.esprit.spring.Services.Interfaces.INotificationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class NotificationController {
    private INotificationService iNotificationService;
    private WSService wsService;

    @GetMapping("/allNotifcations") //showing all notifications for admin
    public List<Notification> getAllNotifications (){
        return iNotificationService.getAll();
    }

    @GetMapping("/allNotifcations/{id}") //showing notifications of a selected user for admin {it includes pending notifications}
    public List<Notification> getAllNotifications (@PathVariable int id){
        return iNotificationService.getNotificationsByUserId(id);
    }

    @PostMapping("/addInstantNotification") //adding notification by system or by admin
    public Notification addInstantNotification(
            @RequestParam String message,
            @RequestParam String section,
            @RequestParam String d,
            @RequestParam long userId,
            @RequestParam boolean toAll
    ) {
        Notification n = new Notification();

        n.setMessage(message);
        n.setSection(TypeNotificationSection.valueOf(section));
        n.setStatus(TypeNotificationStatus.UNREAD);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        n.setSentDate(date);
        if(toAll){
            n.setToAll(true);
            wsService.notifyFrontend(section+" - "+message);
        }else{
            n.setToAll(false);
            User u=new User();
            u.setId(userId);
            n.setUser(u);
            wsService.notifyUser(userId+"",section+" - "+message);
        }
        return iNotificationService.add(n);
    }
    @PutMapping ("/editNotification") //editing notification by admin
    public Notification editNotificationByAdmin(@RequestParam long id,@RequestParam String message, @RequestParam String section, @RequestParam String d){
        Notification n = iNotificationService.selectById(id);
        n.setMessage(message);
        n.setSection(TypeNotificationSection.valueOf(section));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        n.setSentDate(date);
        return iNotificationService.edit(n);}
    @DeleteMapping ("/deleteNotification") // deleting notification by admin
    public void deleteNotification (@RequestParam long id){
        iNotificationService.delete(id);
    }

    @GetMapping("/notifications/")//showing notifications of a selected user {without pending notifications}
    public List<Notification> getMyNotifications (){
        //chnage 1 with current user
        return iNotificationService.getSentNotificationsByUserId(1);
    }
    @PutMapping ("/readNotification") //editing notification by user {change status to Read }
    public Notification editNotification(@RequestParam long id){
        return iNotificationService.markAsRead(id);}

}
