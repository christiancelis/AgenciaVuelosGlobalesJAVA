package revision.infrastructure.in;
import java.util.Scanner;
import employee.application.UseCaseGetAllEmployees;
import employee.domain.ServiceEmployee;
import employee.infrastructure.out.EmployeeRepository;
import plane.application.GetAllPlanes;
import plane.domain.ServicePlane;
import plane.infrastructure.out.PlaneRepository;
import revision.application.UseCaseCreateRevision;
import revision.application.UseCaseDeleteRevision;
import revision.application.UseCaseGetAllRevision;
import revision.application.UseCaseGetRevisionById;
import revision.application.UseCaseUpdateRevision;
import revision.domain.ServiceRevision;
import revision.infrastructure.out.RevisionRepository;

public class MenuRevision {


    public void start(){
        Scanner scanner = new Scanner(System.in);
        ServiceRevision serviceRevision = new RevisionRepository();
        ServicePlane servicePlane = new PlaneRepository();
        ServiceEmployee serviceEmployee = new EmployeeRepository();
        UseCaseCreateRevision  createRevision = new UseCaseCreateRevision(serviceRevision);
        UseCaseUpdateRevision updateRevision = new UseCaseUpdateRevision(serviceRevision);
        UseCaseDeleteRevision deleteRevision = new UseCaseDeleteRevision(serviceRevision);
        UseCaseGetAllRevision getAllRevision = new UseCaseGetAllRevision(serviceRevision);
        UseCaseGetRevisionById getRevisionById = new UseCaseGetRevisionById(serviceRevision);
    
        GetAllPlanes getAllPlanes = new GetAllPlanes(servicePlane);
        UseCaseGetAllEmployees getAllEmployees = new UseCaseGetAllEmployees(serviceEmployee);
        
        RevisionController revisionController = new RevisionController(createRevision, updateRevision, deleteRevision, getAllRevision, getRevisionById, getAllPlanes, getAllEmployees);
        
        while (true) {
            
            int opcion = utils.Validation.leerNumero("Digite una opcion: ", scanner);
            switch (opcion) {
                case 1:
                    revisionController.CrearRevision();
                    break;
                case 2:
                    revisionController.updateRevision();
                    break;
                case 3: 
                    revisionController.deleteRevision();
                case 4:
                    return;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            imprimirMenu();
        }
       
    }


    private void imprimirMenu(){
        System.out.println("Menu Revision");
        System.out.println("1. Registrar Revision");
        System.out.println("2. Actualizar Revision");
        System.out.println("3. Eliminar Revision");
        System.out.println("4. Salir");
    }


   





}




