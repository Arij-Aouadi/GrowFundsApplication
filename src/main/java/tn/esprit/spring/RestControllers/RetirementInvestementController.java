package tn.esprit.spring.RestControllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class RetirementInvestementController {

    @GetMapping("/retirementPlan")
    public List<Map<String, Integer>> calculateRetirementPlan(
            @RequestParam(value = "currentAge") int currentAge,
            @RequestParam(value = "initialNetWorth") double initialNetWorth,
            @RequestParam(value = "yearlyIncome") double yearlyIncome,
            @RequestParam(value = "yearlyIncreaseSalary") double yearlyIncreaseSalary,
            @RequestParam(value = "portfolioYearlyReturn") double portfolioYearlyReturn,
            @RequestParam(value = "portfolioFees") double portfolioFees,
            @RequestParam(value = "percentageInvesting") double percentageInvesting,
            @RequestParam(value = "retirementAge") int retirementAge) {
        List<Map<String, Integer>> retirementPlan = new ArrayList<>();
        double netWorth = initialNetWorth;
        double income = yearlyIncome;
        double yearlyInvestment = income * percentageInvesting;
        for (int i = currentAge; i <= retirementAge; i++) {
            Map<String, Integer> yearData = new HashMap<>();
            yearData.put("Age",i);
            yearData.put("income", (int) income);
            yearData.put("yearlyInvestment", (int)yearlyInvestment);
            double investmentReturn = netWorth * portfolioYearlyReturn;
            double fees = investmentReturn * portfolioFees;
            double investmentReturnAfterFees = investmentReturn - fees;
            double netWorthBeforeFees = netWorth + yearlyInvestment + investmentReturn;
            double netWorthAfterFees = netWorth + yearlyInvestment + investmentReturnAfterFees;
            yearData.put("investmentReturnAfterFees",(int)investmentReturnAfterFees);
            yearData.put("netWorthBeforeFees",(int) netWorthBeforeFees);
            yearData.put("netWorthAfterFees", (int)netWorthAfterFees);
            retirementPlan.add(yearData);
            income *= (1 + yearlyIncreaseSalary);
            yearlyInvestment = income * percentageInvesting;
            netWorth = netWorthAfterFees;
        }

        return retirementPlan;
    }
    @GetMapping("/retirementPlan2")
    public List<Integer> calculateRetirementPlan2(
            @RequestParam(value = "currentAge") int currentAge,
            @RequestParam(value = "initialNetWorth") double initialNetWorth,
            @RequestParam(value = "yearlyIncome") double yearlyIncome,
            @RequestParam(value = "yearlyIncreaseSalary") double yearlyIncreaseSalary,
            @RequestParam(value = "portfolioYearlyReturn") double portfolioYearlyReturn,
            @RequestParam(value = "portfolioFees") double portfolioFees,
            @RequestParam(value = "percentageInvesting") double percentageInvesting,
            @RequestParam(value = "retirementAge") int retirementAge) {
        double netWorth = initialNetWorth;
        double income = yearlyIncome;
        double yearlyInvestment = income * percentageInvesting;
        List<Integer>res= new ArrayList<Integer>();
        for (int i = currentAge; i <= retirementAge; i++) {
            res.add(i);
            double investmentReturn = netWorth * portfolioYearlyReturn;
            double fees = investmentReturn * portfolioFees;
            double investmentReturnAfterFees = investmentReturn - fees;
            double netWorthAfterFees = netWorth + yearlyInvestment + investmentReturnAfterFees;
            res.add((int)netWorthAfterFees);
            income *= (1 + yearlyIncreaseSalary);
            yearlyInvestment = income * percentageInvesting;
            netWorth = netWorthAfterFees;
        }

        return res;
    }
}
