package tn.esprit.spring.Services.Interfaces;

import java.util.HashMap;
import java.util.List;

public interface IAccountandBankStatistics  {
    double calculPlacement(float cii,float tia ,int duree );
    List<Float> monthlyspendingsscategorized(int rib , int month);
    double returnOnAssets();
    double netincome();
    double avergetotalassets();
    String mostprofitableloans();

            //Revenus totaux =benefice of loans + pourcentage de amount investment
    //Bénéfice net = Revenus totaux - Charges totales
          //  frais de transaction ,des frais mensuels pour la gestion du compte
    //des frais de dépassement si le solde du compte d'épargne dépasse le montant maximum autoris
    // Frais de dépassement = (Solde du compte - Montant maximum autorisé) x Taux de dépassement
    //Loan-to-Deposit Ratio (LDR) = Total Loans / Total Deposits
}
