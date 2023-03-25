package tn.esprit.spring.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.swing.*;
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
@Table(name= "User")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int cin;
    String firstName ;
    String secondName ;
    @Temporal(TemporalType.DATE )
    Date birthDate;
     int phoneNum ;
     String email ;
     String address ;
     Float salary ;
     Float investmentAmount ;
     String relationWithClient ;
     String Profession ;
     int stabilityYears ;
     int numberPastCredit;
     @ManyToOne
    Role role;
     @OneToMany(mappedBy = "user")
     List<Account> accountList ;
     @OneToMany(mappedBy = "user")
     List<Projects> projectsList;
     @OneToMany(mappedBy = "user")
    List<Investtisment> investtismentList;
    @OneToMany(mappedBy = "user")
    List<Complaint> complaintList ;
    @OneToOne(mappedBy = "guarant")
    Credits creditGuarant;



}
