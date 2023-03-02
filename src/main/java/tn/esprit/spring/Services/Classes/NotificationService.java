package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Notification;
import tn.esprit.spring.DAO.Entities.TypeNotificationStatus;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.NotificationRepository;
import tn.esprit.spring.Services.Interfaces.INotificationService;

import java.util.List;

@AllArgsConstructor //injection par constructeur
@Service
public class NotificationService  implements INotificationService {
    private NotificationRepository notificationRepository;
    @Override
    public Notification add(Notification n) {
        return notificationRepository.save(n);
    }

    @Override
    public Notification edit(Notification n) {
        return notificationRepository.save(n);
    }

    @Override
    public Notification markAsRead(Notification n) {
        n.setStatus(TypeNotificationStatus.Read);
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
    public void delete(Notification n) {
        notificationRepository.delete(n);

    }


    @Override
    public List<Notification> getNotificationsByUserId(int idUser) {
        User u = new User(); // this will be replaced when user repository is ready
        return (List<Notification>)notificationRepository.findByUser(u);
    }

    @Override
    public List<Notification> getSentNotificationsByUserId(int idUser) {
        User u = new User(); // this will be replaced when user repository is ready
        return (List<Notification>)notificationRepository.findSentByUser(u);
    }
}
