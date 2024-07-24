package user.infrastructure.in;

import java.util.Scanner;
import com.agenciadevuelosglobales.Menu.GenerarPermisos;

import airCrew.infrastructure.in.AirCrewMenu;
import documentType.application.UseCaseCreateDocumentType;
import documentType.application.UseCaseDeleteDocumentTypeById;
import documentType.application.UseCaseGetAllDocumentTypes;
import documentType.application.UseCaseGetDocumentTypeById;
import documentType.application.UseCaseUpdateDocumentType;
import documentType.domain.ServiceDocumentType;
import documentType.infrastructure.in.DocumentController;
import documentType.infrastructure.in.MenuDocument;
import documentType.infrastructure.out.DocumentTypeRepository;
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
import tarifa.domain.ServiceTarifa;
import tarifa.infrastructure.out.TarifaRepository;
import trayecto.application.insertVueloTrayecto;
import trayecto.domain.ServiceTrayecto;
import trayecto.infrastucture.in.TrayectoController;
import trayecto.infrastucture.out.TrayectoRepository;
import viaje.application.ListVuelos;
import viaje.domain.ServiceFlight;
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
    ServiceFlight serviceFlight = new FlightRepositoryImpl();
    ServiceTarifa serviceTarifa = new TarifaRepository();
    ViajeController viajeController = new ViajeController(serviceFlight, serviceTarifa);
    ServiceDocumentType serviceDocumentType = new DocumentTypeRepository();
    UseCaseGetAllDocumentTypes getAllDocumentTypes = new UseCaseGetAllDocumentTypes(serviceDocumentType);
    UseCaseGetDocumentTypeById getDocumentTypeById = new UseCaseGetDocumentTypeById(serviceDocumentType);
    UseCaseCreateDocumentType createDocumentType = new UseCaseCreateDocumentType(serviceDocumentType);
    UseCaseUpdateDocumentType updateDocumentType = new UseCaseUpdateDocumentType(serviceDocumentType);
    UseCaseDeleteDocumentTypeById deleteDocumentTypeById = new UseCaseDeleteDocumentTypeById(serviceDocumentType);

    DocumentController documentController = new DocumentController(getAllDocumentTypes, getDocumentTypeById,
            createDocumentType, updateDocumentType, deleteDocumentTypeById);
    GenerarPermisos menuPermisos = new GenerarPermisos();
    ServiceTrayecto serviceTrayecto = new TrayectoRepository();
      
        ListVuelos listVuelos = new ListVuelos(serviceFlight);
        insertVueloTrayecto insertVueloTrayecto = new insertVueloTrayecto(serviceTrayecto);
      TrayectoController trayectoController = new TrayectoController(serviceTrayecto, scanner, listVuelos,insertVueloTrayecto)  ;
    AirCrewMenu airCrewMenu = new AirCrewMenu();
    MenuDocument menuDocument = new MenuDocument();
    public void Start(int rolId, int idUsu) throws Exception {
        while (true) {
            System.out.println("==============================");
            System.out.println("     MENÚ ADMINISTRADOR");
            System.out.println("==============================");
            System.out.println("1. Gestionar Aviones");
            System.out.println("2. Gestionar Tripulaciones");
            System.out.println("3. Gestionar Trayectos");
            System.out.println("4. Gestionar Aeropuertos*");
            System.out.println("5. Gestionar Tarifas de viajes*");
            System.out.println("6. Gestionar documentación");
            System.out.println("7. Gestionar usuarios y permisos");
            System.out.println("8. Salir");
            System.out.println("==============================");

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    menuPermisos.getAllPermiso("avion", rolId, idUsu);
                    menuAdminAviones();
                    break;
                case 2:
                    menuPermisos.getAllPermiso("tripulacion", rolId, idUsu);
                    menuAdminTripulacion();
                    break;
                case 3:
                    menuPermisos.getAllPermiso("viaje", rolId, idUsu);
                    menuAdminTrayectos();
                    break;
                case 4:
                    menuPermisos.getAllPermiso("aeropuerto", rolId, idUsu);
                    menuAdminAeropuerto();
                    break;
                case 5:
                    menuPermisos.getAllPermiso("tarifa", rolId, idUsu);
                    menuAdminTarifa();
                    break;
                case 6:
                    menuPermisos.getAllPermiso("documentacion", rolId, idUsu);
                    menuAdminDocumentacion();
                    break;
                case 7:
                    System.out.println("Gestionar usuarios y permisos - EN MANTENIMIENTO");
                    MenuUsuario menuUsuario = new MenuUsuario();
                    menuUsuario.Start();
                    break;
                case 8:
                    System.out.println("Saliendo del sistema.");
                    return; // Sale del menú principal
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminAviones() {
        while (true) {
            System.out.println("==============================");
            System.out.println("     MENÚ AVIONES");
            System.out.println("==============================");
            System.out.println("1. Registrar Avión");
            System.out.println("2. Consultar Información de Avión");
            System.out.println("3. Consultar Historial de Aviones");
            System.out.println("4. Actualizar Información de Avión");
            System.out.println("5. Eliminar Avión");
            System.out.println("6. Salir");
            System.out.println("==============================");

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
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuAdminTripulacion() {
       

           airCrewMenu.start();
    }

    public void menuAdminTrayectos() throws Exception {

        while (true) {

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Consultando Información de Trayecto...");
                    viajeController.buscarVueloPorId();
                    break;
                case 2:
                    System.out.println("Asignando Aeronave a Trayecto...");
                    // Implementar lógica para asignar aeronave
                    break;
                case 3:
                    System.out.println("Actualizando Información de Trayecto...");
                    viajeController.UpdateVueloById();
                    break;
                case 4:
                    System.out.println("Eliminar Trayecto...");
                    viajeController.EliminarVuelo();
                    break;
                case 5:
                    System.out.println("Consultando Escalas de un Trayecto...");
                    // Implementar lógica para consultar escalas
                    break;
                case 6:
                    System.out.println("Actualizando Información de Escala...");
                    // Implementar lógica para actualizar información de escala
                    break;
                case 7:
                    System.out.println("Eliminando Escala...");
                    // Implementar lógica para eliminar escala
                    break;
                case 8:
                    System.out.println("Registrar Vuelo...");
                    // Implementar lógica para registrar vuelo
                    viajeController.start();
                    return;
                case 9:
                    System.out.println("Registrar Trayecto...");
                    trayectoController.CreateEscala();
                    // Implementar lógica para registrar escala
                    break;
                case 10:
                    System.out.println("Eliminar Vuelo...");
                    // Implementar lógica para eliminar vuelo
                    break;
                case 11:
                    System.out.println("Actualizar Información de Vuelo...");
                    // Implementar lógica para actualizar información de vuelo
                    break;
                case 12:
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
        menuDocument.start();
    }
}