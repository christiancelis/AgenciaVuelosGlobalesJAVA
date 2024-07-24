package airCrew.application;

import java.util.ArrayList;

import airCrew.domain.AirCrew;
import airCrew.domain.ServiceAirCrew;

public class UseCaseGetAllCrewInfo {
    ServiceAirCrew serviceAirCrew;

    public UseCaseGetAllCrewInfo(ServiceAirCrew serviceAirCrew) {
        this.serviceAirCrew = serviceAirCrew;
    }

    public ArrayList<AirCrew> execute(int idViaje){
        return serviceAirCrew.GetAllCrewInfo(idViaje);
    }
    
}
