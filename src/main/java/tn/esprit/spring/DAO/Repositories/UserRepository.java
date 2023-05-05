package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    //select user selon username
    Optional<User> findByUsername (String userName);

    //select user where id =....
   // List<User> findById(Long id);

    //Boolean existByUsername (String userName);
    //Boolean existByEmail (String email);
    boolean existsUserByUsername(String userName);
    boolean existsUserByEmail(String email);

    User findUserById(long id);

    @Query(value = " SELECT * from user u join account a on a.user_id=u.id JOIN credits c ON a.account_num=c.account_account_num WHERE c.id_credit=?1", nativeQuery = true)
    User returnUserofCredit(int idCredit);

    //@Query(value = "SELECT u.* FROM user u join role r on u.role_id_role=r.id_role ",nativeQuery = true)
    //List<User> selectUsersByRoleType(Long idRole);

    //----Sarra---
    @Query (value = "SELECT email from User u join Account a on u.cin = a.user_id where account_num=:num", nativeQuery = true)
    String retrieveEmailByAccounNum(@Param("num") int num );
//    @Query (value = "SELECT username from User u join Account a on u.cin = a.user_id where a.rib=:num", nativeQuery = true)
//    String retrieveUsernameByAccounNum(@Param("num") long num );
//    @Query (value = "SELECT username from User u  where u.cin=:num", nativeQuery = true)
//    String retrieveUsernameBycin(@Param("num") String num );
//    @Query (value = "SELECT cin from User u join Account a on u.cin = a.user_id where a.rib=:num", nativeQuery = true)
//    String retrievecinByAccounNum(@Param("num") long num );
//
//    @Query (value = "SELECT profession from User u join Account a on u.cin = a.user_id where a.rib=:num", nativeQuery = true)
//    String retrieveProfessionByAccounNum(@Param("num") long num );



}
