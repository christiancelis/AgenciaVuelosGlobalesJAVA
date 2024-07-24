package airCrew.application;

import airCrew.domain.ServiceAirCrew;

public class UseCaseGetIdAirlineOfTrip {
    ServiceAirCrew serviceAirCrew;

    public UseCaseGetIdAirlineOfTrip(ServiceAirCrew serviceAirCrew) {
        this.serviceAirCrew = serviceAirCrew;
    }

    public int execute(int idViaje){
        return serviceAirCrew.GetIdAirlineOfTrip(idViaje);
    }

    
}
