package manufacture.application;

import java.util.ArrayList;

import manufacture.domain.Manufacture;
import manufacture.domain.ServiceManufacture;

public class GetAllManufactures {
    private final ServiceManufacture serviceManufacture;

    public GetAllManufactures(ServiceManufacture serviceManufacture) {
        this.serviceManufacture = serviceManufacture;
    }

    public ArrayList<Manufacture> execute(){
        return serviceManufacture.getAllManufactures();
    }
    
}
