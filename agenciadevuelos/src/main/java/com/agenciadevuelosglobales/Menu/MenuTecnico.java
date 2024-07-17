package com.agenciadevuelosglobales.Menu;

import java.util.Scanner;

public class MenuTecnico {
    private Scanner scanner = new Scanner(System.in);

    public void Start(int rolId,int idUsu){
        while (true) {
        
            System.out.println("1. Gestionar Aviones");
            System.out.println("2. Gestionar Mantenimiento Aviones");
            System.out.println("3. Salir");

            MenuPermisos menuPermisos = new MenuPermisos();

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    String avion = "avion";
                    menuPermisos.getAllPermiso(avion, rolId, idUsu);
        
                    return;
                case 2:
                    String mantenimiento = "mantenimiento";
                    menuPermisos.getAllPermiso(mantenimiento, rolId, idUsu);
                    return;
                case 3:
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    return;
            }
        }
        }
    }

