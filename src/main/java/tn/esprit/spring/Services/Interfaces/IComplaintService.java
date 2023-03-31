package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Complaint;

import java.util.List;

public interface IComplaintService {
    Complaint add(Complaint c);
    Complaint edit(Complaint c);
    List<Complaint> getAll();
    Complaint selectById(Long idComplaint);
    void delete(Complaint c);
    void deleteById(Long idComplaint);
    List<Complaint> getComplaintsByClient(Long idClient);

}
