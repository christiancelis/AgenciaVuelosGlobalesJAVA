package airport.application;

import airport.domain.ServiceAirport;

public class UseCaseDeleteAirport {
    ServiceAirport serviceAirport;

    public UseCaseDeleteAirport(ServiceAirport serviceAirport) {
        this.serviceAirport = serviceAirport;
    }

    public void execute(int id){
        serviceAirport.deleteAirport(id);
    }
}
