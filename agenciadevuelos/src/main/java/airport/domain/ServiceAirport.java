package airport.domain;

import java.util.ArrayList;

import airport.domain.entity.Airport;
import airport.domain.entity.City;
import airport.domain.entity.Country;

public interface ServiceAirport {
    void createAirport(Airport airport);
    ArrayList<City> getCities();
    ArrayList<Country> getCountry(int id);
    Airport getAirport(int id);
    ArrayList<Airport> getAllAirpots();
    void deleteAirport(int id);
    void updateAirport(Airport airport);
}
