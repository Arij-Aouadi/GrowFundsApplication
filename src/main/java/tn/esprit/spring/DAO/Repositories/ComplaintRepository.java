package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Complaint;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    @Query("SELECT  c FROM Complaint c  WHERE c.user.id=:id")
    List<Complaint> getComplaintByClient(@Param("id") Long id);
}
