package viaje.infrastructure.out;

import viaje.domain.Viaje;
import viaje.domain.ServiceAeropuerto;
import utils.DistanceCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  AirportDatabase implements ServiceAeropuerto {
    private static final String FILE_PATH = "agenciadevuelos/src/main/java/airports.csv";
    private Map<String, List<Viaje>> cityToAirportMap = new HashMap<>();
    private List<Viaje> allAirports = new ArrayList<>();

    public AirportDatabase() throws IOException {
        loadDatabase();
    }

    private void loadDatabase() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",", -1);

                if (columns.length >= 16) {
                    try {
                        String city = columns[10].trim().toLowerCase();
                        String iataCode = columns[1].trim();
                        String aeropuerto = columns[3].trim();
                        double latitude = Double.parseDouble(columns[4].trim());
                        double longitude = Double.parseDouble(columns[5].trim());
                        String type = columns[2].trim().toLowerCase();

                        if (city.isEmpty() || iataCode.isEmpty() || !(type.contains("large") || type.contains("medium"))) {
                            continue;
                        }

                        Viaje airport = new Viaje(iataCode, type, city, latitude, longitude, aeropuerto);
                        allAirports.add(airport);
                        cityToAirportMap.computeIfAbsent(city, k -> new ArrayList<>()).add(airport);
                    } catch (NumberFormatException e) {
                        // Ignorar líneas con datos inválidos
                    }
                }
            }
        }
    }

    public List<Viaje> findAirportsByCoordinates(double lat, double lon, double tolerance) {
        List<Viaje> nearbyAirports = new ArrayList<>();
        for (Viaje airport : allAirports) {
            double distance = DistanceCalculator.calculateDistance(lat, lon, airport.getLatitude(), airport.getLongitude());
            if (distance <= tolerance) {
                nearbyAirports.add(airport);
            }
        }
        return nearbyAirports;
    }
}
