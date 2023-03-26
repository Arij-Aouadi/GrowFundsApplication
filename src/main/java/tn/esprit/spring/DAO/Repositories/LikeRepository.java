package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Likes;

public interface LikeRepository extends JpaRepository<Likes,Integer> {
}
