package tn.esprit.spring.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "Complaint")

public class Complaint implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idComplaint ;
    String type;
    String description;
    @Temporal(TemporalType.DATE)
    Date dateComplaint ;
    @ManyToOne
    User user ;


}
