package tn.esprit.spring.DAO.Entities;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.Size;
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
        long id;

    String cin;

    String userName ;

    @Temporal(TemporalType.DATE )
    Date birthDate;
    @Temporal(TemporalType.DATE )
    Date ceratedDate;
     int phoneNum ;
     String email ;
     String adresse ;
     Float salaire ;
     Float investmentAmount ;
     String relationWithClient ;
     String Profession ;
     @NotNull
     @Size(min = 8,max = 50)
     String password ;


     @ManyToMany
      List<Role> listRole;
     @OneToMany(mappedBy = "user")
     List<Account> accountList ;
     @OneToMany(mappedBy = "user")
     List<Projects> projectsList;
     @OneToMany(mappedBy = "user")
    List<Investtisment> investtismentList;
    @OneToMany(mappedBy = "user")
    List<Complaint> complaintList ;
    @ManyToOne
    User user ;
    @OneToMany(mappedBy = "user")
    List<User> listUsers ;


}
