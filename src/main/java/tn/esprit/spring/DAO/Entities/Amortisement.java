package tn.esprit.spring.DAO.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Amortisement implements Serializable {

    double amount;
    float intrest;

    float monthlyPayment;

    float amortiValue;


}
