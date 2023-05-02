package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idProject ;
    String name;
    String category                                           ;
     Float budget ;
      TypeProjectStatus status;
     @ManyToOne
     User founder ;
     @OneToMany
     List<Investisment> investisments;
     @OneToMany
    List<Revenue> revenues;
}
