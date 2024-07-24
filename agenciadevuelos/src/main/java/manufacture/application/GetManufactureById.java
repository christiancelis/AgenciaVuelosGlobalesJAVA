package manufacture.application;

import manufacture.domain.Manufacture;
import manufacture.domain.ServiceManufacture;

public class GetManufactureById {
    private final ServiceManufacture serviceManufacture;

    public GetManufactureById(ServiceManufacture serviceManufacture) {
        this.serviceManufacture = serviceManufacture;
    }

    public Manufacture execute(Integer id){
        return serviceManufacture.getManufactureById(id);
    }
    
}
