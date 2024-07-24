package user.infrastructure.in;

import java.util.Scanner;
import com.agenciadevuelosglobales.Menu.GenerarPermisos;

import employee.application.UseCaseGetAllEmployees;
import employee.domain.ServiceEmployee;
import employee.infrastructure.out.EmployeeRepository;
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
import revision.application.UseCaseCreateRevision;
import revision.application.UseCaseDeleteRevision;
import revision.application.UseCaseGetAllRevision;
import revision.application.UseCaseGetRevisionById;
import revision.application.UseCaseUpdateRevision;
import revision.domain.ServiceRevision;
import revision.infrastructure.in.MenuRevision;
import revision.infrastructure.in.RevisionController;
import revision.infrastructure.out.RevisionRepository;

public class MenuUserTecnico {
    private Scanner scanner = new Scanner(System.in);
    ServiceStatus sServiceStatus = new StatusRepository();
    GetAllStatus getAllStatus = new GetAllStatus(sServiceStatus);
    ServiceModel serviceModel = new ModelRepository();
    GetAllModels getAllModels = new GetAllModels(serviceModel);
    ServicePlane servicePlane = new PlaneRepository();
    CreatePlane createPlane = new CreatePlane(servicePlane);
    DeletePlaneByPlate deletePlaneByPlate = new DeletePlaneByPlate(servicePlane);
    UpdatePlaneByPlate updatePlaneByPlate = new UpdatePlaneByPlate(servicePlane);
    GetPlaneByPlate getPlaneByPlate = new GetPlaneByPlate(servicePlane);
    GetModelById getModelById = new GetModelById(serviceModel);
    GetStatusById getStatusById = new GetStatusById(sServiceStatus);
    ServiceRevision serviceRevision = new RevisionRepository();
    ServiceEmployee serviceEmployee = new EmployeeRepository();
    UseCaseCreateRevision createRevision = new UseCaseCreateRevision(serviceRevision);
    UseCaseUpdateRevision updateRevision = new UseCaseUpdateRevision(serviceRevision);
    UseCaseDeleteRevision deleteRevision = new UseCaseDeleteRevision(serviceRevision);
    UseCaseGetAllRevision getAllRevision = new UseCaseGetAllRevision(serviceRevision);
    UseCaseGetRevisionById getRevisionById = new UseCaseGetRevisionById(serviceRevision);
    UseCaseGetAllEmployees getAllEmployees = new UseCaseGetAllEmployees(serviceEmployee);
    MenuRevision menuRevision = new MenuRevision();
    // controladores

    GetAllPlanes getAllPlanes = new GetAllPlanes(servicePlane);
    PlaneController planeController = new PlaneController(deletePlaneByPlate, getAllPlanes,
            updatePlaneByPlate, getAllStatus, createPlane, getAllModels, getPlaneByPlate, getModelById, getStatusById);
            //
    RevisionController revisionController = new RevisionController(createRevision, updateRevision, deleteRevision,
            getAllRevision, getRevisionById, getAllPlanes, getAllEmployees);

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
        menuRevision.start();
    }
}
