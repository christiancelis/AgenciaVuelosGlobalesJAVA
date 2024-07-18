package user.infrastructure.in;

import java.util.Scanner;

import com.agenciadevuelosglobales.Menu.GenerarPermisos;

import trip.application.GetAllTrip;
import trip.domain.ServiceTrip;
import trip.domain.Trip;
import trip.infrastructure.in.TripController;
import trip.infrastructure.out.TripRepository;


public class MenuUserCliente {

    Scanner scanner = new Scanner(System.in);
    GenerarPermisos menuPermisos = new GenerarPermisos();
    private ServiceTrip serviceTrip;
         
    public void Start(int rolId, int idUsu) {
        while (true) {
            System.out.println("==============================");
            System.out.println("         MENÚ CLIENTE");
            System.out.println("==============================");
            System.out.println("1. Acceder a vuelos");
            System.out.println("2. Acceder a reservas");
            System.out.println("3. Salir");
            System.out.println("==============================");

          
        ServiceTrip serviceTrip = new TripRepository(); // O cualquier implementación concreta de ServiceTrip
        GetAllTrip getAllTripService = new GetAllTrip(serviceTrip); // Asegúrate de pasar serviceTrip a GetAllTrip
        TripController tripController = new TripController(getAllTripService);
        tripController.ListAllTrip();
            

            int choice = utils.Validation.leerNumero("Digita un numero: ", scanner);

            switch (choice) {
                case 1:
                    String vuelo = "viaje";
                    menuPermisos.getAllPermiso(vuelo, rolId, idUsu);
                    break;
                case 2:
                    String reserva = "reserva";
                    menuPermisos.getAllPermiso(reserva, rolId, idUsu);
                    case 3:
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
