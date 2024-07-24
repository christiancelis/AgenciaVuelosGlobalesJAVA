package airport.application;

import airport.domain.ServiceAirport;
import airport.domain.entity.Airport;

public class UseCaseGetAirport {
    private final ServiceAirport serviceAirport;

    public UseCaseGetAirport(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    public Airport execute(int id){
        return serviceAirport.getAirport(id);
    }



}
