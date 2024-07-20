package viaje.infrastructure.in;

import viaje.domain.FlightRecord;
import viaje.domain.ServiceFlightRepository;
import viaje.domain.Viaje;
import viaje.infrastructure.out.AirportDatabase;
import utils.DistanceCalculator;
import utils.Geocoding;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ViajeController {
    private static final double COST_PER_KM = 0.1; // Costo por kilómetro
    private static final double TOLERANCE_KM = 50; // Tolerancia en km para buscar aeropuertos cercanos

    private final ServiceFlightRepository flightRepository;

    public ViajeController(ServiceFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<FlightRecord> flightRecords = new ArrayList<>();
        double exchangeRate = 4000;

        while (true) {
            String origin = getCityInput(scanner, "origen");
            String destination = getCityInput(scanner, "destino");

            if (origin.equalsIgnoreCase(destination)) {
                printErrorMessage("La ciudad de origen y destino no pueden ser la misma.");
                continue; // Volver a solicitar las ciudades
            }

            try {
                Geocoding.LocationDetails originDetails = Geocoding.getLocationDetails(origin);
                Geocoding.LocationDetails destinationDetails = Geocoding.getLocationDetails(destination);

                AirportDatabase airportDatabase = new AirportDatabase();
                List<Viaje> originAirports = airportDatabase.findAirportsByCoordinates(
                        originDetails.getLat(), originDetails.getLon(), TOLERANCE_KM);
                List<Viaje> destinationAirports = airportDatabase.findAirportsByCoordinates(
                        destinationDetails.getLat(), destinationDetails.getLon(), TOLERANCE_KM);

                if (originAirports.isEmpty()) {
                    printErrorMessage("No se encontraron aeropuertos cerca de la ciudad de origen.");
                    continue; // Volver a solicitar las ciudades
                }

                if (destinationAirports.isEmpty()) {
                    printErrorMessage("No se encontraron aeropuertos cerca de la ciudad de destino.");
                    continue; // Volver a solicitar las ciudades
                }

                // Mostrar y seleccionar aeropuerto de origen
                Viaje selectedOriginAirport = chooseAirport(scanner, originAirports, "origen");
                if (selectedOriginAirport == null) {
                    continue; // Volver a solicitar las ciudades si no se selecciona un aeropuerto
                }
                printInfoMessage("Elegiste el aeropuerto de origen: " + selectedOriginAirport.getName() + " ("
                        + selectedOriginAirport.getCity() + ")");

                // Mostrar y seleccionar aeropuerto de destino
                Viaje selectedDestinationAirport = chooseAirport(scanner, destinationAirports, "destino");
                if (selectedDestinationAirport == null) {
                    continue; // Volver a solicitar las ciudades si no se selecciona un aeropuerto
                }
                printInfoMessage("Elegiste el aeropuerto de destino: " + selectedDestinationAirport.getName() + " ("
                        + selectedDestinationAirport.getCity() + ")");

                // Calcular distancia
                double distance = DistanceCalculator.calculateDistance(
                        selectedOriginAirport.getLatitude(), selectedOriginAirport.getLongitude(),
                        selectedDestinationAirport.getLatitude(), selectedDestinationAirport.getLongitude());
                String formattedDistance = String.format("%.2f", distance);
                // Calcular precio en dólares y convertir a pesos colombianos
                double priceInDollars = distance * COST_PER_KM;
                double priceInCOP = priceInDollars * exchangeRate;

                // Formatear el precio en pesos colombianos
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
                String formattedPrice = currencyFormat.format(priceInCOP);

                printInfoMessage("La distancia entre " + selectedOriginAirport.getName() + " y "
                        + selectedDestinationAirport.getName() + " es de " + formattedDistance + " km.");
                printInfoMessage("El precio calculado es: " + formattedPrice);

                // Solicitar la fecha y hora del viaje
                LocalDateTime dateTimeInput = getDateTimeInput(scanner);
                if (dateTimeInput == null) {
                    printErrorMessage("Fecha y hora no válidas.");
                    continue; // Volver a solicitar la fecha y hora si son inválidas
                }

                // Imprimir fecha y hora para depuración
                System.out.println("Fecha y hora del viaje: " + dateTimeInput);

                Date travelDate = Date.valueOf(dateTimeInput.toLocalDate());
                Time travelTime = Time.valueOf(dateTimeInput.toLocalTime());

                // Crear el registro del vuelo
                String originCity = originDetails.getCity();
                String destinationCity = destinationDetails.getCity();
                FlightRecord flightRecord = new FlightRecord(selectedOriginAirport, selectedDestinationAirport,
                        priceInCOP, travelDate, originCity, destinationCity, travelTime);
                flightRecords.add(flightRecord);

                // Guardar el registro del vuelo en la base de datos
                try {
                    flightRepository.GuardarViaje(flightRecord);
                    printInfoMessage("Registro de vuelo guardado: " + flightRecord);
                } catch (SQLException e) {
                    printErrorMessage("Error al guardar el registro del vuelo en la base de datos: " + e.getMessage());
                }

                // Salir del bucle después de calcular el precio
                break;
            } catch (IOException e) {
                printErrorMessage("Error al obtener los detalles de ubicación: " + e.getMessage());
            } catch (Exception e) {
                printErrorMessage("Error al calcular la distancia: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private String getCityInput(Scanner scanner, String cityType) {
        System.out.print("Ingrese la ciudad de " + cityType + ": ");
        return scanner.nextLine().trim();
    }

    private static Viaje chooseAirport(Scanner scanner, List<Viaje> airports, String cityType) {
        if (airports.size() == 1) {
            return airports.get(0); // Solo un aeropuerto disponible
        }

        System.out.println("Elija el número del aeropuerto en la ciudad de " + cityType + ":");
        for (int i = 0; i < airports.size(); i++) {
            Viaje airport = airports.get(i);
            System.out.println((i + 1) + ". " + airport.getCity() + " - " + airport.getName());
        }

        System.out.print("Número del aeropuerto (1-" + airports.size() + "): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= airports.size()) {
                return airports.get(choice - 1); // Ajustar índice (0 basado)
            } else {
                printErrorMessage("Número inválido. Por favor, intente nuevamente.");
                return null;
            }
        } catch (NumberFormatException e) {
            printErrorMessage("Entrada inválida. Por favor, ingrese un número.");
            return null;
        }
    }

    private static LocalDateTime getDateTimeInput(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        while (true) {
            System.out.print("Ingrese la fecha y hora del viaje (formato: yyyy-MM-dd HH:mm:ss): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                printErrorMessage("Formato de fecha y hora no válido. Por favor, intente nuevamente.");
            }
        }
    }

    private static void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    private static void printInfoMessage(String message) {
        System.out.println("[INFO] " + message);
    }
}
