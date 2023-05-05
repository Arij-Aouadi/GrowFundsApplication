package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Product;

import java.util.List;

public interface IProductService {

    Product add(Product a);
    Product edit(Product a);
    List<Product> selectAll();
    Product selectById(int idPack);
    void deleteById(int idPack);

}
