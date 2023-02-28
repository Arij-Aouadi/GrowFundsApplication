package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.PaymentMethod;
import tn.esprit.spring.DAO.Entities.TypePayment;
import tn.esprit.spring.Services.Classes.PaymentMethodService;

import java.util.List;

@RequestMapping("/PaymentMethod")
@RestController
@AllArgsConstructor
public class PaymentMethodRestControllers {
    private PaymentMethodService iPaymentMethodService;

    @GetMapping("/afficherPayementMethod")
    public List<PaymentMethod> afficher() {
        return iPaymentMethodService.selectAll();
    }
    @GetMapping("/afficherPayementMethodAvecId/{id}")
    public PaymentMethod afficherAccountAvecId(@PathVariable int id)
    {
        return iPaymentMethodService.selectById(id);
    }

    @PostMapping("/ajouterPayementMethod")
    public PaymentMethod ajouter(@RequestBody PaymentMethod paymentMethod){
        return iPaymentMethodService.add(paymentMethod);

    }
    @PostMapping("/ajouterallPayments")

    public List<PaymentMethod> addAll (List<PaymentMethod>list){
        return iPaymentMethodService.addAll(list);
    }
    @PutMapping ("/modifierPayments")
    public PaymentMethod edit(@RequestBody PaymentMethod paymentmethod){
        return iPaymentMethodService.edit(paymentmethod);}

    @DeleteMapping ("/deletePaymentbyid")
    public void deletebyid (@RequestParam int id){
        iPaymentMethodService.deleteById(id);}


    @DeleteMapping ("/deletePayment")
    public void deletebyobject (@RequestBody PaymentMethod paymentmethod){
        iPaymentMethodService.delete(paymentmethod);}
    @DeleteMapping ("/deleteAllPayment")
    public void deleteAll (List<PaymentMethod>list) {
        iPaymentMethodService.deleteAll(list);
    }
    @GetMapping("/retrievePaymentMethodwithTypePaym")
    public List<PaymentMethod>afficheravectypepay(@RequestParam TypePayment type){
        return iPaymentMethodService.selectByTypePayment(type);
    }


}
