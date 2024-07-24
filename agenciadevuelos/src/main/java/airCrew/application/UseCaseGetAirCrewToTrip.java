package airCrew.application;

import java.util.ArrayList;

import airCrew.domain.ServiceAirCrew;
import employee.domain.Employee;

public class UseCaseGetAirCrewToTrip {
    ServiceAirCrew serviceAirCrew;

    public UseCaseGetAirCrewToTrip(ServiceAirCrew serviceAirCrew) {
        this.serviceAirCrew = serviceAirCrew;
    }

    public ArrayList <Employee> execute(int idViaje){
        return serviceAirCrew.GetAirCrewToTrip(idViaje);
    }
}
