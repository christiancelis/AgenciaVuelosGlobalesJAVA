package user.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;

import com.agenciadevuelosglobales.Menu.GenerarPermisos;
import com.agenciadevuelosglobales.Menu.MenuManager;

import trip.application.GetAllTrip;
import trip.domain.ServiceTrip;
import trip.domain.Trip;
import trip.infrastructure.out.TripRepository;
import viaje.application.FlightService;
import viaje.domain.ServiceFlightRepository;
import viaje.infrastructure.in.ViajeController;
import viaje.infrastructure.out.FlightRepositoryImpl;

public class MenuUserCliente {
    Scanner scanner = new Scanner(System.in);
    GenerarPermisos menuPermisos = new GenerarPermisos();
    private ServiceTrip serviceTrip;
    ServiceFlightRepository serviceFlightRepository = new FlightRepositoryImpl();
    FlightService flightService = new FlightService(serviceFlightRepository);
    FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();
    ViajeController viajeController = new ViajeController(flightRepository);


    public void Start(int rolId, int idUsu) {
        while (true) {
            System.out.println("==============================");
            System.out.println("         MENÚ CLIENTE");
            System.out.println("==============================");
            System.out.println("1. Acceder a vuelos");
            System.out.println("2. Acceder a reservas");
            System.out.println("3. Ver información de viajes");
            System.out.println("4. Salir");
            System.out.println("==============================");

            int choice = utils.Validation.leerNumero("Digita un numero: ", scanner);

            switch (choice) {
                case 1:
                    String vuelo = "viaje";
                    menuPermisos.getAllPermiso(vuelo, rolId, idUsu);
                    break;
                case 2:
                    String reserva = "reserva";
                    menuPermisos.getAllPermiso(reserva, rolId, idUsu);
                    break;
                case 3:
                    mostrarInformacionDeViajesPaginado();
                    break;
                case 4:
                    System.out.println("Saliendo..");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    private void mostrarInformacionDeViajesPaginado() {
        ServiceTrip serviceTrip = new TripRepository();
        GetAllTrip getAllTripService = new GetAllTrip(serviceTrip);
        
        int limit = 5; 
        int offset = 0; 

        while (true) {
            ArrayList<Trip> listTrip = ((TripRepository) serviceTrip).getAllTrip(limit, offset);
            if (listTrip.isEmpty()) {
                System.out.println("No hay más viajes para mostrar.");
                break;
            }
            
            for (Trip viajes : listTrip) {
                System.out.println("==============================");
                System.out.println("ID VIAJE             : " + viajes.getId());
                System.out.println("✈️ AEROPUERTO ORIGEN  : " + viajes.getAeropuertoOrigen());
                System.out.println("🌆 CIUDAD ORIGEN     : " + viajes.getCiudadOrigen());
                System.out.println("✈️ AEROPUERTO DESTINO : " + viajes.getAeropuertoDestino());
                System.out.println("🌆 CIUDAD DESTINO    : " + viajes.getCiudadDestino());
                System.out.println("📅 FECHA DE SALIDA   : " + viajes.getFechaViaje());
                System.out.println("💵 PRECIO DE VIAJE   : $" + viajes.getPrecio());
                System.out.println("==============================\n");
            }
            
            System.out.println("1. Siguiente página");
            System.out.println("2. Página anterior");
            System.out.println("3. Salir");
            
            int choice = utils.Validation.leerNumero("Digita un numero: ", scanner);
            switch (choice) {
                case 1:
                    offset += limit;
                    break;
                case 2:
                    offset = Math.max(offset - limit, 0);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuUserVuelo() {
        while (true) {
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Buscar vuelos - MANTENIMIENTO");
                    break;
                case 2:
                    System.out.println("Seleccionar vuelo -  MANTENIMIENTO");
                    break;
                case 3:
                    System.out.println("Añadir pasajeros - MANTENIMIENTO");
                    break;
                case 4:
                    System.out.println("Seleccionar asientos - MANTENIMIENTO");
                    break;
                case 5:
                    System.out.println("Realizar pago");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuUserReserva() {
        while (true) {
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Consultar reserva de vuelo - MANTENIMIENTO");
                    break;
                case 2:
                    System.out.println("Cancelar reserva de vuelo-  MANTENIMIENTO");
                    break;
                case 3:
                    System.out.println("Modificar reserva de vuelo- MANTENIMIENTO");
                    break;
                case 4:
                    System.out.println("SALIENDO..");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}
