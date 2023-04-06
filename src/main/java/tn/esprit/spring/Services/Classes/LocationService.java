package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Location;
import tn.esprit.spring.DAO.Entities.State;
import tn.esprit.spring.DAO.Repositories.LocationRepository;
import tn.esprit.spring.Services.Interfaces.ILocationService;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService implements ILocationService {
    private LocationRepository locationRepository;

    @Override
    public double distance(Location location1, Location location2) {

        final int EARTH_RADIUS = 6371;

        double dLat = Math.toRadians(location2.getLatitude() - location1.getLatitude());
        double dLon = Math.toRadians(location2.getLongitude() - location2.getLongitude());

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(Math.toRadians(location2.getLatitude())) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = EARTH_RADIUS * c;

        return distance;
    }

    @Override
    public Location minDistanceFrom(Location clientLocation) {

        List<Location> agenciesLocationsList = locationRepository.findAll() ;
        double min = distance(agenciesLocationsList.get(1),clientLocation);
        Location closetOne = new Location();

        for (Location agencyLocation : agenciesLocationsList) {
            if (distance(agencyLocation,clientLocation) <= min) {
                min = distance(agencyLocation,clientLocation);
                closetOne.setIdLocation(agencyLocation.getIdLocation());
                closetOne.setLongitude(agencyLocation.getLongitude());
                closetOne.setLatitude(agencyLocation.getLatitude());
            }
        }
        return closetOne;
    }

    @Override
    public Location add(Location agencyLocation) {
        return locationRepository.save(agencyLocation);
    }

    @Override
    public Location edit(Location agencyLocation) {
        return locationRepository.save(agencyLocation);
    }

    @Override
    public void deleteById(long idlocation) {
        locationRepository.deleteById(idlocation);
    }

    @Override
    public List<Location> selectAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> selectAgencyByState(State state) {
        return locationRepository.findLocationByState(state);
    }
}
