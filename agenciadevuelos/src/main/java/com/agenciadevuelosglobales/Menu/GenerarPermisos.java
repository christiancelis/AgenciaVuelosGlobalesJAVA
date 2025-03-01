package com.agenciadevuelosglobales.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import tarifa.domain.ServiceTarifa;
import tarifa.infrastructure.out.TarifaRepository;
import user.application.GetAllPermisos.GetAllPermisos;
import user.domain.ServiceUser;
import user.domain.RolPermisoUsuario.RolPermiso;

import user.infrastructure.in.MenuUserAdmin;
import user.infrastructure.in.MenuUserCliente;
import user.infrastructure.in.MenuUserTecnico;
import user.infrastructure.in.MenuUserVentas;
import user.infrastructure.out.UserRepository;
import viaje.application.GuardarVuelo;
import viaje.domain.ServiceFlight;
import viaje.infrastructure.in.ViajeController;
import viaje.infrastructure.out.FlightRepositoryImpl;

public class GenerarPermisos {

    private final ServiceUser serviceUser;
    private final GetAllPermisos getAllPermisos;
    ServiceFlight serviceFlightRepository = new FlightRepositoryImpl();
    GuardarVuelo flightService = new GuardarVuelo(serviceFlightRepository);
    FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();
     ServiceTarifa serviceTarifa = new TarifaRepository();
    ViajeController viajeController = new ViajeController(flightRepository, serviceTarifa);


    public GenerarPermisos() {
        this.serviceUser = new UserRepository();
        this.getAllPermisos = new GetAllPermisos(serviceUser);
    }

    public ArrayList<RolPermiso> getAllPermiso(String validacion, int rolId, int idUsuario) {
        ArrayList<RolPermiso> permisos = new ArrayList<>();
        try {
            permisos = getAllPermisos.execute(validacion, rolId, idUsuario);
            System.out.println("Permisos obtenidos correctamente:");
            System.out.println("========================================");
            showPermisosInConsole(permisos, validacion, rolId, idUsuario);
        } catch (Exception e) {
            System.out.println("Error al obtener permisos: " + e.getMessage());
        }

        Map<Integer, Consumer<String>> roleMenus = getRoleMenus();
        roleMenus.getOrDefault(rolId, v -> System.out.println("Rol no reconocido")).accept(validacion);

        return permisos;
    }

    private void showPermisosInConsole(ArrayList<RolPermiso> permisos, String validacion, int rolId, int idUsuario) {
        ArrayList<RolPermiso> userPermisos = new ArrayList<>();
        for (RolPermiso permiso : permisos) {
            if (permiso.getIdRol() == rolId && permiso.getIdUsuario() == idUsuario && permiso.getValidacion().contains(validacion)) {
                userPermisos.add(permiso);
            }
        }

        // Agregar permiso "Salir"
        RolPermiso permisoSalir = new RolPermiso();
        permisoSalir.setNombrePermiso("Salir");
        permisoSalir.setValidacion(validacion);
        permisoSalir.setIdRol(rolId);
        permisoSalir.setIdUsuario(idUsuario);
        userPermisos.add(permisoSalir);

        int maxWidth = 50; // Define el ancho de la caja
        String formatString = "║ %-3d. %-"+ (maxWidth - 7) + "s ║\n";
        String horizontalLine = "╠" + "═".repeat(maxWidth) + "╣";
        String topLine = "╔" + "═".repeat(maxWidth) + "╗";
        String bottomLine = "╚" + "═".repeat(maxWidth) + "╝";

        System.out.println("\n" + topLine);
        System.out.printf("║ %-"+ maxWidth + "s ║\n", "Lista de Permisos");
        System.out.println(horizontalLine);

        for (int i = 0; i < userPermisos.size(); i++) {
            RolPermiso permiso = userPermisos.get(i);
            System.out.printf(formatString, i + 1, permiso.getNombrePermiso());
        }

        System.out.println(bottomLine + "\n");
    }

    private Map<Integer, Consumer<String>> getRoleMenus() {
        Map<Integer, Consumer<String>> roleMenus = new HashMap<>();

        roleMenus.put(1, t -> {
            try {
                adminMenu(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        roleMenus.put(2, this::techMenu);
        roleMenus.put(3, this::ventasMenu);
        roleMenus.put(4, this::clienteMenu);

        return roleMenus;
    }

    private void adminMenu(String validacion) throws Exception {
        MenuUserAdmin menuFinal = new MenuUserAdmin();

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
                System.out.println("ESTAMOS ENTRANDO A MENU DE AEROPUERTO\n");
                menuFinal.menuAdminAeropuerto();
                break;
            case "tarifa":
                System.out.println("ESTAMOS ENTRANDO A MENU DE TARIFA\n");
                menuFinal.menuAdminTarifa();
                break;
            case "documentacion":
                System.out.println("ESTAMOS ENTRANDO A MENU DE DOCUMENTACION\n");
                menuFinal.menuAdminDocumentacion();
                break;
            default:
                System.out.println("Validación no reconocida para el administrador.");
                break;
        }
    }

    private void techMenu(String validacion) {
        MenuUserTecnico menuUserTecnico = new MenuUserTecnico();
        System.out.println("Rol TECNICO");
        switch (validacion) {
            case "avion":
                System.out.println("ESTAMOS ENTRANDO A MENU DE AVION\n");
                menuUserTecnico.menuTecAvion();
                break;
            case "mantenimineto":
                System.out.println("ESTAMOS ENTRANDO A MENU DE MANTENIMIENTOS\n");
                menuUserTecnico.menuTecMantenimiento();
                break;
            default:
                break;
        }
    }

    private void ventasMenu(String validacion) {
        MenuUserVentas menuUserVentas = new MenuUserVentas();
        System.out.println("Rol VENTAS");
        switch (validacion) {
            case "cliente":
                System.out.println("ESTAMOS ENTRANDO A MENU DEL CLIENTE\n");
                menuUserVentas.menuUserCliente();
                break;
            case "reserva":
                System.out.println("ESTAMOS ENTRANDO A MENU DE RESERVAS\n");
                menuUserVentas.menuUserReserva();
                break;
            default:
                break;
        }
    }

    private void clienteMenu(String validacion) {
        MenuUserCliente menuUserCliente = new MenuUserCliente();
        System.out.println("ROL cliente");
        switch (validacion) {
            case "viaje":
                menuUserCliente.menuUserVuelo();
                break;
            case "reserva":
                menuUserCliente.menuUserReserva();
                break;
            default:
                break;
        }
    }

    public ArrayList<RolPermiso> getPermisos(int rolId, int idUsu) {
        // TODO: Implementar este método para obtener permisos específicos
        throw new UnsupportedOperationException("Método 'getPermisos' no implementado.");
    }
}
