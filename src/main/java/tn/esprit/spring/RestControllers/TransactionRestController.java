package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Transactions;
import tn.esprit.spring.Services.Interfaces.ITransactionService;

import java.util.List;

@RequestMapping("/Transaction")
@RestController
@AllArgsConstructor
public class TransactionRestController {
    private ITransactionService iTransactionService;

    @GetMapping("/afficherTransaction")
    public List<Transactions> afficher() {
        return iTransactionService.selectAll();
    }
    @PostMapping("/ajouterTransaction")
    public Transactions ajouter(@RequestBody Transactions transactions){
        return iTransactionService.add(transactions);

    }
    @GetMapping("/afficherTransactionAvecId/{id}")
    public Transactions afficherAccountAvecId(@PathVariable int id)
    {
        return iTransactionService.selectById(id);
    }
    @PostMapping("/ajouterallTransaction")

    public List<Transactions> addAll (List<Transactions>list){
        return iTransactionService.addAll(list);
    }
    @PutMapping ("/modifierTransaction")
    public Transactions edit(@RequestBody Transactions transaction){
        return iTransactionService.edit(transaction);}

    @DeleteMapping ("/deleteTransactionbyid")
    public void deletebyid (@RequestParam int id){
        iTransactionService.deleteById(id);}


    @DeleteMapping ("/deleteTransaction")
    public void deletebyobject (@RequestBody Transactions transaction){
        iTransactionService.delete(transaction);}
    @DeleteMapping ("/deleteallTransactions")
    public void deleteAll (List<Transactions>list) {
        iTransactionService.deleteAll(list);
    }
    @GetMapping("/retrieveTransactionWithRib")
    public List<Transactions>afficher2(@RequestParam long ribsource){
        return iTransactionService.selectByRibsource(ribsource);
    }

}

