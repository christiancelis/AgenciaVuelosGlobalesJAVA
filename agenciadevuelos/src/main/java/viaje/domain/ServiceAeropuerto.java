package viaje.domain;

import java.util.List;

public interface ServiceAeropuerto {
    List<Viaje> findAirportsByCoordinates(double latitude, double longitude, double toleranceKm);
}
