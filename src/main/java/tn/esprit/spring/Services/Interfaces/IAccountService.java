package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Account;

import java.util.List;

public interface IAccountService {
    Account add(Account a);
    void alimenteAcc ( long rib , float amount ) ;
    Account edit(Account a);
    Account update(Account a ,long rib);
    List<Account> selectAll();
    Account selectById(int idAccount);
    void deleteById(int idAccount);
    void delete(Account a);
    List<Account> addAll(List<Account> list);
    void deleteAll(List<Account> list);
    Account assignCreditToAccount(int idCredit, int accountNum);
}
