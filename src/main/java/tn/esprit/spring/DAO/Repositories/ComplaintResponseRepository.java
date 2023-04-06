package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Entities.ComplaintResponse;

import java.util.List;

public interface ComplaintResponseRepository extends JpaRepository<ComplaintResponse,Long> {

    @Query("SELECT  cr FROM ComplaintResponse cr  WHERE cr.complaint.idComplaint=:id")
    List<ComplaintResponse> getResponsesByComplaint(@Param("id") Long id);
}
