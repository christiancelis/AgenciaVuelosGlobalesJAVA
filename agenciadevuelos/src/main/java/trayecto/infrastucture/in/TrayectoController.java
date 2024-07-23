package trayecto.infrastucture.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import trayecto.domain.Trayecto;
import trayecto.domain.ServiceTrayecto;
import viaje.domain.FlightRecord;
import viaje.application.ListVuelos;
import config.Geocoding;

public class TrayectoController {

    private List<Trayecto> trayectoList = new ArrayList<>();
    private ServiceTrayecto serviceTrayecto;
    private Scanner scanner;
    private ListVuelos listVuelos;

    public TrayectoController(ServiceTrayecto serviceTrayecto, Scanner scanner, ListVuelos listVuelos) {
        this.serviceTrayecto = serviceTrayecto;
        this.scanner = scanner;
        this.listVuelos = listVuelos;
    }

    public void CreateEscala() {
        boolean exitoso = false;

        while (!exitoso) {
            try {
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("INFO: EL TRAYECTO ES CUANDO HAY UN PUNTO A Y UN PUNTO B, E INTERNAMENTE PUEDEN HABER MAS VIAJES O 'ESCALAS'");
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

                System.out.println("\n=================================");
                System.out.println("         Crear Escala            ");
                System.out.println("=================================\n");

                String origin = getCityInput("origen");
                if (!cityExists(origin)) {
                    System.out.println("\n[Error] La ciudad de origen no es válida. Inténtelo de nuevo.\n");
                    continue;
                }

                String destination = getCityInput("destino");
                if (!cityExists(destination)) {
                    System.out.println("\n[Error] La ciudad de destino no es válida. Inténtelo de nuevo.\n");
                    continue;
                }

                // Validar que la ciudad de origen y destino no sean las mismas
                if (origin.equalsIgnoreCase(destination)) {
                    System.out.println("\n[Error] La ciudad de origen y la ciudad de destino no pueden ser la misma. Inténtelo de nuevo.\n");
                    continue;
                }

                // Listar todos los vuelos y obtener el ID del viaje
                MutableInteger idViaje = new MutableInteger();
                listarVuelosYObtenerID(idViaje);

                Geocoding.LocationDetails originDetails = Geocoding.getLocationDetails(origin);
                Geocoding.LocationDetails destinationDetails = Geocoding.getLocationDetails(destination);

                String originCity = originDetails.getCity();
                String destinationCity = destinationDetails.getCity();

                if (originCity == null || originCity.isEmpty()) {
                    System.out.println("\n[Error] No se pudo obtener la ciudad de origen. Inténtelo de nuevo.\n");
                    continue;
                }
                if (destinationCity == null || destinationCity.isEmpty()) {
                    System.out.println("\n[Error] No se pudo obtener la ciudad de destino. Inténtelo de nuevo.\n");
                    continue;
                }

                System.out.println("\n---------------------------------");
                System.out.println("Detalles de la Ciudad desde API:");
                System.out.println("Ciudad de Origen: " + originCity);
                System.out.println("Ciudad de Destino: " + destinationCity);
                System.out.println("---------------------------------\n");

                Trayecto trayecto = new Trayecto();
                trayecto.setOriginCity(originCity);
                trayecto.setDestinoCity(destinationCity);
                trayecto.setIdViaje(idViaje.getValue());

                trayectoList.add(trayecto);

                try {
                    serviceTrayecto.guardarTrayecto(trayecto);
                    System.out.println("\n[Éxito] Trayecto creado exitosamente.\n");
                    exitoso = true;
                } catch (Exception e) {
                    System.out.println("\n[Error] Error al guardar el registro del trayecto en la base de datos: " + e.getMessage() + "\n");
                }

            } catch (Exception e) {
                System.out.println("\n[Error] Ocurrió un error inesperado: " + e.getMessage() + "\n");
                // e.printStackTrace();
            }
        }
    }

    private String getCityInput(String cityType) {
        System.out.print("Ingrese la ciudad de " + cityType + ": ");
        return scanner.nextLine().trim();
    }

    private boolean cityExists(String city) {
        try {
            Geocoding.LocationDetails locationDetails = Geocoding.getLocationDetails(city);
            return locationDetails != null && locationDetails.getCity() != null;
        } catch (Exception e) {
            System.out.println("\n[Error] No se pudo validar la ciudad: " + e.getMessage() + "\n");
            return false;
        }
    }

    private void listarVuelosYObtenerID(MutableInteger idViaje) {
        ArrayList<FlightRecord> vuelos = listVuelos.execute();
        if (vuelos.isEmpty()) {
            System.out.println("\n[Error] No hay vuelos disponibles.\n");
            idViaje.setValue(-1);
            return;
        }

        // Uso de colores si la terminal lo soporta
        final String RESET = "\033[0m";  // Reset color
        final String GREEN = "\033[0;32m"; // Green color
        final String BLUE = "\033[0;34m"; // Blue color

        System.out.println(BLUE + "=================================" + RESET);
        System.out.println(BLUE + "       Listado de Vuelos         " + RESET);
        System.out.println(BLUE + "=================================" + RESET);

        // Encabezado
        System.out.printf("%-5s | %-25s | %-25s | %-25s | %-15s%n", "ID", "Ciudad Origen", "Aeropuerto Origen", "Ciudad Destino", "Aeropuerto Destino");
        System.out.println(BLUE + "----------------------------------------------------------" + RESET);

        // Mostrar los vuelos en un formato de tabla
        for (FlightRecord vuelo : vuelos) {
            System.out.printf("%-5d | %-25s | %-25s | %-25s | %-20s%n",
                              vuelo.getId(),
                              vuelo.getOriginCity(),
                              vuelo.getOriginAirport(),
                              vuelo.getDestinationCity(),
                              vuelo.getDestinationAirport());
        }
        System.out.println(BLUE + "=================================" + RESET);

        // Selección de ID del viaje
        while (true) {
            System.out.print(GREEN + "Seleccione el ID del viaje: " + RESET);
            int id = utils.Validation.leerNumero("", scanner);
            if (vuelos.stream().anyMatch(v -> v.getId() == id)) {
                idViaje.setValue(id);
                break;
            } else {
                System.out.println("\n[Error] ID de viaje no válido. Inténtelo de nuevo.\n");
            }
        }
    }

    // Clase interna mutable
    private static class MutableInteger {
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }



    

}
