package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name= "ComplaintResponse")
public class ComplaintResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long idResponse;
    //User Information

    @ManyToOne
    User user ;
    //Description of Complaint: A detailed description of the complaint,
    //including the date and time of the issue, and any relevant account or transaction details.
    @Column(name = "description", length = 3000)
    String description;
    //Date of creation
    @Temporal(TemporalType.DATE)
    Date dateResponse ;
    @JsonIgnore
    @ManyToOne
    Complaint complaint;

}
