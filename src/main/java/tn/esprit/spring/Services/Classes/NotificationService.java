package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.DAO.Repositories.NotificationRepository;
import tn.esprit.spring.Services.Interfaces.INotificationService;

import java.util.List;
@AllArgsConstructor
@Service
public class NotificationService implements INotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @Override
    public Notification add(Notification n) {
        return notificationRepository.save(n);
    }

    @Override
    public Notification edit(Notification n) {
        return notificationRepository.save(n);
    }

    @Override
    public Notification markAsRead(long id) {
        Notification n = notificationRepository.findById(id).get();
        n.setStatus(TypeNotificationStatus.READ);
        return notificationRepository.save(n);
    }

    @Override
    public List<Notification> getAll() {
        return (List<Notification>)notificationRepository.findAll();
    }

    @Override
    public Notification selectById(Long idNotification) {
        return notificationRepository.findById(idNotification).get();
    }

    @Override
    public void delete(long id) {
        Notification n = notificationRepository.findById(id).get();
        notificationRepository.delete(n);

    }

    @Override
    public List<Notification> getNotificationsByUserId(long idUser) {
        return (List<Notification>)notificationRepository.getNotificationByUser(idUser);
    }

    @Override
    public List<Notification> getSentNotificationsByUserId(long idUser) {
        return (List<Notification>)notificationRepository.getSentNotificationByUser(idUser);
    }

    @Override
    public void sendPrivateNotification(final String userId) {
        WSResponseMessage message = new WSResponseMessage("Private Notification");

        messagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications", message);
    }
}
