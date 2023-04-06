package tn.esprit.spring.DAO.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long idNotification; //a unique identifier for each notification
    @ManyToOne
    User user;//the user for whom the notification is intended

    String message; //the content of the notification that will be displayed to the user

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sentDate; //the date the notification was sent
    @Enumerated(EnumType.STRING)
    TypeNotificationStatus status; // the status of the notification (read, unread, pending)
    @Enumerated(EnumType.STRING)
    TypeNotificationSection section; // the section of the application to which the notification is linked (credit, transaction, user profile, etc.)

}
