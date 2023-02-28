package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    //select user selon username
    Optional<User> findByUserName(String userName);
    //select user where id =....
    Optional<User> findByCin(int cin);

    //Boolean existByUsername (String userName);
    //Boolean existByEmail (String email);
}
