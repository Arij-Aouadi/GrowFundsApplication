package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Entities.ComplaintResponse;

import java.util.List;

public interface IComplaintResponseService {
    ComplaintResponse add(ComplaintResponse c);
    ComplaintResponse edit(ComplaintResponse c);
    List<ComplaintResponse> getAllByComplaint(Complaint c);
    void delete(Long id);
}
