package plane.application;

import plane.domain.Plane;
import plane.domain.ServicePlane;

public class UpdatePlaneByPlate {

    private final ServicePlane servicePlane;

    public UpdatePlaneByPlate(ServicePlane servicePlane) {
        this.servicePlane = servicePlane;
    }

    public void execute(Plane plane){
        servicePlane.updatePlaneByPlate(plane);
    }

    
}
