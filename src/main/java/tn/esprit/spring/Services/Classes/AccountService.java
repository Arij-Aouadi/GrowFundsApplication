package tn.esprit.spring.Services.Classes;

import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Account;

import java.util.List;AllArgsConstructor //injection par constructeur
@Service

public class AccountService implements IAccountService {
    private AccountRepository accountRepository ;

    @Override
    public Account add(Account a) { return accountRepository.save(a);


    }

    @Override
    public Account edit(Account a) { return accountRepository.save(a);

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
}
