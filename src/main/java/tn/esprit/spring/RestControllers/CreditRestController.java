package tn.esprit.spring.RestControllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.Services.Interfaces.ICreditService;

import java.util.List;

@RestController
public class CreditRestController {
    private ICreditService iCreditService;

    @GetMapping("/afficherCredits")
    public List<Credits> afficherCredit() {
        return iCreditService.selectAll();
    }

    @PostMapping("ajouterCredit")
    public Credits ajouterCredit(@RequestBody Credits credits ){
        return iCreditService.add(credits);
    }

    @GetMapping("afficherCreditAvecId/{id}")
    public Credits afficherCreditAvecId(@PathVariable int id){
        return iCreditService.selectById(id);

}
}
