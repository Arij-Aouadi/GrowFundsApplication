package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Notification;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Interfaces.INotificationService;

import java.util.List;

@RestController
@AllArgsConstructor
public class NotificationController {
    private INotificationService iNotificationService;

    @GetMapping("/allNotifcations") //showing all notifications for admin
    public List<Notification> getAllNotifications (){
        return iNotificationService.getAll();
    }

    @GetMapping("/allNotifcations/{id}") //showing notifications of a selected user for admin {it includes pending notifications}
    public List<Notification> getAllNotifications (@PathVariable int id){
        return iNotificationService.getNotificationsByUserId(id);
    }

    @PostMapping("/addNotificationByAdmin") //adding notification by system or by admin
    public Notification addNotification(@RequestBody Notification notification) {
        return iNotificationService.add(notification);
    }
    @PutMapping ("/editNotification") //editing notification by admin
    public Notification editNotificationByAdmin(@RequestBody Notification notification){
        return iNotificationService.edit(notification);}
    @DeleteMapping ("/deleteNotification") // deleting notification by admin
    public void deleteNotification (@RequestBody Notification notification){
        iNotificationService.delete(notification);
    }

    @GetMapping("/notifications/")//showing notifications of a selected user {without pending notifications}
    public List<Notification> getMyNotifications (@RequestBody User user){
        return iNotificationService.getSentNotificationsByUserId(user.getCin());
    }

    @PutMapping ("/readNotification") //editing notification by user {change status to Read }
    public Notification editNotification(@RequestBody Notification notification){
        return iNotificationService.markAsRead(notification);}

}
