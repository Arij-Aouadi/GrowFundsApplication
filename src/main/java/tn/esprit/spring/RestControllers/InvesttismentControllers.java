package tn.esprit.spring.RestControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.Services.Interfaces.IInvesttismentServices;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class InvesttismentControllers {
    private IInvesttismentServices iInvesttismentServices;


    @GetMapping("/afficherInvesttisment")
    public List<Investtisment> afficherInvesttisment()
    {

        return iInvesttismentServices.selectAll();
    }

    @PostMapping("ajouterInvesttisment")
    public Investtisment ajouterInvesttisment(@RequestBody Investtisment investtisment) {
        return iInvesttismentServices.add(investtisment);
    }

    @GetMapping("afficherInvesttismentAvecId/{id}")
    public Investtisment afficherInvesttismentAvecId(@PathVariable int id) {
        return iInvesttismentServices.selectById(id);
    }



    @PostMapping("/ajouterallInvesttisment")

    public List<Investtisment> addAllProjects ( @RequestBody List<Investtisment> list){

        return iInvesttismentServices.addAll(list);
    }
    @PutMapping ("/modifierInvesttisment")
    public Investtisment editInvesttisment(@RequestBody Investtisment Investtisment){
        return iInvesttismentServices.edit(Investtisment);}

    @DeleteMapping ("/deleteInvesttismentbyid")
    public void deletebyidInvesttisment (@RequestParam int id){
        iInvesttismentServices.deleteById(id);
    }


    @DeleteMapping ("/deleteInvesttisment")
    public void deletebyobjectInvesttisment(@RequestBody Investtisment investtisment){
        iInvesttismentServices.delete(investtisment);}

  //  http://localhost:1006/CalculateAmoutOfInves/{{Investesment-id}}
    @GetMapping("/CalculateAmoutOfInves/{Investesment-id}")
    @ResponseBody
    public void CalculateAmoutOfInves(@PathVariable("Investesment-id") int idInvestesment) {
        iInvesttismentServices.CalculateAmoutOfInves(idInvestesment);
    }
  //  http://localhost:1006/finalAmount
    @GetMapping("/finalAmount")

    public void finalAmount() {
        iInvesttismentServices.finalAmount();
    }

  //  http://localhost:1006/export&


    @GetMapping("/export&")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String currentDateTime = dateFormater.format(new Date());
      String headerKey = "Content-Disposition";
        String headerValue = "Attachement;filename=inves_"+ currentDateTime + ".pdf";
         response.setHeader(headerKey,headerValue);
         List<Investtisment> listInvestesment = iInvesttismentServices.selectAll();
        InvestissmentPdfexport exporter= new InvestissmentPdfexport(listInvestesment);
        exporter.export(response);

    }








}






