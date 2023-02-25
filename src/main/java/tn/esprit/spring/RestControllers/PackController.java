package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.Services.Interfaces.IPacksService;

import java.util.List;
@RestController
@AllArgsConstructor
public class PackController {
    private IPacksService iPacksService;

    @GetMapping("/afficherpack")
    public List<Packs> afficher() {
        return iPacksService.selectAll();
    }
    @PostMapping("/ajouterpack")
    public Packs ajouter(@RequestBody Packs packs){
        return iPacksService.add(packs);

    }
    @GetMapping("/afficherpackbyid/{id}")
    public Packs afficherAvecId(@PathVariable int id){
        return iPacksService.selectById(id);
    }

}
