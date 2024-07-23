package Escala.domain;

import java.sql.Date;
import java.sql.Time;

import viaje.domain.FlightRecord;

public class Escala extends FlightRecord {
    private int id;
    private int viajeId;

    // Constructor con parámetros adicionales
    public Escala(int id, String originAirport, String originCity, String destinationAirport, String destinationCity,
            double price, Date travelDate, Time hora, int idAvion, int viajeId) {
        super(originAirport, originCity, destinationAirport, destinationCity, price, travelDate, hora, idAvion);
        this.id = id;
        this.viajeId = viajeId;
    }

    // Constructor con parámetros para el ID y viajeId
    public Escala(int id, int viajeId, String string, String string2, String string3, String string4, double d, Date date, Time time) {
        super("", "", "", "", 0, null, null, 0); // Llamada al constructor de la superclase con valores predeterminados
        this.id = id;
        this.viajeId = viajeId;
    }

   

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViajeId() {
        return viajeId;
    }

    public void setViajeId(int viajeId) {
        this.viajeId = viajeId;
    }
}
