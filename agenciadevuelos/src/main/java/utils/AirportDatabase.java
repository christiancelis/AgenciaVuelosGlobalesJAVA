package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportDatabase {
    private static final String FILE_PATH = "agenciadevuelos/src/main/java/airports.csv"; 
    private Map<String, List<Airport>> cityToAirportMap = new HashMap<>();
    private List<Airport> allAirports = new ArrayList<>(); 

    public AirportDatabase() throws IOException {
        loadDatabase();
    }

    private void loadDatabase() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
           
    
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",", -1); // El parámetro -1 incluye las columnas vacías
    
                // Verificar si la línea tiene suficientes columnas
                if (columns.length >= 16) {
                    try {
                        String city = columns[10].trim().toLowerCase(); // La columna 'municipality'
                        String iataCode = columns[1].trim(); // La columna 'iata_code'
                        double latitude = Double.parseDouble(columns[4].trim());
                        double longitude = Double.parseDouble(columns[5].trim());
                        String type = columns[2].trim().toLowerCase(); // La columna 'type'
    
                        // Depuración: imprimir los valores leídos
                        // System.out.println("Ciudad: " + city + ", IATA: " + iataCode + ", Latitude: " + latitude + ", Longitude: " + longitude + ", Type: " + type);
    
                        // Verificar si la ciudad está vacía o el tipo no es adecuado
                        if (city.isEmpty() || iataCode.isEmpty() || !(type.contains("large") || type.contains("medium"))) {
                            // System.out.println("Registro ignorado - Ciudad vacía o tipo no válido: " + line);
                            continue; // Saltar este registro
                        }
    
                        // Limpiar y procesar la ciudad
                        city = city.replaceAll("[^a-zA-Z0-9 ]", "").trim();
                        iataCode = iataCode.replaceAll("[^a-zA-Z0-9]", "").trim();
    
                        // Crear el objeto Airport
                        Airport airport = new Airport(
                                columns[1], // ident
                                columns[2], // type
                                columns[3], // name
                                latitude, // latitude_deg
                                longitude, // longitude_deg
                                iataCode // iata_code
                        );
    
                        // Añadir el aeropuerto al mapa
                        cityToAirportMap
                                .computeIfAbsent(city, k -> new ArrayList<>())
                                .add(airport);
                                
                        // Añadir a la lista global de aeropuertos
                        allAirports.add(airport);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                } else {
                    System.out.println("Línea malformada en el archivo CSV: ");
                }
            }
        }
    
        // Depuración adicional para verificar los datos cargados
        // System.out.println("Datos cargados en cityToAirportMap:");
        // for (Map.Entry<String, List<Airport>> entry : cityToAirportMap.entrySet()) {
        //     System.out.println("Ciudad: " + entry.getKey());
        //     for (Airport airport : entry.getValue()) {
        //         System.out.println("    Aeropuerto: " + airport.getName() + ", IATA: " + airport.getIataCode());
        //     }
        // }
    }
    
    public List<Airport> getAirportsByCoordinates(double latitude, double longitude, double tolerance) {
        List<Airport> nearbyAirports = new ArrayList<>();
        for (Airport airport : allAirports) {
            double distance = DistanceCalculator.calculateDistance(
                    latitude, longitude,
                    airport.getLatitude(), airport.getLongitude()
            );
            if (distance <= tolerance) { // Tolerance in km (e.g., 50 km)
                nearbyAirports.add(airport);
            }
        }
        return nearbyAirports;
    }

    // Clase interna para representar un aeropuerto
    public static class Airport {
        private final String ident;
        private final String type;
        private final String name;
        private final double latitude;
        private final double longitude;
        private final String iataCode;

        public Airport(String ident, String type, String name, double latitude, double longitude, String iataCode) {
            this.ident = ident;
            this.type = type;
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.iataCode = iataCode;
        }

        public String getIdent() {
            return ident;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getIataCode() {
            return iataCode;
        }

        @Override
        public String toString() {
            return "Airport{" +
                   
                   
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
