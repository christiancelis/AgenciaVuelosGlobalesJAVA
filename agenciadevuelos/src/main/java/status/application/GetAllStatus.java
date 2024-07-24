package status.application;
import java.util.ArrayList;

import status.domain.ServiceStatus;
import status.domain.Status;

public class GetAllStatus {
    public final ServiceStatus serviceStatus;

    public GetAllStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public ArrayList <Status> execute(){
        return serviceStatus.getAllStatus();
    } 
}
