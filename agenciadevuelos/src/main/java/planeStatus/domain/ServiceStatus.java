package planeStatus.domain;

import java.util.ArrayList;

public interface ServiceStatus {
    ArrayList <Status> getAllStatus();
    Status getStatusById(Integer id);
}
