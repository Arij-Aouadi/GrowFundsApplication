package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects,Integer> {
    List<Projects> findByInvestor(User investor);

}

