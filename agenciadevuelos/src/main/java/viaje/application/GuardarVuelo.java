package viaje.application;

import viaje.domain.FlightRecord;
import viaje.domain.ServiceFlightRepository;
import java.sql.SQLException;

public class GuardarVuelo {

    private final ServiceFlightRepository serviceFlightRepository;

    public GuardarVuelo(ServiceFlightRepository flightRepository) {
        this.serviceFlightRepository = flightRepository;
    }

    public void saveFlightRecord(FlightRecord flightRecord) {
        try {
            serviceFlightRepository.GuardarViaje(flightRecord); 
            System.out.println("Registro de vuelo guardado: " + flightRecord);
        } catch (SQLException e) {
            System.err.println("Error al guardar el registro de vuelo: " + e.getMessage());
        }
    }

    public void execute(FlightRecord flightRecord) throws SQLException {
       serviceFlightRepository.GuardarViaje(flightRecord);
        System.out.println("Método execute llamado en FlightService");
    }
}
