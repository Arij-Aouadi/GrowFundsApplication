package tn.esprit.spring.Services.Classes;

import tn.esprit.spring.DAO.Entities.Product;
import tn.esprit.spring.DAO.Repositories.ProductRepository;
import tn.esprit.spring.Services.Interfaces.IProductService;

import java.util.List;

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
    public Product selectById(int idPack) {
        return productRepository.findById(idPack).get();
    }

    @Override
    public void deleteById(int idPack) {

    }

    @Override
    public void delete(Product a) {

    }


    @Override
    public List<Product> addAll(List<Product> list) {
        return null;
    }

    @Override
    public void deleteAll(List<Product> list) {

    }
}
