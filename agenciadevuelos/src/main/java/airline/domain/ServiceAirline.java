package airline.domain;

import java.util.ArrayList;

public interface ServiceAirline {
    Airline getAirlineById(int id);
    ArrayList<Airline> getAllAirlines();
}
