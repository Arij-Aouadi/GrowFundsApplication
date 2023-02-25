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
@Table(name= "Account")


public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int accountNum;
    int cin;
    @Enumerated(EnumType.STRING)
     TypeAccount typeAcc ;
    Float solde;
    Long rib;
    @Temporal(TemporalType.DATE )
    Date date;
    @ManyToOne
    User user ;
    @OneToMany(mappedBy = "account")
    List<Credits> creditsList ;
    @OneToMany(mappedBy = "account")
    List<PaymentMethod> paymentMethodList;
    @ManyToMany(mappedBy = "accountList")
    List<Transactions> virement;
    @OneToMany(mappedBy = "account")
    List<Transactions> Versement ;


}
