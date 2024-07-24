package viaje.domain;

import java.sql.Date;
import java.sql.Time;

public class FlightRecord {
    private int id;
    private String originAirport;
    private String originCity;
    private String destinationAirport;
    private String destinationCity;
    private double price;
    private Date travelDate;
    private Time hora;
    private int idAvion; 

    


    public FlightRecord() {
    }

    public FlightRecord(String originAirport, String originCity, String destinationAirport,
            String destinationCity, double price, Date travelDate, Time hora, int idAvion) {

        this.originAirport = originAirport;
        this.originCity = originCity;
        this.destinationAirport = destinationAirport;
        this.destinationCity = destinationCity;
        this.price = price;
        this.travelDate = travelDate;
        this.hora = hora;
        this.idAvion = idAvion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
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

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }


    @Override
    public String toString() {
        return String.format("ID: %d, Origen: %s (%s), Destino: %s (%s), Precio: %.2f COP, Fecha: %s, Hora: %s",
                id, originAirport, originCity, destinationAirport, destinationCity, price, travelDate, hora,idAvion);
    }
}
