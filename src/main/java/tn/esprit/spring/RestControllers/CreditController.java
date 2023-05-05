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
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.UserRepository;
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
@CrossOrigin(origins = "http://localhost:4200")

public class CreditController {
    private ICreditService iCreditService;
    private UserRepository userRepository;
    @GetMapping("/admin/showAllCredits")
    public List<Credits> showCredits()
    {
        return iCreditService.selectAll();
    }

    @GetMapping("/admin/mensuelPayment/{id}")
    public float calculateMonthlyPayment(@PathVariable int id) {
        return iCreditService.calculeMonthlyPayment(id);
    }
    @GetMapping("/admin/returnCreditUser/{id}")
    public User returnUser(@PathVariable int id) {
        return userRepository.returnUserofCredit(id);
    }

    private PostService postService;
    @GetMapping("/admin/predictClientClassAndSetInterestRate/{id}")
    public PostModel showPredictions(@PathVariable int id) {
        PostModel predictions= postService.getPredictionByCreditId(id);
        return predictions;
    }

    @GetMapping("/showCreditByStatus")
    public List<Credits> showCreditByStatus(@RequestParam String status) {
        return iCreditService.GetCreditsByStatus(status);
    }
    @GetMapping("/getClientCredits/{num}")
    public List<Credits> showClientCredit(@PathVariable int num) {
        return iCreditService.clientCredits(num);
    }

    @GetMapping("/selectCreditByAccountNum")
    public boolean doesCreditExist(@RequestParam int numAccount) {
        return iCreditService.CreditExists(numAccount);
    }

    @GetMapping("/getAmortisementTable")
    public List<Amortisement> TABLE(@RequestParam int idCredit) {
        return iCreditService.amortisement(idCredit);
    }

    @GetMapping("/pdf/generateAmortissement/{id}")
    public void generatePdf(HttpServletResponse response,@PathVariable int id) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey ="Content-Disposition";
        String headerValue ="attachment; filename=Tableau_Credit_N_"+id+".pdf";
        response.setHeader(headerKey, headerValue);
        iCreditService.export(response,id);
    }

    @PostMapping("/addCredit")
    public Credits addCredit (@RequestBody Credits credits){
        return iCreditService.add(credits);

    }
    @GetMapping("/admin/selectCreditById/{id}")
    public Credits selectCreditById(@PathVariable int id){
        return iCreditService.selectById(id);
    }

    @PostMapping("/addlistOfCredits")

    public List<Credits> addAllCredit (@RequestBody List<Credits> list){

        return iCreditService.addAll(list);
    }
    @PutMapping ("admin/updateCredit")
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
