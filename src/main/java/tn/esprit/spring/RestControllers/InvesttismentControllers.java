package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.Services.interfaces.IInvesttismentServices;

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

}



