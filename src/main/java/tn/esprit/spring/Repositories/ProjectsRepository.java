package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Projects;

public interface ProjectsRepository extends JpaRepository<Projects,Integer> {

}
