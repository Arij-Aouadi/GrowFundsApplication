package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Transactions;
import tn.esprit.spring.Services.Interfaces.ITransactionService;

import javax.mail.MessagingException;
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
//    @PostMapping("/ajouterTransaction")
//    public Transactions ajouter(@RequestBody Transactions transactions){
//        return iTransactionService.addTransaction(transactions);

   // }
    @GetMapping("/afficherTransactionAvecId/{id}")
    public Transactions afficherTransactionAvecId(@PathVariable int id)
    {
        return iTransactionService.selectById(id);
    }
    //@PostMapping("/ajouterallTransaction")

    //public List<Transactions> addAll (List<Transactions>list){
     //   return iTransactionService.addAll(list);
    //}
    //@PutMapping ("/modifierTransaction")
    //public Transactions edit(@RequestBody Transactions transaction){
        //return iTransactionService.edit(transaction);}

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
//    @GetMapping("/retrieveTransactionWithRib")
//    public List<Transactions>afficher2(@RequestParam long ribsource){
//        return iTransactionService.selectByRibsource(ribsource);
//    }
    @GetMapping("/retrieveTransactionWithRibSource/{ribsource}")
    public List<Transactions>retrieveTransactionWithRibSource(@PathVariable int ribsource){
        return iTransactionService.selectByRibsource(ribsource);
    }
    @PostMapping("/testsendattachementemail")
    @ResponseBody
    public int sendAttachmentEmail(@RequestBody String mail ) throws MessagingException
    {
        int code = iTransactionService.sendAttachmentEmail(mail) ;
        return code ;
    }
    @PostMapping("/app-Transaction-ANGULAR")
    @ResponseBody
    public String approveTransaction(@RequestBody Transactions o, long code) throws MessagingException
    {
        String Transaction = iTransactionService.approveTransactionAng(o,code) ;
        return Transaction ;
    }
    @PostMapping("/ajouterTransaction")
    public int addTransaction(@RequestBody Transactions o ) throws MessagingException
    {
        int Transaction = iTransactionService.addTransaction(o) ;
        return Transaction ;
        // return iTransactionService.addTransaction(o) ;
    }


}

