package manufacturer.application;

import manufacturer.domain.Manufacturer;
import manufacturer.domain.ServiceManufacturer;

public class CreateManufacturer {
private ServiceManufacturer serviceManufacturer;

public CreateManufacturer(ServiceManufacturer serviceManufacturer) {
    this.serviceManufacturer = serviceManufacturer;
}
 public void execute(Manufacturer manufacturer) {
        serviceManufacturer.CreateManufacturer(manufacturer);
    }

}
