package viaje.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public interface ServiceFlight {
    void GuardarViaje(FlightRecord flightRecord) throws SQLException;
    void EliminarVueloById(int id);
    ArrayList <FlightRecord> GetAllVuelos();
    FlightRecord obtenerVueloById(int id) throws SQLException;
    void ActualizarVuelo(FlightRecord vuelo) throws SQLException;
    FlightRecord UpdateVueloById(int id,String originAirport,String originCity,String destinationAirport,String destinationCity,Double precio,Date fechaViaje,Time hora,int idAvion);
}
