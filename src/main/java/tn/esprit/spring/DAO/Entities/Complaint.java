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
@Table(name= "Complaint")

public class Complaint implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long idComplaint ;
    //User Information
    @ManyToOne
    User user ;

    //Priority Level: Assign a priority level to the complaint based on its severity and impact on the customer.
    TypePriorityLevel priorityLevel;
    //These four status values cover the entire lifecycle of a complaint from the time it is received to the time it is fully resolved and closed.
    //---1---NEW: This status is assigned to a complaint when it is first received by the complaint system.
    //           It indicates that the complaint has not yet been reviewed or addressed by a support agent or customer service representative.
    //---2---IN_PROGRESS: This status is assigned to a complaint when a support agent or customer service representative has started working on it.
    //          It indicates that the complaint is being actively investigated or addressed.
    //---3---RESOLVED: This status is assigned to a complaint when the issue raised in the complaint has been addressed and resolved. It indicates that the customer's concern has been resolved to their satisfaction.
    //---4---CLOSED: This status is assigned to a complaint when it has been fully resolved and closed.
    // It indicates that the complaint is no longer active and requires no further action.
    TypeComplaintStatus status;

    //Type of Complaint: Categorize the type of complaint received, such as account-related issues,
    // transaction disputes, billing discrepancies, fraud, or service-related complaints
    String objet;

    //Description of Complaint: A detailed description of the complaint,
    // including the date and time of the issue, and any relevant account or transaction details.
    @Column(name = "description", length = 5000)
    String description;
    //Date of creation
    @Temporal(TemporalType.DATE)
    Date dateComplaint ;

    @OneToMany(mappedBy = "complaint")
    List<ComplaintResponse> responses;




}
