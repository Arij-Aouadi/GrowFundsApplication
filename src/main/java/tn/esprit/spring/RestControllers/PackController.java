package tn.esprit.spring.RestControllers;


import lombok.AllArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.DAO.Entities.Packs;

import tn.esprit.spring.DAO.Entities.Product;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.Services.Interfaces.IPacksService;
import tn.esprit.spring.Services.Interfaces.IUserService;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class PackController {

    private IPacksService iPacksService;
    private IUserService auth;

    //Admin
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/admin/packs/all")
    public List<Packs> getAllPackssAdmin() {


        return iPacksService.selectAll();
    }

    @PostMapping("/admin/packs/add")
    public Packs addPacks(@RequestBody Packs packs){
        float totalPrice = 0;
        List<Product> products = packs.getProduct_pack();

        for(Product product: products) {
            totalPrice += product.getPrice();
        }

        packs.setPrice(totalPrice);
        packs.setDrCode(generateDrCode());
        return iPacksService.add(packs);
    }
    private String generateDrCode() {
        // Generate a DR code here, e.g. using a random UUID
        return UUID.randomUUID().toString();}
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

    @PutMapping ("/client/packs/like/{cid}")
    public Packs toggleLikePack(@RequestBody Packs packs,@PathVariable long cid){
        User u = auth.getById(cid);
        return iPacksService.toggleLike(packs,u);

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
