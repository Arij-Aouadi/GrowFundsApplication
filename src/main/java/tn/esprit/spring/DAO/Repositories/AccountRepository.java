package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Account;

public interface AccountRepository extends JpaRepository<Account,Integer> {

}