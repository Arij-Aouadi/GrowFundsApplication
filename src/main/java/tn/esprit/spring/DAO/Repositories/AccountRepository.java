package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.User;

public interface AccountRepository extends JpaRepository<Account,Integer> {
   Account findByAccountNum(int numAcc);

}