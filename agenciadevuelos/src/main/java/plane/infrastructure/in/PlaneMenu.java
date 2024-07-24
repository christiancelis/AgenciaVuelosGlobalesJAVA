package plane.infrastructure.in;

import java.util.Scanner;


import plane.application.CreatePlane;
import plane.application.DeletePlaneByPlate;
import plane.application.GetAllPlanes;
import plane.application.GetPlaneByPlate;
import plane.application.UpdatePlaneByPlate;
import plane.domain.ServicePlane;
import plane.infrastructure.out.PlaneRepository;
import planeModel.application.GetAllModels;
import planeModel.application.GetModelById;
import planeModel.domain.ServiceModel;
import planeModel.infrastructure.out.ModelRepository;
import planeStatus.application.GetAllStatus;
import planeStatus.application.GetStatusById;
import planeStatus.domain.ServiceStatus;
import planeStatus.infrastructure.out.StatusRepository;

public class PlaneMenu {
    private final Scanner scanner = new Scanner(System.in);
    
    public void MenuAvion(){
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

        PlaneController planeController = new PlaneController(deletePlaneByPlate,getAllPlanes,
        updatePlaneByPlate,getAllStatus,createPlane,getAllModels,getPlaneByPlate,getModelById,getStatusById);

        
        Integer Opcion = utils.Validation.leerNumero("Digite una opcion: ", scanner);
        switch (Opcion) {
            case 1:
                planeController.registerPlane();
                break;
            case 2:
                planeController.updatePlaneByPlate();
            case 3:
                planeController.deletePlane();
            case 4:
                planeController.getPlane();
            default:
                break;
        }
        
    }
    
}