package airline.application;

import airline.domain.Airline;
import airline.domain.ServiceAirline;

public class CreateAirline {

    private final ServiceAirline serviceAirline;

    public CreateAirline(ServiceAirline serviceAirline) {
        this.serviceAirline = serviceAirline;
    }
    public void execute(Airline airline){
        serviceAirline.createAirlin(airline);
    }
}
