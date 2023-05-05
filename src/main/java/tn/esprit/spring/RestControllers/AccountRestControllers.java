package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import tn.esprit.spring.Services.Interfaces.IAccountService;

import java.util.List;

@RestController
@AllArgsConstructor

public class AccountRestControllers {
    private IAccountService iAccountService;


    @GetMapping("/Retrieve_All_Accounts")
    public List<Account> afficher() {

        List <Account> list= iAccountService.selectAll();
        return list ;
    }
    @GetMapping("/Retrieve_Account_ById/{id}")
    public Account afficherAccountAvecId(@PathVariable int id)
    {
        return iAccountService.selectById(id);

    }
    @PostMapping("/alimente-acc/{rib}/{amount}")
    @ResponseBody
    public void alimenter (@PathVariable ("rib")  long rib ,@PathVariable ("amount")  float amount)
    {
        iAccountService.alimenteAcc(rib,amount) ;
    }

    @PostMapping("/Add_Account")
    public Account ajouter(@RequestBody Account account){
        return iAccountService.add(account);

    }
//    @PostMapping("/ajouterallAccount")
//
//    public List<Account> addAll (List<Account>list){
//        return iAccountService.addAll(list);
//    }
//    @PutMapping ("/Update_Account")
//    public Account edit(@RequestBody Account account){
//
//        return iAccountService.edit(account);}
     @PutMapping ("/Update_Account")
     public Account update(@RequestBody Account a, @RequestParam long rib){
        return iAccountService.update(a,rib);
     }



    @DeleteMapping ("/DeleteAccount/{idacc}")
    public void deletebyid (@RequestParam int id){
        iAccountService.deleteById(id);}


//    @DeleteMapping ("/DeleteAccount")
//    public void deletebyobject (@RequestBody Account account){
//        iAccountService.delete(account);}
    @PostMapping("/Delete_AllAccounts")
    public void deleteAll (List<Account>list) {
        iAccountService.deleteAll(list);
    }
    @GetMapping("/assignCreditToAccount/{id}/{num}")
    public Account assignSkierToPiste(@PathVariable int id,@PathVariable int num){
        return iAccountService.assignCreditToAccount(id,num);
    }

}
