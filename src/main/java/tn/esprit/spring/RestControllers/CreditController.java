package tn.esprit.spring.RestControllers;


import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.PostModel;
import tn.esprit.spring.Services.Classes.PostService;
import tn.esprit.spring.Services.Interfaces.ICreditService;
import tn.esprit.spring.Services.Interfaces.ICreditService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/credit")
public class CreditController {
    private ICreditService iCreditService;
    @GetMapping("/showAllCredits")
    public List<Credits> showCredits()
    {
        return iCreditService.selectAll();
    }

    @GetMapping("/mensuelPayment")
    public float calculateMonthlyPayment(@RequestParam int idCredit) {
        return iCreditService.calculeMonthlyPayment(idCredit);
    }

    private PostService postService;
    @PostMapping("/predictClientClassAndSetInterestRate")
    @PreAuthorize("hasRole('AGENT')")
    public PostModel showPredictions(@RequestParam int idCredit) {
        PostModel predictions= postService.getPredictionByCreditId(idCredit);
        return predictions;
    }

    @GetMapping("/showCreditByStatus")
    public List<Credits> showCreditByStatus(@RequestParam String status) {
        return iCreditService.GetCreditsByStatus(status);
    }

    @GetMapping("/selectCreditByAccountNum")
    public boolean doesCreditExist(@RequestParam int numAccount) {
        return iCreditService.CreditExists(numAccount);
    }

    @PostMapping("/addCredit")
    public Credits addCredit (@RequestBody Credits credits){
        return iCreditService.add(credits);

    }
    @GetMapping("/selectCreditById/{id}")
    public Credits selectCreditById(@PathVariable int id){
        return iCreditService.selectById(id);
    }

    @PostMapping("/addlistOfCredits")

    public List<Credits> addAllCredit (@RequestBody List<Credits> list){

        return iCreditService.addAll(list);
    }
    @PutMapping ("/updateCredit")
    public Credits editCredit (@RequestBody Credits credits){
        return iCreditService.edit(credits);}

    @DeleteMapping ("/deleteCreditById")
    public void deletebyidCredit (@RequestParam int id){
        iCreditService.deleteById(id);
    }


    @DeleteMapping ("/deleteCredit")
    public void deletebyobjectbyid (@RequestBody Credits credits){
        iCreditService.delete(credits);}

}
