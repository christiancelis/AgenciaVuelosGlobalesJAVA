package airport.application;

import java.util.ArrayList;

import airport.domain.ServiceAirport;
import airport.domain.entity.City;

public class UseCaseGetCities {
    private ServiceAirport serviceAirport;

    public UseCaseGetCities(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    public ArrayList<City> execute(){
        return serviceAirport.getCities();
    }

    
}   
