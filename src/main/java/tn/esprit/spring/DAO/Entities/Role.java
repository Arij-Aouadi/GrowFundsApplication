package tn.esprit.spring.DAO.Entities;

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
@Table(name= "Role")

public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idRole ;
    @Enumerated(EnumType.STRING)
    TypeRole typeRole ;



}
