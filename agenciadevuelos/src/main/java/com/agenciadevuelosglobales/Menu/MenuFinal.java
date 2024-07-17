package com.agenciadevuelosglobales.Menu;

import java.util.Scanner;

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

public class MenuFinal {
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
    int choice = utils.Validation.leerNumero(" Diita el numero: ", scanner);

    public void menuAdminAviones() {

        switch (choice) {
            case 1:
                System.out.println("REGISTRAR AVIONES");
                planeController.registerPlane();
                return;
            case 2:
                System.out.println("CONSULTAR INFORMACION DE AVION");
                planeController.getPlane();
                return;
            case 3:
                System.out.println("HISTORIAL DE AVIONES - EN MANTENIMIENTO");
                return;
            case 4:

                System.out.println("ACTUALIZAR INFORMACION DE AVION");
                planeController.updatePlaneByPlate();
                return;
            case 5:
                System.out.println("ELIMINAR AVIONES");
                planeController.deletePlane();
                return;
            default:
                break;
        }

    }

    public void menuAdminTripulacion() {
        switch (choice) {
            case 1:
                System.out.println("Asignar Tripulación a Trayecto");
                break;
            case 2:
                System.out.println("Consultar Asignación de Tripulación");
                break;
            case 3:
                System.out.println("Salir");
                return;
            default:
                break;
        }
    }

    public void menuAdminTrayectos() {
        switch (choice) {
            case 1:
            System.out.println("Consultar Información de Trayecto");
                break;
            case 2:
            System.out.println("Asignar Aeronave a Trayecto");
                break;
            case 3:
            System.out.println("Actualizar Información de Trayecto");
                break;
                
            case 4:
            System.out.println("Eliminar Trayecto");
                break;
            case 5:
            System.out.println("Consultar Escalas de un Trayecto");
                break;
            case 6:
            System.out.println("Actualizar Información de Escala");
                break;
            case 7:
            System.out.println("Eliminar Escala");
                break;
            case 8:
            System.out.println("Saliendo..");
                return;
            default:
                break;
        }
    }
}
