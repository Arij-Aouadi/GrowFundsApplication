package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Location;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;
import tn.esprit.spring.DAO.Entities.State;
import tn.esprit.spring.Services.Classes.LocationService;
import tn.esprit.spring.Services.Interfaces.ILocationService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
public class LocationController {
    ILocationService iLocationService;
    @GetMapping("/getClosestAgency/{latitude}/{longitude}")
    public Location myClosestAgency(@PathVariable double latitude,@PathVariable double longitude ){
        Location clientLocation= new Location();
        clientLocation.setLatitude(latitude);
        clientLocation.setLongitude(longitude);
        return iLocationService.minDistanceFrom(clientLocation);
    }

    @PostMapping("/addAgencyLocation")
    public Location addLocation (@RequestBody Location agencyLocation) {
        return iLocationService.add(agencyLocation);

    }
    @PostMapping("/editAgencyLocation")
    public Location editLocation(@RequestBody Location agencyLocation) {
        return iLocationService.edit(agencyLocation);

    }
    @GetMapping("/showAllAgencies")
    public List<Location> showAll() {
        return iLocationService.selectAll();
    }

    @DeleteMapping("/showAllAgencies")
    public void deleteAgencyById(long idLocation) {
        iLocationService.deleteById(idLocation);

    }

    @GetMapping("/showAgencyByState")
    public List<Location> showAgencyByState(State state) {
        return iLocationService.selectAgencyByState(state);
    }





}
