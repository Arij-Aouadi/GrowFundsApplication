package tn.esprit.spring.RestControllers;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import tn.esprit.spring.DAO.Entities.Product;
import tn.esprit.spring.Services.Interfaces.IProductService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/product")

public class ProductController {
    private IProductService iProductService;

    @GetMapping("/AfficherProduct")
    public List<Product> afficher() {
        return iProductService.selectAll();
    }
    @GetMapping("/AfficherProductAvecId/{id}")
    public Product afficherAccountAvecId(@PathVariable int id)
    {
        return iProductService.selectById(id);
    }

    @PostMapping("/AjouterProduct")
    public Product ajouter(@RequestBody Product product){
        return iProductService.add(product);

    }
    @DeleteMapping ("/DeleteProductById")
    public void deletebyid (@RequestParam int id){
        iProductService.deleteById(id);}

    @DeleteMapping ("/DeleteAllProduct")
    public void deleteAll (List<Product> list) {
        iProductService.deleteAll(list);
    }

    @GetMapping("/AfficherProductById/{id}")
    public Product afficherAvecId(@PathVariable int id){
        return iProductService.selectById(id);
    }


    @GetMapping("/RetrieveProductByName")
    public List<Product> afficherproductbyname(@RequestParam String type){
        return iProductService.selectbynom(type);
    }
    @GetMapping("/RetrieveProductByPrice")
    public List<Product> afficherproductbyprix(@RequestParam Float a,Float b){
        return iProductService.selectbyprice(a,b);
    }
    @PutMapping ("/ModifierProduct")
    public Product edit(@RequestBody Product packs){
        return iProductService.edit(packs);}

    @PostMapping("/AddAll")
    public List<Product> affichertout(@RequestBody List<Product> products) {return iProductService.addAll(products);}



}
