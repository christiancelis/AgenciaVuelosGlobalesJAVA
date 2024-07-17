package com.agenciadevuelosglobales.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import user.application.GetAllPermisos.GetAllPermisos;
import user.domain.ServiceUser;
import user.domain.RolPermisoUsuario.RolPermiso;
import user.infrastructure.out.UserRepository;

public class MenuPermisos {

    private final ServiceUser serviceUser;
    private final GetAllPermisos getAllPermisos;

    public MenuPermisos() {
        this.serviceUser = new UserRepository();
        this.getAllPermisos = new GetAllPermisos(serviceUser);
    }

    public ArrayList<RolPermiso> getAllPermiso(String validacion, int rolId, int idUsuario) {
        ArrayList<RolPermiso> permisos = getAllPermisos.execute(validacion, rolId, idUsuario);

        System.out.println("Permisos obtenidos correctamente:");
        System.out.println("********************");
        
        printPermisos(permisos, validacion, rolId, idUsuario);

        Map<Integer, Consumer<String>> roleMenus = getRoleMenus();

        roleMenus.getOrDefault(rolId, v -> System.out.println("Rol no reconocido")).accept(validacion);

        return permisos;
    }

    private void printPermisos(ArrayList<RolPermiso> permisos, String validacion, int rolId, int idUsuario) {
        int num = 1;
        for (RolPermiso rolPermiso : permisos) {
            if (rolPermiso.getValidacion().contains(validacion) &&
                rolPermiso.getIdRol() == rolId &&
                rolPermiso.getIdUsuario() == idUsuario) {
                System.out.println(num++ + ". " + rolPermiso.getNombrePermiso());
            }
        }
    }

    private Map<Integer, Consumer<String>> getRoleMenus() {
        Map<Integer, Consumer<String>> roleMenus = new HashMap<>();
        
        roleMenus.put(1, this::adminMenu);
        roleMenus.put(2, this::techMenu);
        roleMenus.put(3, this::thirdRoleMenu);
        roleMenus.put(4, this::fourthRoleMenu);
        
        return roleMenus;
    }

    private void adminMenu(String validacion) {
        MenuFinal menuFinal = new MenuFinal();
        switch (validacion) {
            case "avion":
                System.out.println("ESTAMOS ENTRANDO A MENU DE AVIONES\n");
                menuFinal.menuAdminAviones();
                break;
            case "tripulacion":
                System.out.println("ESTAMOS ENTRANDO A MENU DE TRIPULACION\n");
                menuFinal.menuAdminTripulacion();
                break;

            case "viaje":
            System.out.println("ESTAMOS ENTRANDO A MENU DE TRAYECTOS\n");
            menuFinal.menuAdminTrayectos();
            break;
            case "aeropuerto":
            break;

            case "tarifa":

            default:
                System.out.println("Validación no reconocida para administrador.");
                break;
        }
    }

    private void techMenu(String validacion) {
        System.out.println("Rol TECNICO");
    }

    private void thirdRoleMenu(String validacion) {
        // Implementar el menú del tercer rol
    }

    private void fourthRoleMenu(String validacion) {
        // Implementar el menú del cuarto rol
    }
}
