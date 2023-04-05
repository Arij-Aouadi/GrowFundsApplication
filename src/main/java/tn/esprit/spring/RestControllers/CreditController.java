package tn.esprit.spring.RestControllers;


import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
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
public class CreditController {
    private ICreditService iCreditService;
    @GetMapping("/affichercredits")
    public List<Credits> afficherCredits() {
        return iCreditService.selectAll();
    }

    private PostService postService;
    @PostMapping("/predict")
    public PostModel showPredictions(@RequestParam int idCredit) {
        PostModel predictions= postService.getPredictionByCreditId(idCredit);
        return predictions;
    }

    @GetMapping("/afficherCreditsParStatus")
    public List<Credits> afficherCreditStatus(@RequestParam String status) {
        return iCreditService.GetCreditsByStatus(status);
    }

    @GetMapping("/affichercreditparnumcompte")
    public boolean afficherCreditExiste(@RequestParam int numAccount) {
        return iCreditService.CreditExists(numAccount);
    }

    @PostMapping("/ajoutercredit")
    public Credits ajouter(@RequestBody Credits credits){
        return iCreditService.add(credits);

    }
    @GetMapping("/affichercreditid/{id}")
    public Credits afficherAvecId(@PathVariable int id){
        return iCreditService.selectById(id);
    }

    @PostMapping("/ajouterlistcredit")

    public List<Credits> addAllCredit (@RequestBody List<Credits> list){

        return iCreditService.addAll(list);
    }
    @PutMapping ("/modifiercredit")
    public Credits editCredit (@RequestBody Credits credits){
        return iCreditService.edit(credits);}

    @DeleteMapping ("/deletecreditbyid")
    public void deletebyidCredit (@RequestParam int id){
        iCreditService.deleteById(id);
    }


    @DeleteMapping ("/deletecredit")
    public void deletebyobjectbyid (@RequestBody Credits credits){
        iCreditService.delete(credits);}

}
