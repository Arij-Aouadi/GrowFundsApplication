package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Packs;

public interface PackRepository extends JpaRepository<Packs,Integer> {
}
