package airCrew.application;

import airCrew.domain.AirCrew;
import airCrew.domain.ServiceAirCrew;

public class UseCaseValidateAirCrew {
    ServiceAirCrew serviceAirCrew;

    public UseCaseValidateAirCrew(ServiceAirCrew serviceAirCrew) {
        this.serviceAirCrew = serviceAirCrew;
    }

    public AirCrew execute(int idEmpleado){
        return serviceAirCrew.ValidateAirCrew(idEmpleado);
    }
}
