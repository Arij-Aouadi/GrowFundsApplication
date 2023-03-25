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
import java.util.Set;

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
    String username ;
     String cin;
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
     @NotNull
     @Size(min = 8,max = 50)
     String password ;


     @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
     Set<Role> role;
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
    @ManyToOne
    User user ;
    @OneToMany(mappedBy = "user")
    List<User> listUsers ;


    public User(String username, String email, String encode) {

    }
}
