package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Complaint;

import java.util.List;

public interface IComplaintService {
    Complaint add(Complaint c);
    Complaint edit(Complaint c);
    List<Complaint> selectAll();
    Complaint selectById(int idComplaint);
    void delete(Complaint c);
    void deleteById(int idComplaint);
    List<Complaint> addAll(List<Complaint> list);
    void deleteAll(List<Complaint> list);
}
