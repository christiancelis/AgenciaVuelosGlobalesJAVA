package user.infrastructure.in;

import java.util.Scanner;
import com.agenciadevuelosglobales.Menu.GenerarPermisos;

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

public class MenuUserTecnico {
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
        GenerarPermisos menuPermisos = new GenerarPermisos();

        while (true) {
            System.out.println("==============================");
            System.out.println("     MENÚ TÉCNICO");
            System.out.println("==============================");
            System.out.println("1. Gestionar Aviones");
            System.out.println("2. Gestionar Mantenimiento Aviones");
            System.out.println("3. Salir");
            System.out.println("==============================");
            
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    String avion = "avion";
                    menuPermisos.getAllPermiso(avion, rolId, idUsu);
                    menuTecAvion();
                    break;
                case 2:
                    String mantenimiento = "mantenimiento";
                    menuPermisos.getAllPermiso(mantenimiento, rolId, idUsu);
                    menuTecMantenimiento();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuTecAvion() {
        while (true) {

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Realizando consulta de información del avión...");
                    planeController.getPlane();
                    break;
                case 2:
                    System.out.println("Consultando historial de revisiones de aviones... - EN MANTENIMIENTO");
                    break;
                case 3:
                    System.out.println("Actualizando información del avión...");
                    planeController.updatePlaneByPlate();
                    break;
                case 4:
                    System.out.println("Saliendo del menú de aviones.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuTecMantenimiento() {
        while (true) {

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Registrando nueva revisión...");
                    break;
                case 2:
                    System.out.println("Actualizando información de revisión...");
                    break;
                case 3:
                    System.out.println("Eliminando revisión de mantenimiento...");
                    break;
                case 4:
                    System.out.println("Saliendo del menú de mantenimiento.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}
