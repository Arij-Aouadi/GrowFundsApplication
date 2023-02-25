package tn.esprit.spring.RestControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.Services.interfaces.IProjectsServices;

import java.util.List;

@RestController
public class ProjectsControllers {
    private IProjectsServices iProjectsServices;
    @GetMapping("/afficherProjects")
    public List< > afficherProjects() {
        return iProjectsServices.selectAll();
    }

    @PostMapping("ajouterCredit")
    public Credits ajouterCredit(@RequestBody Credits credits ){
        return iCreditService.add(credits);
    }

    @GetMapping("afficherCreditAvecId/{id}")
    public Credits afficherCreditAvecId(@PathVariable int id){
        return iCreditService.selectById(id);

    }

}
