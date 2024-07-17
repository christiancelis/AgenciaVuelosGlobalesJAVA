package planeStatus.application;

import planeStatus.domain.ServiceStatus;
import planeStatus.domain.Status;

public class GetStatusById {
    public final ServiceStatus serviceStatus;

    public  GetStatusById(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Status execute(Integer id){
        return serviceStatus.getStatusById(id);
    } 
}
