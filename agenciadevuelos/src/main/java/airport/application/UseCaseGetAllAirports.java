package airport.application;

import java.util.ArrayList;

import airport.domain.ServiceAirport;
import airport.domain.entity.Airport;

public class UseCaseGetAllAirports {
    ServiceAirport serviceAirport;

    public UseCaseGetAllAirports(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    public ArrayList<Airport> execute(){
        return serviceAirport.getAllAirpots();
    }
}
