package tn.esprit.spring.DAO.Entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Monthly Payment")
public class MonthlyPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long numPayment;

    Date paymentDate;
    @ManyToOne
    Credits credit;
}
