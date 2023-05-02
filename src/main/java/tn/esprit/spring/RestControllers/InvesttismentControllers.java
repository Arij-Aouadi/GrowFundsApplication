package tn.esprit.spring.RestControllers;

import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Investisment;
import tn.esprit.spring.DAO.Entities.Project;
import tn.esprit.spring.DAO.Entities.TypeProjectStatus;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Interfaces.IInvesttismentServices;
import tn.esprit.spring.Services.Interfaces.IProjectsServices;
import tn.esprit.spring.Services.Interfaces.IUserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class InvesttismentControllers {
    private IInvesttismentServices iInvesttismentServices;
    private IProjectsServices iProjectsServices;

    @GetMapping("/client/investments")
    public List<Investisment> getAllClientInvestments()
    {
        return iInvesttismentServices.selectByClient();
    }
    @PostMapping("/client/investments/{id}/add")
    public Project ajouterInvesttisment(@PathVariable(value = "id") int id, @RequestBody Investisment investtisment) {
        iInvesttismentServices.add(investtisment);
        Project p = iProjectsServices.selectById(id);
        p.getInvestisments().add(investtisment);
        iProjectsServices.edit(p);
        return p;
    }
    //client
    /*

    @GetMapping("/afficherInvesttisment")
    public List<Investisment> afficherInvesttisment()
    {

        return iInvesttismentServices.selectAll();
    }

    @PostMapping("ajouterInvesttisment")
    public Investisment ajouterInvesttisment(@RequestBody Investisment investtisment) {
        return iInvesttismentServices.add(investtisment);
    }

    @GetMapping("afficherInvesttismentAvecId/{id}")
    public Investisment afficherInvesttismentAvecId(@PathVariable int id) {
        return iInvesttismentServices.selectById(id);
    }



    @PostMapping("/ajouterallInvesttisment")

    public List<Investisment> addAllProjects (@RequestBody List<Investisment> list){

        return iInvesttismentServices.addAll(list);
    }
    @PutMapping ("/modifierInvesttisment")
    public Investisment editInvesttisment(@RequestBody Investisment Investtisment){
        return iInvesttismentServices.edit(Investtisment);}

    @DeleteMapping ("/deleteInvesttismentbyid")
    public void deletebyidInvesttisment (@RequestParam int id){
        iInvesttismentServices.deleteById(id);
    }


    @DeleteMapping ("/deleteInvesttisment")
    public void deletebyobjectInvesttisment(@RequestBody Investisment investtisment){
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
         List<Investisment> listInvestesment = iInvesttismentServices.selectAll();
        InvestissmentPdfexport exporter= new InvestissmentPdfexport(listInvestesment);
        exporter.export(response);

    }


*/





}






