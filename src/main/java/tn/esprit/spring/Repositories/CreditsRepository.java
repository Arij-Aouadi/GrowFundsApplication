package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Credits;

public interface CreditsRepository extends JpaRepository<Credits,Integer> {
}
