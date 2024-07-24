

package trayecto.infrastucture.in;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import trayecto.domain.Trayecto;
import trayecto.application.insertVueloTrayecto;
import trayecto.domain.ServiceTrayecto;
import utils.Geocoding;
import viaje.application.ListVuelos;
import viaje.domain.FlightRecord;



public class TrayectoController {

    private List<Trayecto> trayectoList = new ArrayList<>();
    private ServiceTrayecto serviceTrayecto;
    private Scanner scanner;
    private ListVuelos listVuelos;
    private insertVueloTrayecto insertVueloTrayecto;

    public TrayectoController(ServiceTrayecto serviceTrayecto, Scanner scanner, ListVuelos listVuelos,insertVueloTrayecto insertVueloTrayecto) {
        this.serviceTrayecto = serviceTrayecto;
        this.scanner = scanner;
        this.listVuelos = listVuelos;
        this.insertVueloTrayecto = insertVueloTrayecto;
    }

    public void CreateEscala() {
        boolean continuar = true;
    
        while (continuar) {
            boolean exitoso = false;
    
            while (!exitoso) {
                try {
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("INFO: EL TRAYECTO ES CUANDO HAY UN PUNTO A Y UN PUNTO B, E INTERNAMENTE PUEDEN HABER MAS VIAJES O 'ESCALAS'");
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                    System.out.println("INFO: Escribe ''salir'' para salir del programa");
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                    System.out.println("\n=================================");
                    System.out.println("         Crear Trayecto           ");
                    System.out.println("=================================\n");
    
                    String origin = getCityInput("origen");
                    if (origin.equalsIgnoreCase("salir")) return; // Salir si el usuario ingresa "salir"
                    Geocoding.LocationDetails originDetails = obtenerYSeleccionarCiudad(origin);
                    if (originDetails == null) {
                        System.out.println("\n[Error] La ciudad de origen no es válida. Inténtelo de nuevo.\n");
                        continue;
                    }
    
                    String destination = getCityInput("destino");
                    if (destination.equalsIgnoreCase("salir")) return; // Salir si el usuario ingresa "salir"
                    Geocoding.LocationDetails destinationDetails = obtenerYSeleccionarCiudad(destination);
                    if (destinationDetails == null) {
                        System.out.println("\n[Error] La ciudad de destino no es válida. Inténtelo de nuevo.\n");
                        continue;
                    }
    
                    // Validar que la ciudad de origen y destino no sean las mismas
                    if (originDetails.getCity().equalsIgnoreCase(destinationDetails.getCity()) &&
                        originDetails.getCountry().equalsIgnoreCase(destinationDetails.getCountry())) {
                        System.out.println("\n[Error] La ciudad de origen y la ciudad de destino no pueden ser la misma. Inténtelo de nuevo.\n");
                        continue;
                    }
                    
                    MutableInteger idViaje = new MutableInteger();
                    listarVuelosYObtenerID(idViaje);
    
                    String originCity = originDetails.getCity();
                    String destinationCity = destinationDetails.getCity();
    
                    System.out.println("\n---------------------------------");
                    System.out.println("Detalles de la Ciudad desde API:");
                    System.out.println("Ciudad de Origen: " + originCity + ", " + originDetails.getCountry());
                    System.out.println("Ciudad de Destino: " + destinationCity + ", " + destinationDetails.getCountry());
                    System.out.println("---------------------------------\n");
    
                    Trayecto trayecto = new Trayecto();
                    
                    trayecto.setOriginCity(originCity);
                    trayecto.setDestinoCity(destinationCity);
                    
                    trayectoList.add(trayecto);
                    int idViaje1 = idViaje.getValue();
    
                    try {
                        int idTrayecto = serviceTrayecto.GuardarTrayecto(trayecto);
                        insertVueloTrayecto.execute(idViaje1, idTrayecto);
                        System.out.println("\n[Éxito] Trayecto creado exitosamente.\n");
                        exitoso = true;
                    } catch (Exception e) {
                        System.out.println("\n[Error] Error al guardar el registro del trayecto en la base de datos: " + e.getMessage() + "\n");
                    }
                } catch (Exception e) {
                    System.out.println("\n[Error] Ocurrió un error inesperado: " + e.getMessage() + "\n");
                }
            }
    
            // Preguntar al usuario si desea agregar otro vuelo
            System.out.print("¿Desea agregar otro vuelo al trayecto? (sí/no): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("no")) {
                continuar = false;
                System.out.println("\n[Información] Proceso terminado. No se agregarán más vuelos.\n");
            } else if (!respuesta.equals("si")) {
                System.out.println("\n[Error] Respuesta no válida. Se asumirá que no desea continuar.\n");
                continuar = false;
            }
        }
    }
    private String getCityInput(String cityType) {
        System.out.print("Ingrese la ciudad de " + cityType + ": ");
        String cityInput = scanner.nextLine().trim();
        // Reemplazar los espacios con guiones bajos o cualquier otro carácter
        return cityInput.replace(" ", "_");
    }
    private Geocoding.LocationDetails obtenerYSeleccionarCiudad(String city) {
        try {
            List<Geocoding.LocationDetails> locations = getLocations(city);
    
            if (locations.size() == 1) {
                return locations.get(0);
            } else if (locations.size() > 1) {
                System.out.println("Se encontraron múltiples resultados para la ciudad ingresada:");
                for (int i = 0; i < locations.size(); i++) {
                    Geocoding.LocationDetails location = locations.get(i);
                    System.out.printf("%d: %s, %s\n", i + 1, location.getCity(), location.getCountry());
                }
    
                while (true) {
                    System.out.print("Seleccione el número correspondiente a la ciudad correcta: ");
                    int seleccion = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
    
                    if (seleccion > 0 && seleccion <= locations.size()) {
                        return locations.get(seleccion - 1);
                    } else {
                        System.out.println("Selección inválida. Inténtelo de nuevo.");
                    }
                }
            } else {
                System.out.println("No se encontraron resultados para la ciudad: " + city);
                return null;
            }
        } catch (Exception e) {
            System.out.println("\n[Error] No se pudo validar la ciudad: " + e.getMessage() + "\n");
            return null;
        }
    }
    

    private List<Geocoding.LocationDetails> getLocations(String city) throws Exception {
        String url = "https://nominatim.openstreetmap.org/search?q=" + city + "&format=json&addressdetails=1";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        List<Geocoding.LocationDetails> locations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");
                JSONObject address = jsonObject.getJSONObject("address");

                String cityName = address.optString("city", 
                            address.optString("town", 
                            address.optString("village", 
                            address.optString("county", "Unknown city"))));
                String countryName = address.optString("country", "Unknown country");

                locations.add(new Geocoding.LocationDetails(lat, lon, cityName, countryName));
            }
        }

        return locations;
    }


    private void listarVuelosYObtenerID(MutableInteger idViaje) {
        ArrayList<FlightRecord> vuelos = listVuelos.execute();
        if (vuelos.isEmpty()) {
            System.out.println("\n[Error] No hay vuelos disponibles.\n");
            idViaje.setValue(-1);
            return;
        }
    
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
            System.out.print(GREEN + "Seleccione el ID del viaje (o escriba 'salir' para cancelar): " + RESET);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("salir")) return; // Salir si el usuario ingresa "salir"
    
            try {
                int id = Integer.parseInt(input);
                if (vuelos.stream().anyMatch(v -> v.getId() == id)) {
                    idViaje.setValue(id);
                    break;
                } else {
                    System.out.println("\n[Error] ID de viaje no válido. Inténtelo de nuevo.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[Error] Entrada no válida. Por favor, ingrese un número válido o 'salir'.\n");
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
    


