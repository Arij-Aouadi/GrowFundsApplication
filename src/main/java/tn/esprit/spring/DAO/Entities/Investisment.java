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

public class Investisment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idinvesttisment ;

    int TauxInvesttisment ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ;
    Float amount ;
    @ManyToOne
    User investor ;

    @Enumerated(EnumType.STRING)
    TypeInvestor leTypeInvestor;

}
