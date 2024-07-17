package com.agenciadevuelosglobales.Menu;

import java.util.Scanner;

public class MenuVentas {

    private Scanner scanner = new Scanner(System.in);

    public void Start(int rolId, int idUsu) {
        while (true) {

            System.out.println("1. Gestionar clientes");
            System.out.println("2. Gestionar vuelos");
            System.out.println("3. Gestionar reservas");
            System.out.println("4. Salir");

            MenuPermisos menuPermisos = new MenuPermisos();

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    String cliente = "cliente";
                    menuPermisos.getAllPermiso(cliente, rolId, idUsu);
                    return;

                case 2:
                    String vuelo = "vuelo";
                    menuPermisos.getAllPermiso(vuelo, rolId, idUsu);
                    return;
                case 3:
                String reserva = "reserva";
                    menuPermisos.getAllPermiso(reserva, rolId, idUsu);
                    
                    break;
                case 4:
                    System.out.println("Salir");
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    return;
            }
        }
    }
}
