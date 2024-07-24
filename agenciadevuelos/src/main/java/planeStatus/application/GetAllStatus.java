package planeStatus.application;
import java.util.ArrayList;

import planeStatus.domain.ServiceStatus;
import planeStatus.domain.Status;

public class GetAllStatus {
    public final ServiceStatus serviceStatus;

    public GetAllStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public ArrayList <Status> execute(){
        return serviceStatus.getAllStatus();
    } 
}
