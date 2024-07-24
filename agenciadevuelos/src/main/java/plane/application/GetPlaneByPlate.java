package plane.application;

import plane.domain.Plane;
import plane.domain.ServicePlane;

public class GetPlaneByPlate {
    private final ServicePlane servicePlane;

    public GetPlaneByPlate(ServicePlane servicePlane) {
        this.servicePlane = servicePlane;
    }

    public Plane execute(String plate){
        return servicePlane.getPlaneByPlate(plate);
    }
    
}
