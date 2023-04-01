package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.DAO.Repositories.TransactionRepository;
import tn.esprit.spring.Services.Interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
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
        double A = cii * Math.pow(1 + c / 12, duree * 12);
        return A;

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
        liste.add(course);
        liste.add(fuel);
        liste.add(transport);
        liste.add(entertaiment);
        liste.add(education);
        liste.add(credit_loans);
        liste.add(uncategorized);

        return liste ;

            }
    //------------------------------BANK_STATICS---------------

    @Override
    public float banknetincome() {
        return 0;
    }

    @Override
    public float netincome() {
        float benefice=0;
        float beneficeinves =0 ;
        //Somme totale à rembourser = Capital + Intérêts
        //Intérêts = Capital x Taux d'intérêt x Durée du crédit
        for (Credits credits : iCreditService.selectAll()){
            LocalDate start =credits.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fin =credits.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period period = Period.between(start,fin);
             benefice = (credits.getAmount() * (credits.getInterestRate()/100) * period.toTotalMonths());
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
        }

        return benefice+beneficeinves+fraisTrans+fraiscompte  ;
    }

    @Override
    public float avergetotalassets() {
        return 0;
    }
















}
