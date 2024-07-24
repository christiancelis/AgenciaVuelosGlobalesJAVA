package trayecto.domain;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ServiceTrayecto {

    int GuardarTrayecto(Trayecto trayecto);
    ArrayList<Trayecto>GetAllTrayecto();
     void actualizarTrayecto(Trayecto trayecto) throws SQLException;
     void insertarVueloEscala(int idViaje,int idEscala);
     
}
