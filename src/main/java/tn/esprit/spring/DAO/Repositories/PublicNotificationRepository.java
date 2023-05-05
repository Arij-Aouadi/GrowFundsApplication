package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Notification;
import tn.esprit.spring.DAO.Entities.PublicNotification;

import java.util.List;

public interface PublicNotificationRepository  extends JpaRepository<PublicNotification,Long> {
    @Query("SELECT  n FROM PublicNotification n ,User  u WHERE n.sentDate>=u.createdDate and u.id=:id")
    List<PublicNotification> getPublicNotificationForUser(@Param("id") long id);
}
