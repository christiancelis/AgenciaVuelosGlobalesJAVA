package com.agenciadevuelosglobales.Menu;

import java.util.Scanner;

import user.infrastructure.in.MenuUserCliente;
import user.infrastructure.in.UserMenu;


public class MenuManager {
    private final Scanner scanner = new Scanner(System.in);
    
   

    public void startMainMenu() {
        try {
            while (true) {
                System.out.println("\n==============================");
                System.out.println("         MENÚ PRINCIPAL       ");
                System.out.println("==============================");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Ingresar a AGENCIA DE VUELOS");
                System.out.println("3. Salir\n");

                int choice = utils.Validation.leerNumero("Eliga la opción: ", scanner);

                switch (choice) {
                    case 1:
                        UserMenu userMenu = new UserMenu();
                        userMenu.MenuUser();
                        break;
                    case 2:
                        MenuUserCliente menuCliente = new MenuUserCliente();
                        System.out.println("\n==============================");
                        System.out.println("  Ingresando a la AGENCIA DE VUELOS...");
                        System.out.println("==============================\n");
                        menuCliente.Start(4, 4);
                        break;
                    case 3:
                        System.out.println("\n==============================");
                        System.out.println("      Saliendo del programa...");
                        System.out.println("==============================\n");
                        return; 
                    default:
                        System.out.println("\n==============================");
                        System.out.println("   Opción no válida. Por favor,");
                        System.out.println("        elige una opción válida.");
                        System.out.println("==============================\n");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el menú principal: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // public void showMenu() {
    //     ViajeController viajeController = new ViajeController(flightRepository);
    //     viajeController.start();
    // }
}
