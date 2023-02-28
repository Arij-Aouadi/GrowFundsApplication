package tn.esprit.spring.DAO.Entities;

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
@Table(name= "investtisment")

public class Investtisment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idinvesttisment ;

    String name;
    String secondName;
    String Profession ;
    Float amount ;
    @ManyToOne
    User user ;


}
