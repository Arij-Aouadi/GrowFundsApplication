package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Product;
import tn.esprit.spring.DAO.Repositories.ProductRepository;
import tn.esprit.spring.Services.Interfaces.IProductService;

import java.util.List;
@AllArgsConstructor
@Service
public class ProductService implements IProductService {
    ProductRepository productRepository;
    @Override
    public Product add(Product a) {
        return productRepository.save(a);
    }

    @Override
    public Product edit(Product a) {
        return productRepository.save(a);
    }

    @Override
    public List<Product> selectAll() {
        return productRepository.findAll();
    }

    @Override
    public Product selectById(int idProduct) {
        return productRepository.findById(idProduct).get();
    }

    @Override
    public void deleteById(int idProduct) {
        productRepository.deleteById(idProduct);

    }

    @Override
    public void delete(Product a) {
        productRepository.delete(a);

    }


    @Override
    public List<Product> addAll(List<Product> list) {
        return productRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Product> list) {
         productRepository.saveAll(list);
    }

    @Override
    public List<Product> selectbynom(String nom) {
        return productRepository.findByNameProdcut(nom);
    }

    @Override
    public List<Product> selectbyprice(Float a, Float b) {
        return productRepository.findByPriceProductBetween(a,b);
    }
}
