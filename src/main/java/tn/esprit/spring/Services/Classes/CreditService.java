package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.CreditsRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class CreditService implements tn.esprit.spring.Services.Interfaces.ICreditService {


    private CreditsRepository creditsRepository;
    private UserRepository userRepository;
    @Override
    public Credits add(Credits c){
        return  creditsRepository.save(c);
    }
    /*public Credits add( Credits c) {
        User guarant = userRepository.findUserByCin(c.getGuarant().getCin());

        //Debt to income ratio must be 36% or less to be considered good
        //Stability years must be 2 or more to be considered stable

        if ( c.getGuarant().getSalary() <= (float) (0.36 * c.getAmount()) && guarant.getStabilityYears()>=2 )
        {
            // if the client has a credit history
            //
            if (creditsRepository.findCreditsByStatusAndAccount_AccountNum("completed",c.getAccount().getAccountNum()).size()>0)
            {
                    //calcul du retard
            }
            //client has no history of getting a credit therefore his ability to pay can't be predicted
            //Admmin can choose to impose a higher interest rate
            else {
                c.setJudgment("Client has no credit history, impose higher rate. ");
            }
            c.setStatus("Pending");

        }
        else {
            c.setStatus("Denied");
            c.setJudgment(" Invalid guarantor : guarantor's salary cannot cover the debt or guarantor's employment is not stable ");
        }

        return creditsRepository.save(c);

    }*/

    @Override
    public Credits edit(Credits c) {
        return creditsRepository.save(c);
    }

    @Override
    public List<Credits> selectAll() {
        return creditsRepository.findAll();
    }

    @Override
    public Credits selectById(int idCredit) {
        return creditsRepository.findById(idCredit).get();
    }

    @Override
    public void delete(Credits c) {
        creditsRepository.delete(c);

    }

    @Override
    public void deleteById(int idCredit) {
        creditsRepository.deleteById(idCredit);
    }

    @Override
    public List<Credits> addAll(List<Credits> list) {
        return creditsRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Credits> list) {
        creditsRepository.deleteAll(list);
    }

    @Override
    public List<Credits> GetCreditsByStatus(String status) {
        return creditsRepository.findCreditsByStatus(status);
    }

    @Override
    public boolean CreditExists(int accountNum) {
        return creditsRepository.existsCreditsByAccount_AccountNum(accountNum);
    }

    @Override
    public void predict(Credits c) {

    }
}
