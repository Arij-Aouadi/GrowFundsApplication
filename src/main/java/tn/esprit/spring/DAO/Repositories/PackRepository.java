package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Packs;

import java.util.List;

public interface PackRepository extends JpaRepository<Packs,Integer> {


    Packs findByIdPack(int id);
    List<Packs> findByTypepack(String type);
    @Query("SELECT COUNT(l) FROM Likes l WHERE l.packageId = :packageId")
    int countLikesByPackageId(@Param("packageId") int packageId);

}

