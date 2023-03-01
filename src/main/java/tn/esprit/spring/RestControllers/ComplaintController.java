package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Complaint;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.Services.Interfaces.IComplaintService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ComplaintController {
    private IComplaintService iComplaintService;

    @GetMapping("/afficherreclamations")
    public List<Complaint> afficher() {
        return iComplaintService.selectAll();
    }
    @PostMapping("/ajouterreclamation")
    public Complaint ajouter(@RequestBody Complaint complaint){
        return iComplaintService.add(complaint);

    }
    @GetMapping("/afficherreclamationparid/{id}")
    public Complaint afficherAvecId(@PathVariable int id){
        return iComplaintService.selectById(id);
    }

    @PostMapping("/ajouterreclamations")

    public List<Complaint> addAllComplaints (@RequestBody List<Complaint> list){

        return iComplaintService.addAll(list);
    }
    @PutMapping ("/modifiercomplaint")
    public Complaint editCredit (@RequestBody Complaint complaint){
        return iComplaintService.edit(complaint);}

    @DeleteMapping ("/deletecomplaintbyid")
    public void deletebyidCredit (@RequestParam int id){
        iComplaintService.deleteById(id);
    }


    @DeleteMapping ("/deletecomplaint")
    public void deletebyobjectbyid (@RequestBody Complaint complaint){
        iComplaintService.delete(complaint);}
}
