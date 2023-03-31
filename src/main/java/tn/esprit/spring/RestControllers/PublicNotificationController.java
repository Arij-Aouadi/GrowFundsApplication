package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.Services.Classes.UserService;
import tn.esprit.spring.Services.Classes.WSService;
import tn.esprit.spring.Services.Interfaces.IPublicNotificationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class PublicNotificationController {
    UserService userService;
    @Autowired
    private IPublicNotificationService publicNotificationService;
    private WSService wsService;

    @GetMapping("/admin/publicnotif")
    public List<PublicNotification> getAllPublicNotifications (){
        return publicNotificationService.getAll();
    }

    @GetMapping("/publicnotif")
    public List<PublicNotification> getPublicNotifications (){
        User connectedUser= userService.getConnectedUser();
        return publicNotificationService.getSentPublicNotificationsByUserId(connectedUser.getId());
    }

    @PostMapping("/admin/addInstantPublicNotifi")
    public PublicNotification addInstantNotification(@RequestParam String message ) {
        PublicNotification n = new PublicNotification();
        n.setMessage(message);
        n.setSentDate(new Date());
        wsService.notifyFrontend(message);
        return publicNotificationService.add(n);
    }
    @PostMapping("/admin/addScheduledPublicNotif")
    public PublicNotification addInstantPublicNotification(@RequestParam String message,@RequestParam String date ) {
        PublicNotification n = new PublicNotification();
        n.setMessage(message);
        n.setSentDate(new Date());
        wsService.notifyFrontend(message);
        return publicNotificationService.add(n);
    }

    @PutMapping("/admin/publicnotif/n/{id}/edit")
    public PublicNotification editNotification(@PathVariable long id,@RequestParam String message,@RequestParam String date){
        PublicNotification n = publicNotificationService.getById(id);
        n.setMessage(message);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        n.setSentDate(newDate);
        return publicNotificationService.edit(n);}

    @DeleteMapping ("/admin/publicnotif/n/{id}/delete")
    public void deletePublicNotification (@RequestParam long id){
        publicNotificationService.delete(id);
    }
}
