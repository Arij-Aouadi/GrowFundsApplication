/*package tn.esprit.spring.bach;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import java.util.List;
@Component
public class DbWriter implements ItemWriter<Account> {

    @Autowired
    private AccountRepository AccountRepository;

    @Override
    public void write(List<? extends Account> Accounts) throws Exception{
        System.out.println("Data Saved for Accounts: " + Accounts);
        AccountRepository.saveAll(Accounts);
    }

}*/
