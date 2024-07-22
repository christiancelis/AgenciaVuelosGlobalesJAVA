package viaje.domain;

import java.sql.Date;
import java.sql.Time;

public class FlightRecord {
    private int id;
    private String originAirport;
    private String destinationAirport;
    private String originCity;
    private String destinationCity;
    private double price;
    private Date travelDate;
    private Time hora;

    // Constructor vacío
    public FlightRecord() {
        this.originAirport = "";
        this.destinationAirport = "";
        this.originCity = "";
        this.destinationCity = "";
        this.price = 0.0;
        this.travelDate = new Date(System.currentTimeMillis());
        this.hora = new Time(System.currentTimeMillis());
    }

    // Constructor completo para inicializar todos los campos
    public FlightRecord(String originAirport, String destinationAirport, String originCity,
                        String destinationCity, double price, Date travelDate, Time hora) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.price = price;
        this.travelDate = travelDate;
        this.hora = hora;
    }

    // Getters y setters
    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
