package tn.esprit.spring.RestControllers;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import tn.esprit.spring.DAO.Entities.Product;
import tn.esprit.spring.Services.Interfaces.IProductService;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class ProductController {
    private IProductService iProductService;

    //admin

    @GetMapping("/admin/product/all")
    public List<Product> getAllProductsAdmin() {
        return iProductService.selectAll();
    }
    @PostMapping("/admin/product/add")
    public Product addProduct(@RequestBody Product p){
        float a,b;

        a= p.getPrice();
        b= p.getInterest();
        p.setPrice(a+a*b);
        return iProductService.add(p);
    }
    @PutMapping ("/admin/product/edit")
    public Product editProduct(@RequestBody Product p){

        return iProductService.edit(p);
    }

    @DeleteMapping ("/admin/product/{id}/delete")
    public List<Product> deletebyid (@PathVariable int id){
        iProductService.deleteById(id);
        return iProductService.selectAll();
    }

    @GetMapping("/admin/product/{id}")
    public Product getProdcutForAdmin(@PathVariable int id) {
        return iProductService.selectById(id);
    }
//Client

    @GetMapping("/client/product/all")
    public List<Product> getAllProductsClient() {
        return iProductService.selectAll();
    }

    @GetMapping("/client/product/{id}")
    public Product getProdcutForClient(@PathVariable int id) {
        return iProductService.selectById(id);
    }
    //visitor

    @GetMapping("/product/all")
    public List<Product> getAllProductsVisitor() {
        return iProductService.selectAll();
    }


/*
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

*/

}
