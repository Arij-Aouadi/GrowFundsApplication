package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Services.Interfaces.IAccountandBankStatistics;

import java.util.ArrayList;
import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class AccountandBankStatisticsRestController {
    private IAccountandBankStatistics iAccountandBankStatistics;

    @GetMapping("/calculate-placement/{v1}/{v2}/{v3}")
    public List<Double> calculPlacement(
            @PathVariable("v1") float cii,
                                  @PathVariable("v2") float tia,
                                  @PathVariable("v3") int duree
    ) {


        //valeur2=iAccountStat.calculPlacement(cii,tia,duree)-cii
        //return iAccountStat.calculPlacement(cii,tia,duree)

        List<Double> res=new ArrayList<>() ;
        res.add(iAccountandBankStatistics.calculPlacement(cii,tia,duree));
        res.add(iAccountandBankStatistics.calculPlacement(cii,tia,duree)-cii);
        return res;
    }
    @GetMapping("/client/month/monthly-Spendings/{rib}/{month}")
    public List<Float> monthlyspendingsscategorized(@PathVariable int rib, @PathVariable int month){
        return iAccountandBankStatistics.monthlyspendingsscategorized(rib, month);
    }
    //----------------ROA----------------
    @GetMapping("/client/roa")
   public double returnOnAssets(){
        return iAccountandBankStatistics.returnOnAssets();
    }
    @GetMapping("/client/most-profit")
    public List<Float> mostprofitableloans(){
        return iAccountandBankStatistics.mostprofitableloans();
    }
    @GetMapping("/client/ldr")
        public float Loan_to_Deposit_Ratio(){

        return iAccountandBankStatistics.Loan_to_Deposit_Ratio();
    }

}
