package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Role;
import tn.esprit.spring.DAO.Entities.TypeRole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role>findByTypeRole(TypeRole typeRole);
}
