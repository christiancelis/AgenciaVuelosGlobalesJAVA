package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportDatabase {
    private static final String FILE_PATH = "agenciadevuelos/src/main/java/airports.csv"; // Ruta al archivo CSV
    private Map<String, List<Airport>> cityToAirportMap = new HashMap<>();

    public AirportDatabase() throws IOException {
        loadDatabase();
    }

    private void loadDatabase() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            String header = br.readLine(); // Leer el encabezado
            System.out.println("Encabezado CSV: " + header); // Depuración

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",", -1); // El parámetro -1 incluye las columnas vacías

                // Verificar si la línea tiene suficientes columnas
                if (columns.length >= 16) {
                    try {
                        String city = columns[10].trim().toLowerCase(); // La columna 'municipality'
                        String iataCode = columns[12].trim(); // La columna 'iata_code'
                        double latitude = Double.parseDouble(columns[4].trim());
                        double longitude = Double.parseDouble(columns[5].trim());
                        String type = columns[2].trim().toLowerCase(); // La columna 'type'

                        // Solo añadir si 'type' contiene "airport" y el IATA code no está vacío
                        if (type.contains("airport") && !iataCode.isEmpty()) {
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

                            cityToAirportMap
                                    .computeIfAbsent(city, k -> new ArrayList<>())
                                    .add(airport);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir datos numéricos en la línea: " + line);
                    }
                } else {
                    System.out.println("Línea malformada en el archivo CSV: " + line);
                }
            }
        }
    }

    public List<Airport> getAirports(String city) {
        city = city.trim().toLowerCase();
        List<Airport> airports = cityToAirportMap.getOrDefault(city, new ArrayList<>());
        System.out.println("Aeropuertos para la ciudad '" + city + "': " + airports); // Depuración
        return airports;
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
                    "ident='" + ident + '\'' +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", iataCode='" + iataCode + '\'' +
                    '}';
        }
    }
}
