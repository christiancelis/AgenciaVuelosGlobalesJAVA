package user.infrastructure.in;

import java.sql.Connection;
import java.util.Scanner;
import com.agenciadevuelosglobales.Menu.GenerarPermisos;

import config.DataBaseConfig;
import plane.application.CreatePlane;
import plane.application.DeletePlaneByPlate;
import plane.application.GetAllPlanes;
import plane.application.GetPlaneByPlate;
import plane.application.UpdatePlaneByPlate;
import plane.domain.ServicePlane;
import plane.infrastructure.in.PlaneController;
import plane.infrastructure.out.PlaneRepository;
import planeModel.application.GetAllModels;
import planeModel.application.GetModelById;
import planeModel.domain.ServiceModel;
import planeModel.infrastructure.out.ModelRepository;
import planeStatus.application.GetAllStatus;
import planeStatus.application.GetStatusById;
import planeStatus.domain.ServiceStatus;
import planeStatus.infrastructure.out.StatusRepository;
import utils.AirportDatabase;
import viaje.application.GuardarVuelo;
import viaje.domain.FlightRecord;
import viaje.domain.ServiceFlightRepository;
import viaje.infrastructure.in.ViajeController;
import viaje.infrastructure.out.FlightRepositoryImpl;

public class MenuUserAdmin {
    private Scanner scanner = new Scanner(System.in);

    ServiceStatus sServiceStatus = new StatusRepository();
    GetAllStatus getAllStatus = new GetAllStatus(sServiceStatus);
    ServiceModel serviceModel = new ModelRepository();
    GetAllModels getAllModels = new GetAllModels(serviceModel);
    ServicePlane servicePlane = new PlaneRepository();
    CreatePlane createPlane = new CreatePlane(servicePlane);
    GetAllPlanes getAllPlanes = new GetAllPlanes(servicePlane);
    DeletePlaneByPlate deletePlaneByPlate = new DeletePlaneByPlate(servicePlane);
    UpdatePlaneByPlate updatePlaneByPlate = new UpdatePlaneByPlate(servicePlane);
    GetPlaneByPlate getPlaneByPlate = new GetPlaneByPlate(servicePlane);
    GetModelById getModelById = new GetModelById(serviceModel);
    GetStatusById getStatusById = new GetStatusById(sServiceStatus);
    PlaneController planeController = new PlaneController(deletePlaneByPlate, getAllPlanes,
        updatePlaneByPlate, getAllStatus, createPlane, getAllModels, getPlaneByPlate, getModelById, getStatusById);

    public void Start(int rolId, int idUsu) {

        while (true) {
            System.out.println("==============================");
            System.out.println("     MENÚ ADMINISTRADOR");
            System.out.println("==============================");
            System.out.println("1. Gestionar Aviones");
            System.out.println("2. Gestionar Tripulaciones");
            System.out.println("3. Gestionar Trayectos");
            System.out.println("4. Gestionar Aeropuertos");
            System.out.println("5. Gestionar Tarifas de viajes");
            System.out.println("6. Gestionar documentación");
            System.out.println("7. Gestionar usuarios y permisos");
            System.out.println("8. Salir");
            System.out.println("==============================");
            

            GenerarPermisos menuPermisos = new GenerarPermisos();

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    String avion = "avion";
                    menuPermisos.getAllPermiso(avion, rolId, idUsu);
                    menuAdminAviones();
                    break;
                case 2:
                    String tripulacion = "tripulacion";
                    menuPermisos.getAllPermiso(tripulacion, rolId, idUsu);
                    menuAdminTripulacion();
                    break;
                case 3:
                    String trayecto = "trayecto";
                    menuPermisos.getAllPermiso(trayecto, rolId, idUsu);
                    menuAdminTrayectos();
                    break;
                case 4:
                    String aeropuerto = "aeropuerto";
                    menuPermisos.getAllPermiso(aeropuerto, rolId, idUsu);
                    menuAdminAeropuerto();
                    break;
                case 5:
                    String tarifa = "tarifa";
                    menuPermisos.getAllPermiso(tarifa, rolId, idUsu);
                    menuAdminTarifa();
                    break;
                case 6:
                    String documentacion = "documentacion";
                    menuPermisos.getAllPermiso(documentacion, rolId, idUsu);
                    menuAdminDocumentacion();
                    break;
                case 7:
                    System.out.println("Gestionar usuarios y permisos - EN MANTENIMIENTO");
                    MenuUsuario menuUsuario = new MenuUsuario();
                    menuUsuario.Start();
                    break;
                case 8:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminAviones() {
        while (true) {
 
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Registrando Avión...");
                    planeController.registerPlane();
                    break;
                case 2:
                    System.out.println("Consultando Información de Avión...");
                    planeController.getPlane();
                    break;
                case 3:
                    System.out.println("Consultando Historial de Aviones...");
                    break;
                case 4:
                    System.out.println("Actualizando Información de Avión...");
                    planeController.updatePlaneByPlate();
                    break;
                case 5:
                    System.out.println("Eliminando Avión...");
                    planeController.deletePlane();
                    break;
                case 6:
                    System.out.println("Saliendo del menú de Aviones.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminTripulacion() {
        while (true) {
  

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Asignando Tripulación...");
                    break;
                case 2:
                    System.out.println("Consultando Asignación de Tripulación...");
                    break;
                case 3:
                    System.out.println("Saliendo del menú de Tripulación.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminTrayectos() {
        while (true) {
     
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Consultando Información de Trayecto...");
                    break;
                case 2:
                    System.out.println("Asignando Aeronave a Trayecto...");
                   
                    break;
                case 3:
                    System.out.println("Actualizando Información de Trayecto...");
                    break;
                case 4:
                    System.out.println("Eliminando Trayecto...");
                    break;
                case 5:
                    System.out.println("Consultando Escalas de un Trayecto...");
                    break;
                case 6:
                    System.out.println("Actualizando Información de Escala...");
                    break;
                case 7:
                    System.out.println("Eliminando Escala...");
                    break;
                case 8:
                    System.out.println("Saliendo del menú de Trayectos.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminAeropuerto() {
        while (true) {
          
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Registrando Aeropuerto...");
                    break;
                case 2:
                    System.out.println("Consultando Información de Aeropuerto...");
                    break;
                case 3:
                    System.out.println("Actualizando Información de Aeropuerto...");
                    break;
                case 4:
                    System.out.println("Eliminando Aeropuerto...");
                    break;
                case 5:
                    System.out.println("Saliendo del menú de Aeropuertos.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminTarifa() {
        while (true) {
         
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Registrando Tarifa de Vuelo...");
                    break;
                case 2:
                    System.out.println("Actualizando Información de Tarifa de Vuelo...");
                    break;
                case 3:
                    System.out.println("Eliminando Tarifa de Vuelo...");
                    break;
                case 4:
                    System.out.println("Consultando Tarifa de Vuelo...");
                    break;
                case 5:
                    System.out.println("Saliendo del menú de Tarifas de Vuelo.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminDocumentacion() {
        while (true) {
    

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Registrando Tipo de Documento...");
                    break;
                case 2:
                    System.out.println("Actualizando Tipo de Documento...");
                    break;
                case 3:
                    System.out.println("Eliminando Tipo de Documento...");
                    break;
                case 4:
                    System.out.println("Consultando Tipo de Documento...");
                    break;
                case 5:
                    System.out.println("Saliendo del menú de Documentación.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}
