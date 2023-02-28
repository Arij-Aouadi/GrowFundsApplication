package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.DAO.Entities.Packs;

import tn.esprit.spring.Services.Interfaces.IPacksService;


import java.util.List;
@RestController
@RequestMapping("/Packs")

@AllArgsConstructor
public class PackController {
    private IPacksService iPacksService;

    @GetMapping("/AfficherPack")
    public List<Packs> afficher() {
        return iPacksService.selectAll();
    }
    @GetMapping("/AfficherPackAvecId/{id}")
    public Packs afficherAccountAvecId(@PathVariable int idPack)
    {
        return iPacksService.selectById(idPack);
    }

    @PostMapping("/AjouterPack")
    public Packs ajouter(@RequestBody Packs packs){
        return iPacksService.add(packs);

    }
    @DeleteMapping ("/DeletePackById")
    public void deletebyid (@RequestParam int id){
        iPacksService.deleteById(id);}

    @DeleteMapping ("/DeleteAllPacks")
    public void deleteAll (List<Packs> list) {
        iPacksService.deleteAll(list);
    }

    @GetMapping("/AfficherPackById/{id}")
    public Packs afficherAvecId(@PathVariable int id){
        return iPacksService.selectById(id);
    }


    @GetMapping("/RetrievePacksByType")
    public List<Packs> afficheravectypepay(@RequestParam String type){
        return iPacksService.selectBytypepack(type);
    }
    @PutMapping ("/ModifierPack")
    public Packs edit(@RequestBody Packs packs){
        return iPacksService.edit(packs);}

    @PostMapping("/AddAll")
    public List<Packs> affichertout(@RequestBody List<Packs> packs) {return iPacksService.addAll(packs);}
}
