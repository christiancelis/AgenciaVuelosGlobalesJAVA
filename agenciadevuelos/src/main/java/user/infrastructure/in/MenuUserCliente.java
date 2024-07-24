package user.infrastructure.in;
import java.util.Scanner;
import com.agenciadevuelosglobales.Menu.GenerarPermisos;
import viaje.application.GuardarVuelo;
import viaje.domain.ServiceFlight;
import viaje.infrastructure.in.ViajeController;
import viaje.infrastructure.out.FlightRepositoryImpl;

public class MenuUserCliente {
    Scanner scanner = new Scanner(System.in);
    GenerarPermisos menuPermisos = new GenerarPermisos();
    ServiceFlight serviceFlightRepository = new FlightRepositoryImpl();
    GuardarVuelo flightService = new GuardarVuelo(serviceFlightRepository);
    FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();
    ViajeController viajeController = new ViajeController(flightRepository, null);


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
                System.out.println("---------------------------------------------------");
                System.out.println("RECUERDA VER EL ID DE TU VIAJE PARA VER INFORMACION");
                System.out.println("---------------------------------------------------");
                    viajeController.buscarVueloPorId();
                    
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
                    case 6:
                    System.out.println("saliendo...");
                    return;
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
