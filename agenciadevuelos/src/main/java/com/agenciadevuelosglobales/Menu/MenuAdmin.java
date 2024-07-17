package com.agenciadevuelosglobales.Menu;

import java.util.Scanner;

public class MenuAdmin {
    private Scanner scanner = new Scanner(System.in);

    public void Start(int rolId, int idUsu) {

        while (true) {
            System.out.println("1. Gestionar Aviones");
            System.out.println("2. Gestionar Tripulaciones");
            System.out.println("3. Gestionar Trayectos");
            System.out.println("4. Gestionar Aeropuertos");
            System.out.println("5. Gestionar Tarifas de viajes");
            System.out.println("6. Gestionar documentacion");
            System.out.println("7. Gestionar usuarios y permisos");
            System.out.println("8. Salir");

            MenuPermisos menuPermisos = new MenuPermisos();

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch(choice) {
                case 1:
                    String avion = "avion";
                    menuPermisos.getAllPermiso(avion, rolId, idUsu);
        
                    return;
                case 2:
                    String tripulacion = "tripulacion";
                    menuPermisos.getAllPermiso(tripulacion, rolId, idUsu);
                    return;
                case 3:
                    String viaje = "viaje";
                    menuPermisos.getAllPermiso(viaje, rolId, idUsu);
                    return;
                case 4:
                    String aeropuerto = "aeropuerto";
                    menuPermisos.getAllPermiso(aeropuerto, rolId, idUsu);
                    return;
                case 5:
                    String tarifa = "tarifa";
                    menuPermisos.getAllPermiso(tarifa, rolId, idUsu);
                    return;
                case 6:
                    String documentacion = "documentacion";
                    menuPermisos.getAllPermiso(documentacion, rolId, idUsu);
                    return;
                case 7:
                    MenuUsuario menuUsuario = new MenuUsuario();
                    menuUsuario.Start();

                    case 10:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    return;
            }
        }

    }

}
