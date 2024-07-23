package viaje.infrastructure.in;

import viaje.application.ListVuelos;
import viaje.domain.FlightRecord;
import viaje.domain.ServiceFlight;
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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import plane.application.GetAllPlanes;
import plane.domain.Plane;
import plane.domain.ServicePlane;
import plane.infrastructure.out.PlaneRepository;


public class ViajeController {
    Scanner scanner = new Scanner(System.in);
    private static final double COST_PER_KM = 0.1; // Costo por kilómetro
    private static final double TOLERANCE_KM = 50; // Tolerancia en km para buscar aeropuertos cercanos

    private final ServiceFlight flightRepository;

    public ViajeController(ServiceFlight flightRepository) {
        this.flightRepository = flightRepository;
    }

  
   public void start() {
    try {
        List<FlightRecord> flightRecords = new ArrayList<>();
        double exchangeRate = 4000;

        while (true) {
            printHeader("Inicio del Registro de Vuelo");

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
                printInfoMessage("Aeropuerto de origen seleccionado: " + selectedOriginAirport.getCity() + " ("
                        + selectedOriginAirport.getName() + ")");

                // Mostrar y seleccionar aeropuerto de destino
                Viaje selectedDestinationAirport = chooseAirport(scanner, destinationAirports, "destino");
                if (selectedDestinationAirport == null) {
                    continue; // Volver a solicitar las ciudades si no se selecciona un aeropuerto
                }
                printInfoMessage(
                        "Aeropuerto de destino seleccionado: " + selectedDestinationAirport.getCity() + " ("
                                + selectedDestinationAirport.getName() + ")");

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
                printInfoMessage("Fecha y hora del viaje: " + dateTimeInput);

                Date travelDate = Date.valueOf(dateTimeInput.toLocalDate());
                Time travelTime = Time.valueOf(dateTimeInput.toLocalTime());
                ServicePlane servicePlane = new PlaneRepository();
                GetAllPlanes getAllPlanes = new GetAllPlanes(servicePlane);

                ArrayList<Plane> listaAviones = getAllPlanes.execute();
                System.out.println("\n-----------------------------------");
                System.out.println("ID\tModelo ID\tMatrícula");
                System.out.println("-------------------------------------\n");

                // Imprimir detalles de los aviones
                for (Plane plane : listaAviones) {
                    System.out.printf("%d\t%d\t%s%n",
                            plane.getId(),
                            plane.getModeloId(),
                            plane.getMatricula());
                }
                System.out.println("-------------------------------------\n");

                // Solicitar ID del avión
                int idAvion = utils.Validation.leerNumero("Ingrese el ID del avión: ", scanner);

                // Verificar si el ID del avión existe en la lista
                Optional<Plane> planeOptional = listaAviones.stream()
                        .filter(plane -> plane.getId() == idAvion)
                        .findFirst();

                if (planeOptional.isPresent()) {
                    // ID del avión es válido, crear el registro del vuelo
                    Plane selectedPlane = planeOptional.get();
                    String originCity = originDetails.getCity();
                    String destinationCity = destinationDetails.getCity();

                    // Crear el registro del vuelo
                    FlightRecord flightRecord = new FlightRecord(
                            selectedOriginAirport.getName(),
                            selectedDestinationAirport.getName(),
                            originCity,
                            destinationCity,
                            priceInCOP,
                            travelDate,
                            travelTime,
                            idAvion);

                    flightRecords.add(flightRecord);
                    // GenerarPermisos generarPermisos = new GenerarPermisos();
                    // Guardar el registro del vuelo en la base de datos
                    try {
                        flightRepository.GuardarViaje(flightRecord);
                        printInfoMessage("Registro de vuelo guardado con éxito: " + flightRecord);

                        // generarPermisos.getAllPermiso("vuelo", 1, 1005323441);
                    } catch (SQLException e) {
                        printErrorMessage("Error al guardar el registro del vuelo en la base de datos: " + e.getMessage());
                    }

                    // Salir del bucle después de calcular el precio
                    break;
                } else {
                    printErrorMessage("ID del avión no válido. Por favor, intente nuevamente.");
                }
            } catch (IOException e) {
                printErrorMessage("Error al obtener los detalles de ubicación: " + e.getMessage());
            } catch (Exception e) {
                printErrorMessage("Error al calcular la distancia: " + e.getMessage());
            }
        }
    } finally {
        System.out.println("XD"); // Asegúrate de cerrar el Scanner al final
    }
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (true) {
            System.out.print("Ingrese la fecha y hora del viaje (formato: yyyy-MM-dd HH:mm): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                printErrorMessage("Formato de fecha y hora no válido. Por favor, intente nuevamente.");
            }
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printInfoMessage(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void printHeader(String title) {
        System.out.println("\n==========================================");
        System.out.println("          " + title + "          ");
        System.out.println("==========================================");
    }

    public void EliminarVuelo() {
        ListVuelos listVuelos = new ListVuelos(flightRepository);
        List<FlightRecord> vuelos = listVuelos.execute();

        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos disponibles para mostrar.");
            return;
        }

        // Encabezado de la tabla
        System.out.printf("%-10s %-30s %-30s %-15s %-15s %-8s%n", "ID", "Origen", "Destino", "Precio", "Fecha", "Hora");
        System.out.println(new String(new char[100]).replace("\0", "-")); // Línea separadora

        // Mostrar vuelos
        for (FlightRecord vuelo : vuelos) {
            System.out.printf("%-10d %-30s %-30s %-15.2f %-15s %-8s%n",
                    vuelo.getId(),
                    vuelo.getOriginCity(),
                    vuelo.getDestinationCity(),
                    vuelo.getPrice(),
                    vuelo.getTravelDate(),
                    vuelo.getHora());
        }

        // Solicitar ID del vuelo a eliminar
        int id = utils.Validation.leerNumero("Digita el id a eliminar: ", scanner);

        // Eliminar el vuelo con el ID especificado
        flightRepository.EliminarVueloById(id);
    }

    public void UpdateVueloById() throws Exception {
        ListVuelos listVuelos = new ListVuelos(flightRepository);
        List<FlightRecord> vuelos = listVuelos.execute();

        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos disponibles para mostrar.");
            return;
        }

        // Encabezado de la tabla
        System.out.printf("%-10s %-30s %-30s %-15s %-15s %-8s%n", "ID", "Origen", "Destino", "Precio", "Fecha", "Hora");
        System.out.println(new String(new char[100]).replace("\0", "-")); // Línea separadora

        // Mostrar vuelos
        for (FlightRecord vuelo : vuelos) {
            System.out.printf("%-10d %-30s %-30s %-15.2f %-15s %-8s%n",
                    vuelo.getId(),
                    vuelo.getOriginCity(),
                    vuelo.getDestinationCity(),
                    vuelo.getPrice(),
                    vuelo.getTravelDate(),
                    vuelo.getHora());
        }

        // Solicitar ID del vuelo a actualizar
        int id = utils.Validation.leerNumero("Digita el id del vuelo a actualizar: ", scanner);

        // Buscar el vuelo con el ID especificado
        FlightRecord vuelo = flightRepository.obtenerVueloById(id);

        if (vuelo == null) {
            System.out.println("Vuelo con ID " + id + " no encontrado.");

            return;
        }

        // Mostrar opciones de campos a actualizar
        System.out.println("¿Qué campo deseas actualizar?");
        System.out.println("1. Aeropuerto de origen");
        System.out.println("2. Ciudad de origen");
        System.out.println("3. Aeropuerto de destino");
        System.out.println("4. Ciudad de destino");
        System.out.println("5. Precio");
        System.out.println("6. Fecha del viaje");
        System.out.println("7. Hora del viaje");
        System.out.println("8. Actualizar todos los campos");
        System.out.print("Selecciona una opción (1-8): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (choice) {
            case 1: {
                String origin = getCityInput(scanner, "origen");
                Geocoding.LocationDetails originDetails = Geocoding.getLocationDetails(origin);
                AirportDatabase airportDatabase = new AirportDatabase();
                List<Viaje> originAirports = airportDatabase.findAirportsByCoordinates(
                        originDetails.getLat(), originDetails.getLon(), TOLERANCE_KM);
                if (originAirports.isEmpty()) {
                    printErrorMessage("No se encontraron aeropuertos cercanos a la ciudad de origen.");
                    return;
                }
                Viaje selectedOriginAirport = chooseAirport(scanner, originAirports, "origen");
                if (selectedOriginAirport != null) {
                    vuelo.setOriginAirport(selectedOriginAirport.getName());
                    vuelo.setOriginCity(originDetails.getCity());
                }
                break;
            }
            case 2: {
                String originCity = getCityInput(scanner, "origen");
                vuelo.setOriginCity(originCity);
                break;
            }
            case 3: {
                String destination = getCityInput(scanner, "destino");
                Geocoding.LocationDetails destinationDetails = Geocoding.getLocationDetails(destination);
                AirportDatabase airportDatabase = new AirportDatabase();
                List<Viaje> destinationAirports = airportDatabase.findAirportsByCoordinates(
                        destinationDetails.getLat(), destinationDetails.getLon(), TOLERANCE_KM);
                if (destinationAirports.isEmpty()) {
                    printErrorMessage("No se encontraron aeropuertos cercanos a la ciudad de destino.");
                    return;
                }
                Viaje selectedDestinationAirport = chooseAirport(scanner, destinationAirports, "destino");
                if (selectedDestinationAirport != null) {
                    vuelo.setDestinationAirport(selectedDestinationAirport.getName());
                    vuelo.setDestinationCity(destinationDetails.getCity());
                }
                break;
            }
            case 4: {
                String destinationCity = getCityInput(scanner, "destino");
                vuelo.setDestinationCity(destinationCity);
                break;
            }
            case 5: {
                double newPrice = utils.Validation.leerNumero("Ingrese el nuevo precio: ", scanner);
                vuelo.setPrice(newPrice);
                break;
            }
            case 6: {
                LocalDateTime dateTimeInput = getDateTimeInput(scanner);
                if (dateTimeInput != null) {
                    Date travelDate = Date.valueOf(dateTimeInput.toLocalDate());
                    vuelo.setTravelDate(travelDate);
                } else {
                    printErrorMessage("Fecha y hora no válidas.");
                    return;
                }
                break;
            }
            case 7: {
                LocalDateTime dateTimeInput = getDateTimeInput(scanner);
                if (dateTimeInput != null) {
                    Time travelTime = Time.valueOf(dateTimeInput.toLocalTime());
                    vuelo.setHora(travelTime);
                } else {
                    printErrorMessage("Fecha y hora no válidas.");
                    return;
                }
                break;
            }
            case 8: {
                // Solicitar nuevos datos para todos los campos
                String origin = getCityInput(scanner, "origen");
                String destination = getCityInput(scanner, "destino");

                if (origin.equalsIgnoreCase(destination)) {
                    printErrorMessage("La ciudad de origen y destino no pueden ser la misma.");
                    return;
                }

                Geocoding.LocationDetails originDetails = Geocoding.getLocationDetails(origin);
                Geocoding.LocationDetails destinationDetails = Geocoding.getLocationDetails(destination);

                AirportDatabase airportDatabase = new AirportDatabase();
                List<Viaje> originAirports = airportDatabase.findAirportsByCoordinates(
                        originDetails.getLat(), originDetails.getLon(), TOLERANCE_KM);
                List<Viaje> destinationAirports = airportDatabase.findAirportsByCoordinates(
                        destinationDetails.getLat(), destinationDetails.getLon(), TOLERANCE_KM);

                if (originAirports.isEmpty() || destinationAirports.isEmpty()) {
                    printErrorMessage("No se encontraron aeropuertos cercanos a las ciudades especificadas.");
                    return;
                }

                Viaje selectedOriginAirport = chooseAirport(scanner, originAirports, "origen");
                Viaje selectedDestinationAirport = chooseAirport(scanner, destinationAirports, "destino");

                if (selectedOriginAirport != null) {
                    vuelo.setOriginAirport(selectedOriginAirport.getName());
                    vuelo.setOriginCity(originDetails.getCity());
                }
                if (selectedDestinationAirport != null) {
                    vuelo.setDestinationAirport(selectedDestinationAirport.getName());
                    vuelo.setDestinationCity(destinationDetails.getCity());
                }

                double distance = DistanceCalculator.calculateDistance(
                        selectedOriginAirport.getLatitude(), selectedOriginAirport.getLongitude(),
                        selectedDestinationAirport.getLatitude(), selectedDestinationAirport.getLongitude());
                double priceInDollars = distance * COST_PER_KM;
                double priceInCOP = priceInDollars * 4000; // Tipo de cambio

                vuelo.setPrice(priceInCOP);

                LocalDateTime dateTimeInput = getDateTimeInput(scanner);
                if (dateTimeInput != null) {
                    vuelo.setTravelDate(Date.valueOf(dateTimeInput.toLocalDate()));
                    vuelo.setHora(Time.valueOf(dateTimeInput.toLocalTime()));
                }
                break;
            }
            default:
                printErrorMessage("Opción inválida.");
                return;
        }

        // Actualizar el vuelo en la base de datos
        try {
            flightRepository.ActualizarVuelo(vuelo);
            printInfoMessage("Vuelo actualizado: " + vuelo);
        } catch (SQLException e) {
            printErrorMessage("Error al actualizar el vuelo en la base de datos: " + e.getMessage());
        }
    }

    public void buscarVueloPorId() {
        System.out.print("Ingrese el ID del vuelo a buscar: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            printErrorMessage("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        try {
            FlightRecord vuelo = flightRepository.obtenerVueloById(id);

            if (vuelo == null) {
                printErrorMessage("Vuelo con ID " + id + " no encontrado.");
                return;
            }

            // Mostrar detalles del vuelo
            System.out.printf("%-10s %-30s %-30s %-15s %-15s %-8s%n", "ID", "Origen", "Destino", "Precio", "Fecha",
                    "Hora");
            System.out.println(new String(new char[100]).replace("\0", "-")); // Línea separadora
            System.out.printf("%-10d %-30s %-30s %-15.2f %-15s %-8s%n",
                    vuelo.getId(),
                    vuelo.getOriginCity(),
                    vuelo.getDestinationCity(),
                    vuelo.getPrice(),
                    vuelo.getTravelDate(),
                    vuelo.getHora());

        } catch (SQLException e) {
            printErrorMessage("Error al obtener el vuelo con ID " + id + " de la base de datos: " + e.getMessage());
        }
   
}
}
