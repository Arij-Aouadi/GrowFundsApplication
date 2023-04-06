package tn.esprit.spring.RestControllers;


import com.fasterxml.jackson.databind.util.StdDateFormat;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.esprit.spring.DAO.Entities.Amortisement;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.PostModel;
import tn.esprit.spring.Services.Classes.PostService;
import tn.esprit.spring.Services.Interfaces.ICreditService;
import tn.esprit.spring.Services.Interfaces.ICreditService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/getAmortisementTable")
    public List<Amortisement> TABLE(@RequestParam int idCredit) {
        return iCreditService.amortisement(idCredit);
    }

    @GetMapping("/pdf/generateAmortissement")
    public void generatePdf(HttpServletResponse response, int idCredit) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey ="Content-Disposition";
        String headerValue ="attachment; filename=Tableau_Credit_N_"+idCredit+".pdf";
        response.setHeader(headerKey, headerValue);
        iCreditService.export(response,idCredit);
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
