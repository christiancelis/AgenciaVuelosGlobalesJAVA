package status.application;

import status.domain.ServiceStatus;
import status.domain.Status;

public class GetStatusById {
    public final ServiceStatus serviceStatus;

    public  GetStatusById(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Status execute(Integer id){
        return serviceStatus.getStatusById(id);
    } 
}
