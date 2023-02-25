package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Investtisment;

public interface InvesttismentRepository extends JpaRepository<Investtisment,Integer> {
}
