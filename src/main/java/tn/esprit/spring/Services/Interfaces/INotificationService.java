package tn.esprit.spring.Services.Interfaces;


import tn.esprit.spring.DAO.Entities.Notification;

import java.util.List;

public interface INotificationService {
    Notification add(Notification n);
    Notification edit(Notification n);
    Notification markAsRead(long id);
    List<Notification> getAll();
    Notification selectById(Long idNotification);
    void delete(long id);
    List<Notification> getNotificationsByUserId(long idUser);
    List<Notification> getSentNotificationsByUserId(long idUser);
    public void sendPrivateNotification(final String userId);

}
