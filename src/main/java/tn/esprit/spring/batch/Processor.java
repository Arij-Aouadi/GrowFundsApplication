/* package tn.esprit.spring.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.TypeAccount;
import tn.esprit.spring.DAO.Entities.Typetrans;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<Account, Account> {

    private static final Map<String, Boolean> AccStatus =
            new HashMap<>();
    private static final Map<String, TypeAccount> AccType =
            new HashMap<>();

    public Processor() {
        AccStatus.put("actif", true);
        AccStatus.put("nonActif", false);

        AccType.put("1", TypeAccount.NORMALACCOUNT);
        AccType.put("2", TypeAccount.SAVINGACCOUNT);


    }
    @Override
    public Account process(Account item) throws Exception {


		//boolean status=item.getState() ;
		//boolean accStatus=AccStatus.get(item.getState());
		//item.setState(accStatus);
		  //System.out.println(String.format("Converted from [%b] to [%b]", status, accStatus));
		//item.setOpenDate(new Date());
		//Typeaccount type= item.getTypeAccount();
		//Typeaccount Type=AccType.get(type);
		//item.setTypeAccount(Type);



        return item;
    }

}*/


