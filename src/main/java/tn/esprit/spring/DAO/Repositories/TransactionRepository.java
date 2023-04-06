package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Transactions;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
    //select all from Abonnement where typeAbonn=..
    List<Transactions> findByRibsource(long ribsource);
    @Query (value = "SELECT COUNT(*) from transactions", nativeQuery = true)
    int nombredetrans();


}
