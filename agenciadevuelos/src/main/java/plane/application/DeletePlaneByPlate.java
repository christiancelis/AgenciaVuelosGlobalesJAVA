package plane.application;

import plane.domain.ServicePlane;

public class DeletePlaneByPlate {

    private final ServicePlane servicePlane;

    public DeletePlaneByPlate(ServicePlane servicePlane) {
        this.servicePlane = servicePlane;
    }
    
    public void execute(String plate){
        servicePlane.DeletePlaneByPlate(plate);
    }

    
}
