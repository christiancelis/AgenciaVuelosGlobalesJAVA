package reserva.infrastructure.in;

import reserva.application.CrearReserva;

import java.util.Scanner;

public class ReservaController {
    private CrearReserva crearReserva;

    public ReservaController(CrearReserva crearReserva) {
        this.crearReserva = crearReserva;
    }

    public void crearReserva() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del detalle de reserva:");
        int idDetalleReserva = scanner.nextInt();

        System.out.println("Ingrese el ID del cliente:");
        int idCliente = scanner.nextInt();

        System.out.println("Ingrese el ID de la reserva de viaje:");
        int idReservaViaje = scanner.nextInt();


        System.out.println("Ingrese el ID del viaje:");
        int viajeId = scanner.nextInt();

        // Llamar al método execute de CrearReserva
        crearReserva.execute(idDetalleReserva, idCliente, idReservaViaje, viajeId);

        System.out.println("Reserva creada exitosamente.");
    }
}
