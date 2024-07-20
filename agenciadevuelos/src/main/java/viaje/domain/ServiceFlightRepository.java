package viaje.domain;

import java.sql.SQLException;

public interface ServiceFlightRepository {
    void GuardarViaje(FlightRecord flightRecord) throws SQLException;
    // Puedes agregar otros métodos necesarios para la gestión de vuelos
}
