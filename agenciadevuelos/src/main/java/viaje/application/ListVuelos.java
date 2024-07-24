package viaje.application;

import java.util.ArrayList;

import viaje.domain.FlightRecord;
import viaje.domain.ServiceFlight;

public class ListVuelos {
private final ServiceFlight serviceFlightRepository;

public ListVuelos(ServiceFlight serviceFlightRepository) {
    this.serviceFlightRepository = serviceFlightRepository;
}

public ArrayList<FlightRecord> execute() {
  return serviceFlightRepository.GetAllVuelos();
}

}