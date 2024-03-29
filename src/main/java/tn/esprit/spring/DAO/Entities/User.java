package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.security.services.UserDetailsImpl;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
    @Temporal(TemporalType.DATE )
    Date createdDate;

     int phoneNum ;
     String email ;
     String address ;
     Float salary ;
     Float investmentAmount ;
     String relationWithClient ;
     String Profession ;
     String NewQuestions;
     String typeProjets;
     String TheuserNumber;



    @JsonIgnore
    @ManyToMany
    private List<Packs> likedPackages;

     @NotNull
     @Size(min = 8,max = 50)
     String password ;


     @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
     private Set<Role> role = new HashSet<>();


     @JsonIgnore
     @OneToMany(mappedBy = "user")
     List<Account> accountList ;

    @JsonIgnore
     @OneToMany(mappedBy = "investor")
    List<Investisment> investtismentList;
     @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Complaint> complaintList ;
     @JsonIgnore
    @OneToOne(mappedBy = "guarant")
    Credits creditGuarant;

    @JsonIgnore
    @OneToMany(mappedBy = "founder")
    List<Project> projectsListI;
    public String getMail() {
        return email;
    }


    public User(String username, String email, String encode) {

    }
}
