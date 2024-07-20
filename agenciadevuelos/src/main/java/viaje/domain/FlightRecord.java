package viaje.domain;

import java.sql.Date;
import java.sql.Time;

public class FlightRecord {
    private final Viaje originAirport;
    private final Viaje destinationAirport;
    private final double price;
    private final Date travelDate;
    private final Time hora;
    private final String originCity;
    private final String destinationCity;

    // Constructor que acepta todos los parámetros necesarios
    public FlightRecord(Viaje selectedOriginAirport, Viaje selectedDestinationAirport, double price, Date travelDate, String originCity, String destinationCity,Time hora) {
        this.originAirport = selectedOriginAirport;
        this.destinationAirport = selectedDestinationAirport;
        this.price = price;
        this.travelDate = travelDate;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.hora = hora;
    }

   

    public Viaje getOriginAirport() {
        return originAirport;
    }



    public Viaje getDestinationAirport() {
        return destinationAirport;
    }



    public double getPrice() {
        return price;
    }



    public Date getTravelDate() {
        return travelDate;
    }



    public String getOriginCity() {
        return originCity;
    }



    public String getDestinationCity() {
        return destinationCity;
    }



    @Override
    public String toString() {
        return "FlightRecord{" +
               "originAirport=" + originAirport.getName() +
               ", destinationAirport=" + destinationAirport.getName() +
               ", price=" + price +
               ", travelDate=" + travelDate +
               ", originCity='" + originCity + '\'' +
               ", destinationCity='" + destinationCity + '\'' +
               '}';
    }



    public Time getHora() {
        return hora;
    }
}
