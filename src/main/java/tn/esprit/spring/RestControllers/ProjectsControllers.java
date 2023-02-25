package tn.esprit.spring.RestControllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.Services.interfaces.IProjectsServices;

import java.util.List;

@RestController
public class ProjectsControllers {
    private IProjectsServices iProjectsServices;

    @GetMapping("/afficherProjects")
    public List<Projects> afficherProjects() {
        return iProjectsServices.selectAll();
    }

    @PostMapping("ajouterProjects")
    public Projects ajouterProjects(@RequestBody Projects projects) {
        return iProjectsServices.add(projects);
    }

    @GetMapping("afficherProjectsAvecId/{id}")
    public Projects afficherProjectsAvecId(@PathVariable int id) {
        return iProjectsServices.selectById(id);

    }
}
