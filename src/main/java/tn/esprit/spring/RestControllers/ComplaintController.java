package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.Services.Classes.UserService;
import tn.esprit.spring.Services.Interfaces.IComplaintResponseService;
import tn.esprit.spring.Services.Interfaces.IComplaintService;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class ComplaintController {

    @Autowired
    UserService auth;
    private IComplaintService iComplaintService;
    private IComplaintResponseService iComplaintResponseService;


    @GetMapping("/admin/complaints")
    public List<Complaint> showAllComplaint(){
        List<Complaint> complaints = iComplaintService.getAll();
        return complaints;
    }


    @GetMapping("/admin/complaints/c/{id}")
    public Complaint showComplaint(@PathVariable Long id){
        return iComplaintService.selectById(id);
    }



    @GetMapping("/complaints/")
    public List<Complaint> showAllUserComplaint(){
        User connectedUser = auth.getConnectedUser();
        List<Complaint> complaints = iComplaintService.getComplaintsByClient(connectedUser.getId());
        return complaints;
    }

    @GetMapping("complaints/c/{id}")
    public Complaint showUserComplaint(@PathVariable Long id){
        User connectedUser = auth.getConnectedUser();
        Complaint c =iComplaintService.selectById(id);

        if( c.getUser().getId()==connectedUser.getId())return c;
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Complaint Not Found!"
            );
        }

    }

    @PostMapping("complaints/add")
    public Complaint addComplaint(@RequestParam String objet , @RequestParam String description){
        Complaint c = new Complaint();
        User connectedUser = auth.getConnectedUser();
        c.setUser(connectedUser);
        c.setDateComplaint(new Date());
        c.setObjet(objet);
        c.setDescription(description);
        c.setPriorityLevel(TypePriorityLevel.LOW);
        c.setStatus(TypeComplaintStatus.NEW);
        return iComplaintService.add(c);
    }





    @PutMapping("/admin/complaints/edit/{id}")
    public Complaint updateComplaint(@RequestParam Long id,@RequestParam String priority, @RequestParam String status){
        Complaint c = iComplaintService.selectById(id);
        c.setPriorityLevel(TypePriorityLevel.valueOf(priority));
        c.setStatus(TypeComplaintStatus.valueOf(status));
        return iComplaintService.edit(c);
    }

    @DeleteMapping("/admin/complaints/delete/{id}")
    public  void deleteComplaint(@PathVariable Long id){
        iComplaintService.deleteById(id);
    }


    @PostMapping("/admin/complaints/c/{id}/addResponse")
    public ComplaintResponse addAgentResponse(@PathVariable Long id,@RequestParam String description ){
        Complaint c = iComplaintService.selectById(id);
        ComplaintResponse cr = new ComplaintResponse();
        User connectedUser = auth.getConnectedUser();
        if (c.getStatus()!=TypeComplaintStatus.RESOLVED && c.getStatus()!=TypeComplaintStatus.CLOSED  ){
            cr.setDescription(description);
            cr.setDateResponse(new Date());
            cr.setComplaint(c);
            cr.setUser(connectedUser);
            return iComplaintResponseService.add(cr);
        }else{
            return null;
        }

    }


    @PostMapping("/complaints/c/{id}/addResponse")
    public ComplaintResponse addClientResponse(@PathVariable Long id,@RequestParam String description ){
        Complaint c = iComplaintService.selectById(id);
        ComplaintResponse cr = new ComplaintResponse();
        User connectedUser = auth.getConnectedUser();
        if (c.getStatus()!=TypeComplaintStatus.RESOLVED && c.getStatus()!=TypeComplaintStatus.CLOSED && c.getUser()==connectedUser ){
            cr.setDescription(description);
            cr.setDateResponse(new Date());
            cr.setComplaint(c);
            cr.setUser(connectedUser);
            return iComplaintResponseService.add(cr);
        }else{
            return null;
        }

    }

    @DeleteMapping("/admin/responses/delete/{id}")
    public  void deleteResponse(@PathVariable Long id){
        iComplaintResponseService.delete(id);
    }
}
