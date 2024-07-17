package crewmember.application;

import crewmember.domain.Crewmember;
import crewmember.domain.ServiceCrewmember;

public class CreateCrewmember {

private ServiceCrewmember serviceCrewmember;

public CreateCrewmember(ServiceCrewmember serviceCrewmember) {
    this.serviceCrewmember = serviceCrewmember;
}

 public void execute(Crewmember crewmember) {
        serviceCrewmember.CreateCrewmember(crewmember);
    }


}
