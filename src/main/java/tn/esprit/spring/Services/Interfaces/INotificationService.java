package tn.esprit.spring.Services.Interfaces;


import tn.esprit.spring.DAO.Entities.Notification;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface INotificationService {
    Notification add(Notification n);
    Notification edit(Notification n);
    Notification markAsRead(Notification n);
    List<Notification> getAll();
    Notification selectById(Long idNotification);
    void delete(Notification n);
    List<Notification> getNotificationsByUserId(int idUser);
    List<Notification> getSentNotificationsByUserId(int idUser);

}
