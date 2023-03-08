package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("SELECT  n FROM Notification n  WHERE n.user.id=:id or n.toAll=true and n.sentDate>n.user.ceratedDate")
    List<Notification> getNotificationByUser(@Param("id") long id);
    @Query("SELECT  n FROM Notification n  WHERE n.user.id=:id or n.toAll=true and n.sentDate>n.user.ceratedDate and n.status != 'PENDING'")
    List<Notification> getSentNotificationByUser(@Param("id") long id);
}
