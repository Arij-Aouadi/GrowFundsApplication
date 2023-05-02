package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue,Integer> {
}
