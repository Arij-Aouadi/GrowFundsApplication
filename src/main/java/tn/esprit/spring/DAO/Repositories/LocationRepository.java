package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.Location;
import tn.esprit.spring.DAO.Entities.State;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findLocationByState(State state);
}
