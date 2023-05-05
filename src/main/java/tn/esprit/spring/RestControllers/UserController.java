package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.Services.Interfaces.IUserService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {
    private IUserService iUserService;
    @GetMapping("/admin/users")
    public List<User> afficherUser() {

        List <User> list= iUserService.selectAll();

        return list ;
    }
    @GetMapping("/admin/agents")
    public List<User> getAgents() {

        List <User> list= iUserService.getAgents();

        return list ;
    }

    @GetMapping("/admin/admins")
    public List<User> getAdmins() {

        List <User> list= iUserService.getAdmins();

        return list ;
    }
    @GetMapping("/admin/clients")
    public List<User> getClients() {

        List <User> list= iUserService.getClients();

        return list ;
    }


    @GetMapping("/admin/users/u/{id}")
    public User afficherUserAvecId(@PathVariable long id)
    {
        return iUserService.selectById(id);

    }

    @PutMapping ("/admin/users/edit/{id}")
    public User edit(@PathVariable Long id,@RequestBody User user){
        return iUserService.edit(user);}
    @DeleteMapping ("/admin/users/delete/{id}")
    public void deletebyid (@PathVariable long id){
        iUserService.deleteById(id);}
    @DeleteMapping ("/admin/users/deleteAll")
    public void deleteAll (List<User> list) {
        iUserService.deleteAll(list);
    }

    @PutMapping("/affecterRoleToUser")
    public void affecterRoleToUser(@RequestParam Long idRole,@RequestParam Long id) {
        iUserService.affecterRoleToUser(idRole, id);
    }
    @GetMapping("currentUsers")
    public  User current (){
        return iUserService.getCurrentUser();
    }

    @PutMapping("/assignAccountToUser/{id}/{num}")
    public User assignSkierToPiste(@PathVariable long id, @PathVariable int num){
        return iUserService.assignAccountToUser(id,num);
    }
}
