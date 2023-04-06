package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "Projects")

public class Projects implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idProject ;
    String name;
    String type;
     Float budget ;
     @JsonIgnore
     @ManyToOne
     User user ;
    @JsonIgnore
    @ManyToOne
    User investor;

     @ManyToOne
    Investtisment investtisment;

    @Enumerated(EnumType.STRING)
    leTypeInvestor leTypeInvestor;



}
