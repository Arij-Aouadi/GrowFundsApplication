package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Role;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    //select user selon username
    Optional<User> findByUsername (String userName);
    //select user where id =....
   // List<User> findById(Long id);

    //Boolean existByUsername (String userName);
    //Boolean existByEmail (String email);
    boolean existsUserByUsername(String userName);
    boolean existsUserByEmail(String email);


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

    @Query(value ="SELECT u.* FROM user u JOIN role r on u.role_id_role=r.id_role WHERE r.id_role=1" ,nativeQuery = true)
    List<User> findAgents();
    @Query(value ="SELECT u.* FROM user u JOIN role r on u.role_id_role=r.id_role WHERE r.id_role=2" ,nativeQuery = true)
    List<User> findAdmins();
    @Query(value ="SELECT u.* FROM user u JOIN role r on u.role_id_role=r.id_role WHERE r.id_role=3" ,nativeQuery = true)
    List<User> findClients();
@Query(value = "SELECT r.* FROM user u JOIN role r on u.role_id_role=r.id_role WHERE u.id=?1", nativeQuery = true)
Set<Role> getRoleByIdd(Long id);
    @Query(value = "insert into r values (?1)", nativeQuery = true)
    void insert(float prix);
}
