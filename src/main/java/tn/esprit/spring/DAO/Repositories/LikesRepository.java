package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Likes;

public interface LikesRepository extends JpaRepository<Likes,Integer> {
}
