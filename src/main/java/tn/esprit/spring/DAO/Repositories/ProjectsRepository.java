package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Project;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Project,Integer> {
    List<Project> findByFounder(User founder);

}

