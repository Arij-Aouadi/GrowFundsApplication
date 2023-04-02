package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;
import tn.esprit.spring.Services.Interfaces.IMonthlyPayment;

import java.util.List;

@RestController
@AllArgsConstructor
public class MonthlyPaymentController {
    private IMonthlyPayment iMonthlyPayment;

    @GetMapping("/afficherpaymentmensuel")
    public List<MonthlyPayment> afficher() {
        return iMonthlyPayment.selectAll();
    }
    @PostMapping("/ajouterpaymentmensuel")
    public MonthlyPayment ajouter(@RequestBody MonthlyPayment monthlyPayment){
        return iMonthlyPayment.add(monthlyPayment);

    }
    @GetMapping("/afficherpaymentmensuelparid/{id}")
    public MonthlyPayment afficherAvecId(@PathVariable int id){
        return iMonthlyPayment.selectById(id);
    }

    @PostMapping("/ajoutertoutpayment")

    public List<MonthlyPayment> addAllpayments (@RequestBody List<MonthlyPayment> list){

        return iMonthlyPayment.addAll(list);
    }
    @PutMapping ("/modifierpayementmensuel")
    public MonthlyPayment editPayment (@RequestBody MonthlyPayment monthlyPayment){
        return iMonthlyPayment.edit(monthlyPayment);}

    @DeleteMapping ("/deletecmonthlypaymentbyid")
    public void deletebMonthlyPaymentyId (@RequestParam int id){
        iMonthlyPayment.deleteById(id);
    }


    @DeleteMapping ("/deletemonthlypayment")
    public void deletebyobjectbyid (@RequestBody MonthlyPayment monthlyPayment){
        iMonthlyPayment.delete(monthlyPayment);}

    @PostMapping("/paymentSupposedPayment")
    public MonthlyPayment showPayment (@RequestBody MonthlyPayment monthlyPayment){
       return iMonthlyPayment.CalculateDueDate(monthlyPayment);
    }
}
