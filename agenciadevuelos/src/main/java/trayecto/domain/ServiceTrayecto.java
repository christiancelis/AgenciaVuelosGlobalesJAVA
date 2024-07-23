package trayecto.domain;

import java.sql.SQLException;
import java.util.ArrayList;

import viaje.domain.FlightRecord;

public interface ServiceTrayecto {

    void guardarTrayecto(Trayecto trayecto);
    ArrayList<Trayecto>GetAllTrayecto();
     void actualizarTrayecto(Trayecto trayecto) throws SQLException;
}
