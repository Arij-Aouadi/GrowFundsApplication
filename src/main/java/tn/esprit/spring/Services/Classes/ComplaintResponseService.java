package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Entities.ComplaintResponse;
import tn.esprit.spring.DAO.Repositories.ComplaintResponseRepository;
import tn.esprit.spring.Services.Interfaces.IComplaintResponseService;

import java.util.List;

@Service
@AllArgsConstructor

public class ComplaintResponseService implements IComplaintResponseService  {
    @Autowired
    ComplaintResponseRepository complaintResponseRepository;
    @Override
    public ComplaintResponse add(ComplaintResponse c) {
        return complaintResponseRepository.save(c);
    }

    @Override
    public ComplaintResponse edit(ComplaintResponse c) {
        return complaintResponseRepository.save(c);
    }

    @Override
    public List<ComplaintResponse> getAllByComplaint(Complaint c) {
        return complaintResponseRepository.getResponsesByComplaint(c.getIdComplaint());
    }

    @Override
    public Complaint delete(Long id){

        Complaint c = complaintResponseRepository.findById(id).get().getComplaint();
        complaintResponseRepository.deleteById(id);
        return c;
    }


}
