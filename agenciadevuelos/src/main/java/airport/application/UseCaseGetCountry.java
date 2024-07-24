package airport.application;

import java.util.ArrayList;

import airport.domain.ServiceAirport;
import airport.domain.entity.Country;

public class UseCaseGetCountry {
    private ServiceAirport serviceAirport;

    public UseCaseGetCountry(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    public ArrayList<Country> execute(){
        return serviceAirport.getCountry(0);
    }
}
