package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Entities.TypeAccount;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import tn.esprit.spring.Services.Classes.UserService;
import tn.esprit.spring.Services.Interfaces.IAccountService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")


public class AccountRestControllers {
    UserService auth;
    private IAccountService iAccountService;


    @GetMapping("/admin/accounts/Retrieve_All_Accounts")
    public List<Account> afficher() {

        List <Account> list= iAccountService.selectAll();
        return list ;
    }
    @GetMapping("/admin/accounts/c/{id}")
    public Account afficherAccountAvecId(@PathVariable int id)
    {
        return iAccountService.selectById(id);

    }
    @PostMapping("/admin/account/{rib}/{amount}")
    @ResponseBody
    public void alimenter (@PathVariable ("rib")  long rib ,@PathVariable ("amount")  float amount)
    {
        iAccountService.alimenteAcc(rib,amount) ;
    }

    @PostMapping("/admin/accounts/add")
    public Account ajouter(@RequestBody Account account){
    return iAccountService.add(account);
//    public Account ajouter(@RequestParam int num, @RequestParam int cin, @RequestParam long rib, @RequestParam TypeAccount type, @RequestParam float solde,@RequestParam boolean status){
//      Account account=new Account();
//      User connectedUser = auth.getConnectedUser();
//      account.setUser(connectedUser);
//      account.setAccountNum(num);
//      account.setCin(cin);
//      account.setRib(rib);
//      account.setTypeAcc(type);
//      account.setSolde(solde);
//      account.setDate(new Date());
//      account.setState(status);
//        return iAccountService.add(account);

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
     @PutMapping ("/admin/accounts/edit/{id}")
     public Account update(@RequestBody Account a, @RequestParam int rib){
        return iAccountService.update(a,rib);
     }
    @GetMapping("/client/accounts")
    public List<Account> showAllAccountComplaint() {
        User connectedUser = auth.getConnectedUser();
        List<Account> accounts = iAccountService.getAccountsByClient(connectedUser.getId());
        return accounts;
    }



    @DeleteMapping ("/admin/account/delete/{id}")
    public void deletebyid (@PathVariable int id){
        iAccountService.deleteById(id);}
    @GetMapping("/client/accounts/c/{id}")
    public Account showUserAccount(@PathVariable int id) {
        User connectedUser = auth.getConnectedUser();
        Account c = iAccountService.selectById(id);

        if (c.getUser().getId() == connectedUser.getId()) return c;
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account Not Found!"
            );
        }

    }


//    @DeleteMapping ("/DeleteAccount")
//    public void deletebyobject (@RequestBody Account account){
//        iAccountService.delete(account);}
    @PostMapping("/Delete_AllAccounts")
    public void deleteAll (List<Account>list) {
        iAccountService.deleteAll(list);
    }

}
