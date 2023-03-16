package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Complaint;

public interface ComplaintResponseRepository extends JpaRepository<Complaint,Integer> {
}
