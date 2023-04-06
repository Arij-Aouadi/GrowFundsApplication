package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.DAO.Repositories.TransactionRepository;
import tn.esprit.spring.Services.Interfaces.*;

import java.util.*;
import java.util.Collections;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@AllArgsConstructor
@Service
public class AccountandBankStatistics implements IAccountandBankStatistics {
    public ITransactionService iTransactionService;
    public ICreditService iCreditService ;
    public IInvesttismentServices iInvesttismentServices ;
    public TransactionRepository transactionRepository ;
    public IAccountService iAccountService;
//------------------------------ACCOUNT_STATICS---------------
    @Override
    public double calculPlacement(float cii, float tia, int duree) {
        float c = (tia / 100);
        return cii * Math.pow(1 + c / 12, duree * 12);

    }

    @Override
    public List<Float> monthlyspendingsscategorized(int rib, int month) {
        ArrayList<Float> liste = new ArrayList<Float>();

        float course=0;
        float fuel=0;
        float transport=0;
        float credit_loans =0;
        float  entertaiment=0;
        float education=0;
        float uncategorized=0;

        for (Transactions tran : iTransactionService.selectAll()){
            //+1 car la methode getMonth revoie les mois a partir du 0 exemple 12-05-2023
            //renvoie 4
            int month1 =tran.getTransactionDate().getMonth()+1;
            if ((tran.getRibsource()==rib)&& (month1==month)) {
                String type = String.valueOf(tran.getCategory());
                if (type.equals("COURSES")){
                     course= course + tran.getAmount();

                } else if (type.equals("FUEL")) {
                    fuel= fuel + tran.getAmount();

                } else if (type.equals("TRANSPORT")) {
                    transport = transport + tran.getAmount();
                } else if (type.equals("ENTERTAIMENT")) {
                    entertaiment = entertaiment + tran.getAmount();
                } else if (type.equals("EDUCATION")) {
                    education = education + tran.getAmount();
                } else if (type.equals("CREDIT_LOANS")) {
                    credit_loans =credit_loans + tran.getAmount();
                } else if (type.equals("UNCATEGORIZED")) {
                    uncategorized = uncategorized + tran.getAmount();

                }

            }

            }
          liste.addAll(Arrays.asList(course,fuel,transport,entertaiment,education,credit_loans,uncategorized));
        return liste ;

            }
    //------------------------------BANK_STATICS---------------

//------------ROA--------
    @Override
    public double netincome() {
        float benefice=0;
        float beneficeinves =0 ;

        //float fraidepassement=0;
        //Somme totale à rembourser = Capital + Intérêts
        //Intérêts = Capital x Taux d'intérêt x Durée du crédit
//        for (Credits credits : iCreditService.selectAll()){
//            LocalDate start =credits.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate fin =credits.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            Period period = Period.between(start,fin);
//
//             benefice = (credits.getAmount() * (credits.getInterestRate()/100) * period.toTotalMonths());
//        }
        for (Credits credits : iCreditService.selectAll()){
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(credits.getDateDebut());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(credits.getDateFin());
            int yearDiff = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12;
            int monthDiff = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
           int period= yearDiff + monthDiff;
            benefice = (credits.getAmount() * (credits.getInterestRate()/100) * period);

        }
        for (Investtisment inves : iInvesttismentServices.selectAll()){

             beneficeinves = (float) (beneficeinves + inves.getAmount()* 0.3);
        }
        float fraisTrans = 2000 * transactionRepository.nombredetrans();
        float fraiscompte = 0;
        for (Account acc : iAccountService.selectAll()){
            String type = String.valueOf(acc.getTypeAcc());

            if (type.equals("SAVINGACCOUNT")){
                fraiscompte = fraiscompte + 80000;
            }
            //fraisdepassement=((acc.getSolde()-450000000)*0.2);
        }

        return benefice+beneficeinves+fraisTrans+fraiscompte  ;
    }

    @Override
    public double avergetotalassets() {
        float crdits=0;
        float invest=0;
        for (Credits credits : iCreditService.selectAll()){
            crdits=crdits+credits.getAmount();

        }
        for (Investtisment inves : iInvesttismentServices.selectAll()){
            invest=invest+inves.getAmount();
        }
        //ras el mel 3000000000

        return crdits+invest+300000000 ;
    }
    @Override
    public double returnOnAssets() {

        return netincome()/avergetotalassets();
    }

    @Override
    public String mostprofitableloans() {
        List<Float> profits = new ArrayList<Float>();
        //List<Float> creditpercentage = new ArrayList<Float>();
        //float s0=0;float s1=0;float s2=0;float s3=0;float s4=0;float s5=0;
        float total=0;float cash=0;float bill=0;float hybrid=0;float product=0;float school=0; float payday=0;

        for (Credits credits : iCreditService.selectAll()){
            total=total+credits.getAmount();
            String type = String.valueOf(credits.getTypeCredit());
            if (type.equals("CASH_LOAN")){
                cash= cash+ credits.getAmount()*(credits.getInterestRate()/100);
                //s0=s0+credits.getAmount();

            } else if (type.equals("BILL_DISCOUNTING_LOAN")) {
                bill= bill + credits.getAmount()*(credits.getInterestRate()/100);
                //s1=s1+credits.getAmount();
            } else if (type.equals("HYPRID_LOAN")) {
                hybrid = hybrid + credits.getAmount()*(credits.getInterestRate()/100);
                //s2=s2+credits.getAmount();
            } else if (type.equals("PRODUCT_LOAN")) {
                product = product + credits.getAmount()*(credits.getInterestRate()/100);
                //s3=s3+credits.getAmount();
            } else if (type.equals("SCHOOL_FEES_LOAN")) {
                school = school + credits.getAmount()*(credits.getInterestRate()/100);
               // s4=s4+credits.getAmount();
            } else if (type.equals("PAYDAY_LOAN")) {
                payday = payday + credits.getAmount()*(credits.getInterestRate()/100);
                //s5=s5+credits.getAmount();

            }

        }
        List<String> typecredit= new ArrayList<>(Arrays.asList("CASH_LOAN", "BILL_DISCOUNTING_LOAN", "HYPRID_LOAN","PRODUCT_LOAN","SCHOOL_FEES_LOAN","PAYDAY_LOAN"));
        //creditpercentage.addAll(Arrays.asList((s0*100)/total,(s1*100)/total,(s2*100)/total,(s3*100)/total,(s4*100)/total,(s5*100)/total));
        profits.addAll(Arrays.asList(cash,bill,hybrid,product,school,payday));
        String max = String.valueOf(Collections.max(profits));
        int index = profits.indexOf(max)+1;
        return "The most profitable loans are:  "+ typecredit.get(index)+" With "+max +" Benefits "    ;
        //return Collections.max(profits);
    }

    @Override
    public float Loan_to_Deposit_Ratio() {
        float total_loans=0;
        float total_deposits=0;
        for (Credits credits : iCreditService.selectAll()){
            total_loans= total_loans+credits.getAmount();
            total_deposits=total_deposits+(credits.getAmount()*(credits.getInterestRate()/100)+credits.getAmount());

        }
        return total_loans/total_deposits;

    }

    //Loan-to-Deposit Ratio (LDR) = Total Loans / Total Deposits



















}
