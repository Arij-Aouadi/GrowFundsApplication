package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.ProjectsRepository;
import tn.esprit.spring.Services.Interfaces.IProjectsServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProjectsControllers {
    private IProjectsServices iProjectsServices;

    @GetMapping("/afficherProjects")
    public List<Projects> afficherProjects() {
        return iProjectsServices.selectAll();
    }

    @PostMapping("/ajouterProjects/{id}")
    public Projects ajouterProjects(@RequestBody Projects projects, @PathVariable Long id) {
        return iProjectsServices.add(projects,id);
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
    /*@GetMapping("/findByUser/{idUser}")
    public List<Projects> GetProjectByUser(@PathVariable Long idUser){

        return iProjectsServices.Get_projects_by_User(idUser);
    }*/
    @GetMapping("/search")
    public ResponseEntity<List<Projects>> searchPosts(@RequestParam("query") String query) {
        List<Projects> matchingPosts = iProjectsServices.Searchprojects(query);
        if (matchingPosts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(matchingPosts);
    }
    @GetMapping("/getProjectsByInvestor/{investorId}")
    public List<Projects> getProjectsByInvestor(@PathVariable Long investorId) {
        User investor = new User();
        investor.setId(investorId);
        return iProjectsServices.getProjectsByInvestor(investor);
    }
    @GetMapping("/suggestInvestorsForProject/{investor}")
    public Projects suggestInvestorsForProject(@PathVariable User investor) {
        return iProjectsServices.suggestInvestorsForProject(investor);
    }

}
