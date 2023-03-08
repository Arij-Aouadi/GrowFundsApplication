package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    //select user selon username
    List<User> findByUserName(String userName);
    //select user where id =....
    List<User> findByCin(int cin);

    //Boolean existByUsername (String userName);
    //Boolean existByEmail (String email);
    boolean existsUserByUserName(String userName);
    boolean existsUserByEmail(String email);

}
