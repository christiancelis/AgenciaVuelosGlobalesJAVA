package viaje.application;

import viaje.domain.ServiceFlightRepository;

public class ListVuelos {
private final ServiceFlightRepository serviceFlightRepository;

public ListVuelos(ServiceFlightRepository serviceFlightRepository) {
    this.serviceFlightRepository = serviceFlightRepository;
}

public void execute() {
    serviceFlightRepository.GetAllVuelos();
    ;
}

}