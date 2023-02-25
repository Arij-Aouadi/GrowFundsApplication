package tn.esprit.spring.DAO.Entities;

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
@Table(name= "Credits")

public class Credits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idCredit;
    Float amount;
    @Temporal(TemporalType.DATE )
    Date dateDebut;
    @Temporal(TemporalType.DATE )
    Date dateFin;
    Float paymentMounthly ;
    Float interestRate ;
    String status ;
    String typeCredit ;
    @ManyToOne
    Account account ;
    @ManyToMany
    List<Packs> packsList;


}
