package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Product;

import java.util.List;

public interface IProductService {

    Product add(Product a);
    Product edit(Product a);
    List<Product> selectAll();
    Product selectById(int idPack);
    void deleteById(int idPack);
    void delete(Product a);
    List<Product> addAll(List<Product> list);
    void deleteAll(List<Product> list);
    List<Product> selectbynom (String nom);
    List<Product> selectbyprice (Float a,Float b);
}
