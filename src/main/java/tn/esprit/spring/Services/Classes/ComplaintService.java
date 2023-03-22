package tn.esprit.spring.Services.Classes;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Repositories.ComplaintRepository;
import tn.esprit.spring.Services.Interfaces.IComplaintService;

import java.util.List;

@Service
@AllArgsConstructor
public class ComplaintService implements IComplaintService {
    private ComplaintRepository complaintRepository;

    @Override
    public Complaint add(Complaint c) {
        return complaintRepository.save(c);
    }

    @Override
    public Complaint edit(Complaint c) {
        return complaintRepository.save(c);
    }

    @Override
    public List<Complaint> getAll() {
        return complaintRepository.findAll();
    }


    @Override
    public Complaint selectById(int idComplaint) {
        return complaintRepository.findById(idComplaint).get();
    }

    @Override
    public void delete(Complaint c) {
        complaintRepository.delete(c);
    }

    @Override
    public void deleteById(int idComplaint) {
        complaintRepository.deleteById(idComplaint);
    }

    @Override
    public List<Complaint> getComplaintsByClient(int idClient) {
        return complaintRepository.getComplaintByClient(idClient);
    }

}
