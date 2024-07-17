package plane.application;

import java.util.ArrayList;

import plane.domain.Plane;
import plane.domain.ServicePlane;

public class GetAllPlanes {
    
    private final ServicePlane servicePlane;

    public GetAllPlanes(ServicePlane servicePlane) {
        this.servicePlane = servicePlane;
    }

    public ArrayList<Plane> execute(){
        return servicePlane.getAllPlane();
    }
    
}
