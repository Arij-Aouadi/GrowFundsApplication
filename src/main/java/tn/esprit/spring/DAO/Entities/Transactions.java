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
@Table(name= "Transactions")

public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idTrans;
    @Enumerated(EnumType.STRING)
    TypeTrans typeTrans;
    @Temporal(TemporalType.DATE )
    Date birthDate;
    Long ribsource;
    Long ribrecipient ;
    Float amount ;
    String status ;
    @ManyToMany
    List<Account> accountList;
    @ManyToOne
    Account account ;

}
