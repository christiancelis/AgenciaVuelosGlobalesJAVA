package plane.domain;

import java.util.ArrayList;

public interface ServicePlane {
    void createPlane(Plane plane);
    ArrayList<Plane> getAllPlane();
    void DeletePlaneByPlate(String plate);
    void updatePlaneByPlate(Plane plane);
    Plane getPlaneByPlate(String plate);
}
