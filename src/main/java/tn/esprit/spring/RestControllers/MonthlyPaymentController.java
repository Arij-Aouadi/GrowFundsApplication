package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;
import tn.esprit.spring.Services.Interfaces.IMonthlyPayment;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

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
    @GetMapping("admin/getCreditMonthlyPayments/{id}")
    public List<MonthlyPayment> afficherMPAvecId(@PathVariable int id){
        return iMonthlyPayment.getCreditMonthlyPayment(id);
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


    @GetMapping("/getLateDays/{id}")
    public int LateDays (@PathVariable int id){
        return iMonthlyPayment.calculateLateDays(id);
    }

}
