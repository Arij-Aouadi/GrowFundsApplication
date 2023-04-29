package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.DAO.Entities.*;
import tn.esprit.spring.Services.Classes.ComplaintService;
import tn.esprit.spring.Services.Classes.UserService;
import tn.esprit.spring.Services.Interfaces.IChatgptService;
import tn.esprit.spring.Services.Interfaces.IComplaintResponseService;
import tn.esprit.spring.Services.Interfaces.IComplaintService;
import tn.esprit.spring.openai.OutputDto;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class ComplaintController {

    @Autowired
    UserService auth;
    private IComplaintService iComplaintService;
    private IComplaintResponseService iComplaintResponseService;


    @Autowired
    private IChatgptService chatgptService;


    @GetMapping("/admin/complaints")
    public List<Complaint> showAllComplaint() {
        List<Complaint> complaints = iComplaintService.getAll();
        return complaints;
    }


    @GetMapping("/admin/complaints/c/{id}")
    public Complaint showComplaint(@PathVariable Long id) {
        return iComplaintService.selectById(id);
    }


    @GetMapping("/client/complaints")
    public List<Complaint> showAllUserComplaint() {
        User connectedUser = auth.getConnectedUser();
        List<Complaint> complaints = iComplaintService.getComplaintsByClient(connectedUser.getId());
        return complaints;
    }

    @GetMapping("/client/complaints/c/{id}")
    public Complaint showUserComplaint(@PathVariable Long id) {
        User connectedUser = auth.getConnectedUser();
        Complaint c = iComplaintService.selectById(id);

        if (c.getUser().getId() == connectedUser.getId()) return c;
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Complaint Not Found!"
            );
        }

    }

    @PostMapping("/client/complaints/add")
    public Complaint addComplaint(@RequestBody Complaint c) {
        User connectedUser = auth.getConnectedUser();
        c.setUser(connectedUser);
        c.setDateComplaint(new Date());
        c.setPriorityLevel(TypePriorityLevel.LOW);
        c.setStatus(TypeComplaintStatus.NEW);
        return iComplaintService.add(c);
    }


    @PutMapping("/admin/complaints/edit/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody Complaint c) {
        return iComplaintService.edit(c);
    }

    @DeleteMapping("/admin/complaints/delete/{id}")
    public void deleteComplaint(@PathVariable Long id) {

         iComplaintService.deleteById(id);
    }



    @PostMapping("/admin/complaints/c/{id}/addResponse")
    public Complaint addAgentResponse(@PathVariable Long id, @RequestBody ComplaintResponse cr) {

        cr.setDateResponse(new Date());
        cr.setComplaint(iComplaintService.selectById(id));
        User connectedUser = auth.getConnectedUser();
        cr.setUser(connectedUser);

         iComplaintResponseService.add(cr);
         return cr.getComplaint();
    }


    @PostMapping("/client/complaints/c/{id}/addResponse")
    public Complaint addClientResponse(@PathVariable Long id,  @RequestBody ComplaintResponse cr) {
        cr.setDateResponse(new Date());
        cr.setComplaint(iComplaintService.selectById(id));
        User connectedUser = auth.getConnectedUser();
        cr.setUser(connectedUser);

        iComplaintResponseService.add(cr);
        return cr.getComplaint();

    }

    @DeleteMapping("/admin/responses/delete/{id}")
    public Complaint deleteResponse(@PathVariable Long id) {

        return iComplaintResponseService.delete(id);
    }


    @GetMapping("/admin/complaints/c/{id}/summarize")
    public String summarizeComplaint(@PathVariable Long id) {
        Complaint c = iComplaintService.selectById(id);
        OutputDto out = null;
        try {
            out = chatgptService.sendPrompt("this is a complain try to summarize it in its languague : " + c.getDescription());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return out.getAnswer();
    }


    @GetMapping("/admin/complaints/c/{id}/translate")
    public String translateComplaint(@PathVariable Long id) {
        Complaint c = iComplaintService.selectById(id);
        OutputDto out = null;
        try {
            out = chatgptService.sendPrompt("translate this to english : " + c.getDescription());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return out.getAnswer();
    }


    @GetMapping("/admin/complaints/priority/{id}")
    public ResponseEntity<String> pickPriority(@PathVariable Long id) {
        Complaint c = iComplaintService.selectById(id);
        OutputDto out = null;
        try {
            out = chatgptService.sendPrompt("We have a complaint from a customer of our bank." +
                    " We need you to choose one of these priority levels (LOW, MEDIUM, HIGH)" +
                    " depending on the situation detailed in the complaint. " +
                    " This priority level will help our team to solve the most urgent and critic complaint first: " + c.getDescription());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String answer = out.getAnswer();
        if (answer.toUpperCase().contains("LOW")){
            c.setPriorityLevel(TypePriorityLevel.LOW);
        }else if (answer.toUpperCase().contains("MEDIUM")){
            c.setPriorityLevel(TypePriorityLevel.MEDIUM);
        }else if (answer.toUpperCase().contains("LOW")){
            c.setPriorityLevel(TypePriorityLevel.HIGH);
        }
        //iComplaintService.edit(c);
        return ResponseEntity.ok(out.getAnswer());
    }



}
