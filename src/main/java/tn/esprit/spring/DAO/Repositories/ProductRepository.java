package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
