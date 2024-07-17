package plane.application;

import plane.domain.Plane;
import plane.domain.ServicePlane;

public class CreatePlane{

    private final ServicePlane servicePlane;

    public CreatePlane(ServicePlane servicePlane) {
        this.servicePlane = servicePlane;
    }

    public void execute(Plane plane){
        servicePlane.createPlane(plane);
    }
    
}
