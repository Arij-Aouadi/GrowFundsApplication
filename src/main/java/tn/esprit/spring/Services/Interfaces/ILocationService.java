package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Location;
import tn.esprit.spring.DAO.Entities.State;

import java.util.List;

public interface ILocationService {

    double distance(Location location1,Location location2);
    Location minDistanceFrom(Location clientLocation);
    Location add (Location agencyLocation);
    Location edit (Location agencyLocation);
    void deleteById (long idLocation);

    List<Location> selectAll();

    List<Location> selectAgencyByState(State state);
}
