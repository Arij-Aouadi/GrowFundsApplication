package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Credits;

import java.util.List;

public interface CreditsRepository extends JpaRepository<Credits,Integer> {
    List<Credits> findCreditsByStatus (String status);

    Credits findCreditsByIdCredit(int idCredit);
    Credits findCreditsByAccount_AccountNum(int numAccount);
    boolean existsCreditsByAccount_AccountNum (int numAccount);
    List<Credits> findCreditsByStatusAndAccount_AccountNum(String status,int numAccount);
}
