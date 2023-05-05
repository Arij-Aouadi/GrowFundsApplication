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
@Table(name= "Account")


public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int accountNum;
    int cin;
    private Boolean state;
    @Enumerated(EnumType.STRING)
     TypeAccount typeAcc ;
    Float solde;
    Long rib;
    @Temporal(TemporalType.DATE )
    Date date;

    @JsonIgnore

    @ManyToOne
    User user ;

    @OneToMany(mappedBy = "account")

    @JsonIgnore
    List<Credits> creditsList ;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<PaymentMethod> paymentMethodList;
    @ManyToMany(mappedBy = "accountList")

    @JsonIgnore
    List<Transactions> virement;

    @OneToMany(mappedBy = "account")

    @JsonIgnore
    List<Transactions> Versement ;



}
