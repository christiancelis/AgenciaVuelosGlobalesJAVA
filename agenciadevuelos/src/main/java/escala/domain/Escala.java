package escala.domain;

import java.sql.Date;

import viaje.domain.Viaje;


public class Escala {
    private int id;
    private Viaje originAirport;
    private Viaje destinationAirport;
    private double price;
    private Date travelDate;
    private String originCity;
    private String destinationCity;
    private int avionId;
    private int viajeId;

    public Escala(Viaje originAirport, Viaje destinationAirport, double price, Date travelDate, String originCity,
            String destinationCity, int avionId, int viajeId) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.price = price;
        this.travelDate = travelDate;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.avionId = avionId;
        this.viajeId = viajeId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Viaje getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Viaje originAirport) {
        this.originAirport = originAirport;
    }

    public Viaje getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Viaje destinationAirport) {
        this.destinationAirport = destinationAirport;
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

    public int getAvionId() {
        return avionId;
    }

    public void setAvionId(int avionId) {
        this.avionId = avionId;
    }

    public int getViajeId() {
        return viajeId;
    }

    public void setViajeId(int viajeId) {
        this.viajeId = viajeId;
    }
}
