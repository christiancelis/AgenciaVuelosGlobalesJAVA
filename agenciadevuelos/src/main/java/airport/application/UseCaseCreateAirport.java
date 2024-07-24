package airport.application;

import airport.domain.ServiceAirport;
import airport.domain.entity.Airport;

public class UseCaseCreateAirport {
    private final ServiceAirport serviceAirport;

    public UseCaseCreateAirport(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }
    
    public void execute(Airport airport){
        serviceAirport.createAirport(airport);
    }
}
