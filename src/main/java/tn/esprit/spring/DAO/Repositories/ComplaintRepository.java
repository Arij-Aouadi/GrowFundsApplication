package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
}
