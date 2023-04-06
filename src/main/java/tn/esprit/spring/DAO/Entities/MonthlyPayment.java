package tn.esprit.spring.DAO.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "MonthlyPayment")
public class MonthlyPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPayment;

    @Temporal(TemporalType.DATE )
    Date paymentSupposedDate;
    @Temporal(TemporalType.DATE )
    Date paymentDate;
    @JsonIgnore
    @ManyToOne
    Credits credit;
}
