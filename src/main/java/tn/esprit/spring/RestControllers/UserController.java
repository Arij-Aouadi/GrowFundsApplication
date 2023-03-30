package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Interfaces.IUserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private IUserService iUserService;
    @GetMapping("/afficherUser")
    public List<User> afficherUser() {

        List <User> list= iUserService.selectAll();
        return list ;
    }

    @GetMapping("/afficherUserAvecId/{id}")
    public User afficherUserAvecId(@PathVariable long id)
    {
        return iUserService.selectById(id);

    }




    @PutMapping ("/modifierUser")
    public User edit(@RequestBody User user){
        return iUserService.edit(user);}
    @DeleteMapping ("/deleteUserbyid/{id}")
    public void deletebyid (@PathVariable long id){
        iUserService.deleteById(id);}
    @DeleteMapping ("/deleteUser")
    public void deletebyobject (@RequestBody User user){
        iUserService.delete(user);}

}
