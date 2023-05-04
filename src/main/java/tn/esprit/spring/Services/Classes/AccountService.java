package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import tn.esprit.spring.Services.Interfaces.IAccountService;

import java.util.List;
@AllArgsConstructor //injection par constructeur
@Service

public class AccountService implements IAccountService {
    private AccountRepository accountRepository ;

    @Override
    public Account add(Account a) { return accountRepository.save(a);


    }

    @Override
    public void alimenteAcc(long rib, float amount) {
        Account c = accountRepository.findByRib(rib);
        c.setSolde(c.getSolde()+amount);
        accountRepository.save(c);

    }

    @Override
    public Account edit(Account a) {
//        Account  account = accountRepository.findByRib(rib);
//        account.setSolde(a.getSolde());
//        account.setCin(a.getCin());
//        account.setState(a.getState());
//        account.setTypeAcc(a.getTypeAcc());
//        account.setAccountNum(a.getAccountNum());
//        accountRepository.save(account) ;
//        return a;
        return accountRepository.save(a);

    }

    @Override
    public Account update(Account a, int num) {
        Account  account = accountRepository.findByAccountNum(num);
        account.setSolde(a.getSolde());
        account.setCin(a.getCin());
        account.setState(a.getState());
        account.setTypeAcc(a.getTypeAcc());
        account.setAccountNum(a.getAccountNum());
        accountRepository.save(account) ;
        return a;
    }

    @Override
    public List<Account> selectAll() { return (List<Account>)accountRepository.findAll();

    }

    @Override
    public Account selectById(int idAccount) { return accountRepository.findById(idAccount).get();

    }


    @Override
    public void deleteById(int idAccount) {
        accountRepository.deleteById(idAccount);

    }

    @Override
    public void delete(Account a) {
        accountRepository.delete(a);

    }

    @Override
    public List<Account> addAll(List<Account> list) {
        return accountRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Account> list) {
        accountRepository.deleteAll(list);

    }
    /*@Override
    public Account updateAccount(Account u , int num ) { Account  account = accountRepository.findById(num).get();
        accountRepository.save(account) ;
        return u;

    }*/
    @Override
    public List<Account> getAccountsByClient(Long idClient) {
        return accountRepository.getAccountByClient(idClient);
    }


}
