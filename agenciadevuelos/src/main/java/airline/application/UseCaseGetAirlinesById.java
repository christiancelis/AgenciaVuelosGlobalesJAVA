package airline.application;

import airline.domain.Airline;
import airline.domain.ServiceAirline;

public class UseCaseGetAirlinesById {

    ServiceAirline serviceAirline;

    public UseCaseGetAirlinesById(ServiceAirline serviceAirline) {
        this.serviceAirline = serviceAirline;
    }

    public Airline execute(int id){ 
        return serviceAirline.getAirlineById(id);
    }

}
    

