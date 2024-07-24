package airport.application;

import airport.domain.ServiceAirport;
import airport.domain.entity.Airport;

public class UseCaseUpdateAirport {
    ServiceAirport serviceAirport;

    public UseCaseUpdateAirport(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    public void execute(Airport airport){
        serviceAirport.updateAirport(airport);
    }

    
}
