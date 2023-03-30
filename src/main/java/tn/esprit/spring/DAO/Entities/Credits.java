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
    Float interestRate ;
    String status ;
    String typeCredit ;
    String Judgment;

    /*   Attribute 1:  Status of Existing Checking Account
         1 :      ... <    0 Dt
         2 : 0 <= ... <  200 Dt
     	 3 :      ... >= 200 Dt /salary assignments for at least 1 year
         4 : no checking account */
    int  checkingAccount;

    //  Attribute 2: Duration in months
    int Duration;

    /*   Attribute 3: Credit history
	     0 : no credits taken/all credits paid back duly
         1 : all credits at this bank paid back duly
	     2 : existing credits paid back duly till now
         3 : delay in paying off in the past
	     4 : critical account/other credits existing (not at this bank) */
    int creditHistory;

    /*   Attribute 4: Purpose
	     0 : car (new)
	     1 : car (used)
	     2 : furniture/equipment
	     3 : radio/television
	     4 : domestic appliances
	     5 : repairs
	     6 : education
	     7 : vacation
	     8 : retraining
	     9 : business
	     10 : others */
    int purpose;

    //   Attribute 5: Credit amount
    long creditAmount;

    /*   Attribute 6: Savings account/bonds
	     1 :          ... <  100 Dt
	     2 :   100 <= ... <  500 Dt
	     3 :   500 <= ... < 1000 Dt
	     4 :          .. >= 1000 Dt
         5 :   unknown/ no savings account */

    int bondsStatus;

    /*   Attribute 7: Present employment since
	     1 : unemployed
	     2 :       ... < 1 year
	     3 : 1  <= ... < 4 years
	     4 : 4  <= ... < 7 years
	     5 :       .. >= 7 years */
    int employmentYears;

    //   Attribute 8: Installment rate in percentage of disposable income
    int installmentRate;

    /*   Attribute 9: Personal status and sex
         1 : male   : divorced/separated
	     2 : female : divorced/separated/married
         3 : male   : single
	     4 : male   : married/widowed
	     5 : female : single */
    int statusAndSex;

    /*   Attribute 10: Other debtors/ guarantors
	     1 : none
	     2 : co-applicant
	     3 : guarantor */
    int gurantOrCoapplicant;

    /*   Attribute 11: Present residence since*/
    int residenceSince;

    /*   Attribute 12:  Property
	     1 : real estate
	     2 : if not 1 : building society savings agreement/life insurance
         3 : if not 2 : car or other, not in attribute 6
	     4 : unknown / no property */
    int property;

    /*   Attribute 13: Age in years */

    int age;

    /*   Attribute 14: Other installment plans
	     1 : bank
	     2 : stores
	     3 : none  */
    int otherPlans;

    /*   Attribute 15: Housing
	     1 : rent
	     2 : own
	     3 : for free */

    int housing;

    /*   Attribute 16: Number of existing credits at this bank */

    int numOfExistingCredits;

    /*   Attribute 17: Job
	     1 : unemployed/ unskilled  - non-resident
	     2 : unskilled - resident
	     3 : skilled employee / official
	     4 : management/ self-employed/ highly qualified employee/ officer */
    int job;
    @ManyToOne
    Account account ;
    @ManyToMany
    List<Packs> packsList;
    @OneToMany(mappedBy = "credit")
    List<MonthlyPayment> monthlyPaymentList;
    @OneToOne
    User guarant;

}
