package tn.esprit.spring.RestControllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.Services.interfaces.IInvesttismentServices;
import tn.esprit.spring.Services.interfaces.IProjectsServices;

import java.util.List;

@RestController
public class InvesttismentControllers {
    private IInvesttismentServices iInvesttismentServices;


    @GetMapping("/afficherInvesttisment")
    public List<Investtisment> afficherInvesttisment() {
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
}



