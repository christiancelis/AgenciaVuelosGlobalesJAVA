package planeManufacture.application;

import java.util.ArrayList;

import planeManufacture.domain.Manufacture;
import planeManufacture.domain.ServiceManufacture;

public class GetAllManufactures {
    private final ServiceManufacture serviceManufacture;

    public GetAllManufactures(ServiceManufacture serviceManufacture) {
        this.serviceManufacture = serviceManufacture;
    }

    public ArrayList<Manufacture> execute(){
        return serviceManufacture.getAllManufactures();
    }
    
}
