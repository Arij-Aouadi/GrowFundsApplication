package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.Services.Interfaces.IAccountandBankStatistics;

import java.util.List;
@RestController
@AllArgsConstructor

public class AccountandBankStatisticsRestController {
    private IAccountandBankStatistics iAccountandBankStatistics;

    @GetMapping("/Calculateplacement")
    public String calculPlacement(@RequestParam("Placement Initial") float cii,
                                  @RequestParam("Taux D'intert annuel") float tia,
                                  @RequestParam("Durée") int duree) {

        String Valeur = String.valueOf(iAccountandBankStatistics.calculPlacement(cii,tia,duree));
        String Valeur2 = String.valueOf(iAccountandBankStatistics.calculPlacement(cii,tia,duree)-cii);
        //valeur2=iAccountStat.calculPlacement(cii,tia,duree)-cii
        //return iAccountStat.calculPlacement(cii,tia,duree)
        return "Valeur totale de votre placement :"+ Valeur     +"Total des intérêts gagnés"+ Valeur2 ;
    }
    @GetMapping("/MonthlySpendingssCategorized")
    public List<Float> monthlyspendingsscategorized(@RequestParam int rib, @RequestParam int month){
        return iAccountandBankStatistics.monthlyspendingsscategorized(rib, month);
    }
    @GetMapping("/BankNetIncome")
    public float netincome(){
        return iAccountandBankStatistics.netincome();
    }

}
