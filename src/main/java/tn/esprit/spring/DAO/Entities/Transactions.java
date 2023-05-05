package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Temporal(TemporalType.DATE )
    Date transactionDate;
    Long ribsource;
    Long ribrecipient ;
    Float amount ;
    @Enumerated(EnumType.STRING)
    Typetrans typetrans;
    @Enumerated(EnumType.STRING)
    Category category ;
    @JsonIgnore
    @ManyToMany

    List<Account> accountList;
    @JsonIgnore
    @ManyToOne


    Account account ;

}
