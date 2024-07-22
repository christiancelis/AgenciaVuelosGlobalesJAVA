package viaje.domain;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceFlightRepository {
    void GuardarViaje(FlightRecord flightRecord) throws SQLException;
    void EliminarVueloById(int id);
    ArrayList <FlightRecord> GetAllVuelos();
}
