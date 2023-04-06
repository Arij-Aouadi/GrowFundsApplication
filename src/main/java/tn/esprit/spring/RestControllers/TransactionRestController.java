package tn.esprit.spring.RestControllers;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Transactions;
import tn.esprit.spring.Services.Classes.PDFGenerator;
import tn.esprit.spring.Services.Classes.TransactionPdfExporter;
import tn.esprit.spring.Services.Interfaces.ITransactionService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//    @GetMapping("/pdf/students")
//    public void generatePdf(HttpServletResponse response, long rib) throws DocumentException, IOException {
//
//        response.setContentType("application/pdf");
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
//        String currentDateTime = dateFormat.format(new Date());
//        String headerkey = "Content-Disposition";
//        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
//        response.setHeader(headerkey, headervalue);
//
//        List<Transactions> transactionList = iTransactionService.selectByRibsource(rib);
//
//        PDFGenerator generator = new PDFGenerator();
//        //generator.set;
//        generator.generate(response);
//
//    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response , @RequestBody long rib) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=BankingIdentity_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        //Account acc= AccSercice.retrieveAccount(c.getRib());
        TransactionPdfExporter exporter = new TransactionPdfExporter(rib);
        exporter.export(response);
    }

    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response ,@RequestParam long rib) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=Transactions" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Transactions > listofStudents = iTransactionService.selectByRibsource(rib);
        PDFGenerator generator = new PDFGenerator();
        generator.generate(listofStudents, response);
    }



}

