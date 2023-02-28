package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.Services.Interfaces.IAccountService;

import java.util.List;

@RestController
@AllArgsConstructor

public class AccountRestControllers {
    private IAccountService iAccountService;

    @GetMapping("/afficherAccount")
    public List<Account> afficher() {

        List <Account> list= iAccountService.selectAll();
        return list ;
    }
    @GetMapping("/afficherAccountAvecId/{id}")
    public Account afficherAccountAvecId(@PathVariable int id)
    {
        return iAccountService.selectById(id);

    }

    @PostMapping("/ajouterAccount")
    public Account ajouter(@RequestBody Account account){
        return iAccountService.add(account);

    }
    @PostMapping("/ajouterallAccount")

    public List<Account> addAll (List<Account>list){
        return iAccountService.addAll(list);
    }
    @PutMapping ("/modifierAccount")
    public Account edit(@RequestBody Account account){
        return iAccountService.edit(account);}

    @DeleteMapping ("/deleteAccountbyid")
    public void deletebyid (@RequestParam int id){
        iAccountService.deleteById(id);}


    @DeleteMapping ("/deleteAccount")
    public void deletebyobject (@RequestBody Account account){
        iAccountService.delete(account);}
    @PostMapping("/deleteallAccount")
    public void deleteAll (List<Account>list) {
        iAccountService.deleteAll(list);
    }

}
