package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Transactions;

import javax.mail.MessagingException;
import java.util.List;

public interface ITransactionService {
    //Transactions add(Transactions t);
    //Transactions edit(Transactions t);
    List<Transactions> selectAll();
    Transactions selectById(int idTransactions);
    void deleteById(int idTransactions);
    void delete(Transactions t );
    //List<Transactions> addAll(List<Transactions> list);
    void deleteAll(List<Transactions> list);
    List<Transactions> selectByRibsource(long ribsource);
    //Transactions addTransaction(Transactions s );
    int  addTransaction(Transactions s ) throws MessagingException;
    //String  approveTransaction(Transactions s) throws MessagingException;
    int sendAttachmentEmail(String ReciverEmail) throws MessagingException;

    String  approveTransactionAng(Transactions s, Long code ) throws MessagingException;

}