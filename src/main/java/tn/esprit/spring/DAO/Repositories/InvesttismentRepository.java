package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Investisment;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;


public interface InvesttismentRepository extends JpaRepository<Investisment,Integer> {

    List<Investisment> findAllByInvestor(User u);
}
