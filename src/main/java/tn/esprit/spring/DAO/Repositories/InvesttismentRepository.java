package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.DAO.Entities.Projects;

import java.util.List;


public interface InvesttismentRepository extends JpaRepository<Investtisment,Integer> {


}
