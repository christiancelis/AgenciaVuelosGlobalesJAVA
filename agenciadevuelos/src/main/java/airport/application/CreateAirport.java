package airport.application;

import airport.domain.Airport;
import airport.domain.ServiceAirport;

public class CreateAirport {
    private final ServiceAirport serviceAirport;

    public CreateAirport(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }
    
    public void execute(Airport airport){
        serviceAirport.createAirport(airport);
    }
}
