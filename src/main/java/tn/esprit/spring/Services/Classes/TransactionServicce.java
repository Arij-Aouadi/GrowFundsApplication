package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.Transactions;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import tn.esprit.spring.DAO.Repositories.TransactionRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IAccountService;
import tn.esprit.spring.Services.Interfaces.ITransactionService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.List;

@AllArgsConstructor //injection par constructeur
@Service

public class TransactionServicce implements ITransactionService {
    private TransactionRepository transactionRepository ;
    private IAccountService iAccountService;
    private AccountRepository accountRepository;
    public JavaMailSender emailSender;
    private UserRepository userRepository;




   // @Override
    //public Transactions edit(Transactions t) {
        //return transactionRepository.save(t);
    //}

    @Override
    public List<Transactions> selectAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transactions selectById(int idTransactions) {
        return transactionRepository.findById(idTransactions).get();

    }

    @Override
    public void deleteById(int idTransactions) {
        transactionRepository.deleteById(idTransactions);
    }

    @Override
    public void delete(Transactions t) {
        transactionRepository.delete(t);

    }

    //@Override
    //public List<Transactions> addAll(List<Transactions> list) {
       // return transactionRepository.saveAll(list);
    //}
    //************************Supprimer toutes les Transaction****************
    @Override
    public void deleteAll(List<Transactions> list) {
        transactionRepository.deleteAll(list);}



    //***************************** Retourner les transactions liee a un Rib Donnee ************
    @Override
    public List<Transactions> selectByRibsource(long ribsource) {
        return transactionRepository.findByRibsource(ribsource);
    }

    @Override
    public int addTransaction(Transactions s) throws MessagingException{
        int code_tr = 0;
        //Account acc_emet = accountRepository.findById((s.getRibsource())).orElse(null);
        //Account acc_dest = accountRepository.findById(s.getRibrecipient()).orElse(null);
        for (Account account :  iAccountService.selectAll()) {
            //if (s.getAmount() < account.getSolde()) {
            if (account.getRib() == s.getRibsource()) {
                //accountRepository.findByAccountNum(account.getAccountNum()).getEmail()
                String mail=userRepository.retrieveEmailByAccounNum(account.getAccountNum());


                code_tr = sendAttachmentEmail(mail);
                //this.code=code_tr;
                //account.getUser().getEmail()
                float sold = account.getSolde();
                account.setSolde(sold - s.getAmount());
                iAccountService.edit(account);
                transactionRepository.save(s);

                //accountRepository.save(account);
            } else if (account.getRib() == s.getRibrecipient()) {
                float a = account.getSolde();
                account.setSolde(a + s.getAmount());
                iAccountService.edit(account);
                transactionRepository.save(s);

                //accountRepository.save(account);
                //}
                //}
                //}

                //transactionRepository.save(s);
                //return code_tr ;
                //*********************************************************************

            }
        }
        // }

        // }

        return code_tr;
    }

    @Override
    public int sendAttachmentEmail(String ReciverEmail) throws MessagingException {

            MimeMessage message = emailSender.createMimeMessage();

            boolean multipart = true;

            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
            int max = 999999;
            int min = 9999;
            SecureRandom secureRandom = new SecureRandom();
            int randomWithSecureRandomWithinARange = secureRandom.nextInt(max - min) + min;

            String htmlMsg = "<h3>Validate this Transaction by Using this number  </h3>"
                    + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>"
                    + randomWithSecureRandomWithinARange;

            message.setContent(htmlMsg, "text/html");
            helper.setTo(ReciverEmail);
            helper.setSubject("GrowFunds Transaction endorsment ");
            this.emailSender.send(message);

            //int code=  randomWithSecureRandomWithinARange ;
            //System.err.println(code);
            //return code ;
            return randomWithSecureRandomWithinARange;
        }


    @Override
    public String approveTransactionAng(Transactions s, Long code) throws MessagingException {
        if(addTransaction(s)==code)
        {
            transactionRepository.save(s);
            return "transaction approuvée " ;
        }
        else
        {
            return "Transaction non approuvée" ;
        }
    }
//    public Transactions addTransaction(Transactions s) {
//        Long source = s.getRibsource();
//        Long des = s.getRibrecipient();
//        List<Account> accountList = iAccountService.selectAll();
//        for (Account account : accountList)
//        {
//            if (account.getRib()==source) {
//                float sold = account.getSolde();
//                account.setSolde(sold -s.getAmount());
//                iAccountService.add(account);}
//
//            else if (account.getRib()==des) {
//                float a = account.getSolde();
//
//                account.setSolde(a+s.getAmount());
//                iAccountService.edit(account);}
//        }
//
//        return transactionRepository.save(s);
//    }


}