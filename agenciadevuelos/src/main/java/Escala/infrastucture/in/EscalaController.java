package Escala.infrastucture.in;

import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Escala.domain.Escala;
import Escala.domain.ServiceEscala;
import Escala.infrastucture.out.EscalaRepository;
import viaje.domain.FlightRecord;

public class EscalaController {

    private ServiceEscala escalaService;
    private Scanner scanner;

    public EscalaController(ServiceEscala escalaService, Scanner scanner) {
        this.escalaService = escalaService;
        this.scanner = scanner;
    }

    public void CreateEscala() {
        Scanner scanner = new Scanner(System.in);

        // Crear instancia del repositorio y servicio (dependiendo de tu configuración)
        EscalaRepository escalaRepository = new EscalaRepository();

        // Crear el controlador
        EscalaController escalaController = new EscalaController(escalaService, scanner);

        // Mostrar menú y permitir la creación de una nueva escala
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear nueva escala");
        // Agregar más opciones según sea necesario

        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                escalaController.CreateEscala();
                break;
            // Manejar otras opciones aquí
            default:
                System.out.println("Opción no válida.");
                break;
        }

        // Cerrar el scanner al final
        scanner.close();
    }
    }

