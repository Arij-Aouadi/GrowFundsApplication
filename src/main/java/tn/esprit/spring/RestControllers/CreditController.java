package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.Services.Interfaces.ICreditService;
import tn.esprit.spring.Services.Interfaces.ICreditService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CreditController {
    private ICreditService iCreditService;
    @GetMapping("/affichercredit")
    public List<Credits> afficher() {
        return iCreditService.selectAll();
    }
    @PostMapping("/ajoutercredit")
    public Credits ajouter(@RequestBody Credits credits){
        return iCreditService.add(credits);

    }
    @GetMapping("/affichercreditid/{id}")
    public Credits afficherAvecId(@PathVariable int id){
        return iCreditService.selectById(id);
    }


}
