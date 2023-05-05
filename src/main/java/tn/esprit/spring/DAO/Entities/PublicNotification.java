package tn.esprit.spring.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sentDate; //the date the notification was sent

}

