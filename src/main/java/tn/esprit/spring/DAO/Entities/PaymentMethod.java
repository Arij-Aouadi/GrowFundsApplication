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
@Table(name= "PaymentMethod")

public class PaymentMethod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idPaymentMethod ;
    @Enumerated(EnumType.STRING)
    TypePayment typePayment ;
    String typeCarte ;
    String typeCheque;
    @ManyToOne
    Account account;
}
