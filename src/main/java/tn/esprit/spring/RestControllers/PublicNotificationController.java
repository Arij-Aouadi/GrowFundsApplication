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
import java.time.LocalDateTime;
import java.time.ZoneId;
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

public class PublicNotificationController {
    UserService userService;
    @Autowired
    private IPublicNotificationService publicNotificationService;
    private WSService wsService;

    @GetMapping("/admin/publicnotif")
    public List<PublicNotification> getAllPublicNotifications (){
        return publicNotificationService.getAll();
    }

    @GetMapping("/client/publicnotif")
    public List<PublicNotification> getPublicNotifications (){
        User connectedUser= userService.getConnectedUser();

        List<PublicNotification> notifs = publicNotificationService.getSentPublicNotificationsByUserId(connectedUser.getId());
        notifs.sort(new Comparator<PublicNotification>() {
            public int compare(PublicNotification n1, PublicNotification n2) {
                return n2.getSentDate().compareTo(n1.getSentDate());
            }
        });
        return notifs;

    }

    @PostMapping("/admin/addInstantPublicNotifi")
    public List<PublicNotification> addInstantNotification(@RequestBody PublicNotification n ) {
        n.setSentDate(LocalDateTime.now());
        wsService.notifyFrontend(n.getMessage());
         publicNotificationService.add(n);
        return publicNotificationService.getAll();
    }
    @PostMapping("/admin/addScheduledPublicNotif")
    public List<PublicNotification> addInstantPublicNotification(@RequestBody PublicNotification n  ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        long secondsToWait = now.until(n.getSentDate(), ChronoUnit.SECONDS);
        System.out.println(secondsToWait);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> wsService.notifyFrontend(n.getMessage()), secondsToWait, TimeUnit.SECONDS);
         publicNotificationService.add(n);
         return publicNotificationService.getAll();
    }



    @PutMapping("/admin/publicnotif/n/{id}/edit")
    public PublicNotification editNotification(@PathVariable long id,@RequestParam String message,@RequestParam String date){
        PublicNotification n = publicNotificationService.getById(id);
        n.setMessage(message);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime newDate = null;
        newDate = LocalDateTime.parse(date, formatter);
        newDate= newDate.plusHours(1);
        n.setSentDate(newDate);
        return publicNotificationService.edit(n);}

    @DeleteMapping ("/admin/publicnotif/n/{id}/delete")
    public List<PublicNotification> deletePublicNotification (@PathVariable long id){

        publicNotificationService.delete(id);

            return publicNotificationService.getAll();

    }
}
