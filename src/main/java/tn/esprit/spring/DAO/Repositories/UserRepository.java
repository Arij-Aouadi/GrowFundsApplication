package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByCin(int cin);
}
