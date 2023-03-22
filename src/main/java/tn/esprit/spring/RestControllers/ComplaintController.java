package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Entities.ComplaintResponse;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.Services.Interfaces.IComplaintResponseService;
import tn.esprit.spring.Services.Interfaces.IComplaintService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ComplaintController {
    private IComplaintService iComplaintService;
    private IComplaintResponseService iComplaintResponseService;

    //Admin
    @GetMapping("showAllComplaint")
    public List<Complaint> showAllComplaint(){
        List<Complaint> complaints = iComplaintService.getAll();
        return complaints;
    }

    @PutMapping("EditComplaint")
    public Complaint updateComplaint(@RequestBody Complaint complaint){
        return iComplaintService.edit(complaint);
    }

    //Both
    @GetMapping("showComplaint/{id}")
    public Complaint showComplaint(@PathVariable int id){
        return iComplaintService.selectById(id);
    }
    @PostMapping("addResponse")
    public ComplaintResponse addComplaintResponse(@RequestBody ComplaintResponse complaintResponse){
        return iComplaintResponseService.add(complaintResponse);
    }

    //USER
    @PostMapping("addComplaint")
    public Complaint addComplaint(@RequestBody Complaint complaint){
        return iComplaintService.add(complaint);
    }

    @GetMapping("showAllUserComplaint/{id}")
    public List<Complaint> showAllUserComplaint(@PathVariable int id){
        List<Complaint> complaints = iComplaintService.getComplaintsByClient(id);
        return complaints;
    }

    @DeleteMapping("/deleteComplaint")
    public  void deleteComplaint(@RequestBody Complaint complaint){
        iComplaintService.delete(complaint);
    }




}
