package tn.esprit.spring.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "PublicNotification")
public class PublicNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long idNotification; //a unique identifier for each notification

    String message; //the content of the notification that will be displayed to the user

    @Temporal(TemporalType.DATE )
    Date sentDate; //the date the notification was sent

}

