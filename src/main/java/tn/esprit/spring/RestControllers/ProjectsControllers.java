package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.Services.Interfaces.IProjectsServices;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProjectsControllers {
    private IProjectsServices iProjectsServices;

    @GetMapping("/afficherProjects")
    public List<Projects> afficherProjects() {
        return iProjectsServices.selectAll();
    }

    @PostMapping("/ajouterProjects")
    public Projects ajouterProjects(@RequestBody Projects projects) {
        return iProjectsServices.add(projects);
    }

    @GetMapping("afficherProjectsAvecId/{id}")
    public Projects afficherProjectsAvecId(@PathVariable int id) {
        return iProjectsServices.selectById(id);

    }

    @PostMapping("/ajouterallProjects")

    public List<Projects> addAllProjects ( @RequestBody List<Projects> list){

        return iProjectsServices.addAll(list);
    }
    @PutMapping ("/modifierProjects")
    public Projects editProjects(@RequestBody Projects Projects){
        return iProjectsServices.edit(Projects);}

    @DeleteMapping ("/deleteProjectsbyid")
    public void deletebyidProjects (@RequestParam int id){
        iProjectsServices.deleteById(id);}


    @DeleteMapping ("/deleteProjects")
    public void deletebyobjectProjects (@RequestBody Projects projects){
        iProjectsServices.delete(projects);}


}
