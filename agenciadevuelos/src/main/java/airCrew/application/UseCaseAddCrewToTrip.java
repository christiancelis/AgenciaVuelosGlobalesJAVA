package airCrew.application;

import airCrew.domain.AirCrew;
import airCrew.domain.ServiceAirCrew;

public class UseCaseAddCrewToTrip {
    ServiceAirCrew serviceAirCrew;

    public UseCaseAddCrewToTrip(ServiceAirCrew serviceAirCrew) {
        this.serviceAirCrew = serviceAirCrew;
    }

    public void execute(AirCrew airCrew){
        serviceAirCrew.AddAirCrewToTrip(airCrew);
    }
    
}
