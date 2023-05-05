package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.DAO.Entities.Packs;

import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Interfaces.IPacksService;
import tn.esprit.spring.Services.Interfaces.IUserService;


import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class PackController {

    private IPacksService iPacksService;

    //Admin
    @GetMapping("/admin/packs/all")
    public List<Packs> getAllPackssAdmin() {
        return iPacksService.selectAll();
    }

    @PostMapping("/admin/packs/add")
    public Packs addPacks(@RequestBody Packs packs){
        return iPacksService.add(packs);
    }
    @PutMapping ("/admin/packs/edit")
    public Packs editPacks(@RequestBody Packs packs){
        return iPacksService.edit(packs);}

    @GetMapping("/admin/packs/{id}")
    public Packs getPacksByIdAdmin(@PathVariable int id)
    {
        return iPacksService.selectById(id);
    }

    @DeleteMapping ("/admin/packs/{id}/delete")
    public void deletPacks (@PathVariable int id){
        iPacksService.deleteById(id);}


//client
@GetMapping("/client/packs/all")
public List<Packs> getAllPacksClient() {
    return iPacksService.selectAll();
}

    @PutMapping ("/client/packs/like")
    public Packs toggleLikePack(@RequestBody Packs packs){
        return iPacksService.toggleLike(packs);

    }
    @GetMapping("/client/packs/{id}")
    public Packs getPacksByIdClient(@PathVariable int id)
    {
        return iPacksService.selectById(id);
    }

    //visitor

    @GetMapping("/packs/all")
    public List<Packs> getAllPacksVisitor() {
        return iPacksService.selectAll();
    }


   /*@DeleteMapping ("/DeleteAllPacks")
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


    @PostMapping("/AddAll")
    public List<Packs> affichertout(@RequestBody List<Packs> packs) {return iPacksService.addAll(packs);}


    @GetMapping("/likes-count/{packageId}")
    public int countLikesByPackageId(@PathVariable int packageId) {
        return iPacksService.getLikesCountByPackage(packageId);


    }

    @GetMapping("/most-liked")
    public Packs getMostLikedPackage() {
        Packs mostLikedPackage = iPacksService.getMostLikedPackage();
        return mostLikedPackage;
    }
    @PostMapping("/addPack/{idPack}/{idProduct}")
    public Boolean createandaffect(@PathVariable("idPack") int idpack, @RequestBody Map<Integer, Integer> products ) {
        return iPacksService.createandaffect(idpack,products);

    }
    @GetMapping("/packs/{userId}/recommended-packs")
    public List<Packs> getRecommendedPacksByUserId(@PathVariable int userId) {
        return iPacksService.getRecommendedPacks(userId);
    }

*/
}
