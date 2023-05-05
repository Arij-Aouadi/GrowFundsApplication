package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Project;
import tn.esprit.spring.DAO.Entities.Revenue;
import tn.esprit.spring.DAO.Entities.TypeProjectStatus;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Classes.UserService;
import tn.esprit.spring.Services.Interfaces.IProjectsServices;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class ProjectsControllers {
    private IProjectsServices iProjectsServices;
    private UserService userService;
    @GetMapping("/client/projects/client/{cid}")
    public List<Project> getProjectsByFounder(@PathVariable long cid) {
        User u = userService.getById(cid);
        return iProjectsServices.getProjectsForFounder(u);
    }
    @GetMapping("/client/allprojects")
    public List<Project> getAll() {
        return iProjectsServices.selectAll();
    }

    @PostMapping("/client/project/add/client/{cid}")
    public Project addProject( @RequestBody Project p,@PathVariable long cid) {
        User u = userService.getById(cid);

        p.setStatus(TypeProjectStatus.NOT_STARTED);
        return iProjectsServices.add(p,u);

    }
    @GetMapping("/client/project/{id}")
    public Project getProjectById(@PathVariable int id) {
        return iProjectsServices.selectById(id);

    }
    @PostMapping("/client/project/{id}/addRevenue")
    public Project addRevenue(@PathVariable(value = "id")int id, @RequestBody Revenue r) {
        Date d = new Date();
        r.setDateDeclaration(d);
        return iProjectsServices.addRevenue(r,id);

    }


    @GetMapping("/admin/projects")
    public List<Project> getAllProjects() {
        return iProjectsServices.selectAll();
    }

    @PutMapping ("/admin/projects/edit")
    public Project editProjects(@RequestBody Project p){

        return iProjectsServices.edit(p);}


    /*



    @PostMapping("/ajouterProjects/{id}")
    public Project ajouterProjects(@RequestBody Project projects, @PathVariable Long id) {
        return iProjectsServices.add(projects,id);
    }






    @DeleteMapping ("/deleteProjectsbyid")
    public void deletebyidProjects (@RequestParam int id){
        iProjectsServices.deleteById(id);}


    @DeleteMapping ("/deleteProjects")
    public void deletebyobjectProjects (@RequestBody Project projects){
        iProjectsServices.delete(projects);}
    /*@GetMapping("/findByUser/{idUser}")
    public List<Projects> GetProjectByUser(@PathVariable Long idUser){

        return iProjectsServices.Get_projects_by_User(idUser);
    }
    //http://localhost:1009/search
    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchPosts(@RequestParam("query") String query) {
        List<Project> matchingPosts = iProjectsServices.Searchprojects(query);
        if (matchingPosts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(matchingPosts);

    }
    //http://localhost:1009/getProjectsByInvestor/{{investorId}}
    @GetMapping("/getProjectsByInvestor/{investorId}")
    public List<Project> getProjectsByInvestor(@PathVariable Long investorId) {
        User investor = new User();
        investor.setId(investorId);
        return iProjectsServices.getProjectsByInvestor(investor);
    }//http://localhost:1009/suggestInvestorsForProject/{{investor}}
    @GetMapping("/suggestInvestorsForProject/{investor}")
    public Project suggestInvestorsForProject(@PathVariable User investor) {
        return iProjectsServices.suggestInvestorsForProject(investor);
    }*/

}
