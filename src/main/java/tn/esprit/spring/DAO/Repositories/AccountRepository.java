package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.User;

public interface AccountRepository extends JpaRepository<Account,Integer> {
   Account findByAccountNum(int numAcc);

   Account findAccountByAccountNum(int numAccount);
   Account findByRib(long rib);
//   @Query(value = "SELECT account_num from Account a join Transactions t on a.account_num = t.account_account_num where t.ribsource=:rib", nativeQuery = true)
//   int retrieveAccount_numByAccounNum(@Param("rib") int rib );
//   @Query(value = "SELECT cin from Account a where a.ribsource=:rib", nativeQuery = true)
//   int retrieveCinByRib(@Param("rib") int rib );

}