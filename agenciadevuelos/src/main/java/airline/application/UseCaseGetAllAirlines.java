package airline.application;

import java.util.ArrayList;

import airline.domain.Airline;
import airline.domain.ServiceAirline;

public class UseCaseGetAllAirlines {
    ServiceAirline serviceAirline;

    public UseCaseGetAllAirlines(ServiceAirline serviceAirline) {
        this.serviceAirline = serviceAirline;
    }

    public ArrayList<Airline> execute(){
        return serviceAirline.getAllAirlines();
    }

    
}
