package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    //@Query(value = "SELECT u.* FROM user u join role r on u.role_id_role=r.id_role ",nativeQuery = true)
    //List<User> selectUsersByRoleType(Long idRole);

}
