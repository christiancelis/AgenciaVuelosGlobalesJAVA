package plane.infrastructure.in;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import plane.application.CreatePlane;
import plane.application.DeletePlaneByPlate;
import plane.application.GetAllPlanes;
import plane.application.GetPlaneByPlate;
import plane.application.UpdatePlaneByPlate;
import plane.domain.Plane;
import planeModel.application.GetAllModels;
import planeModel.application.GetModelById;
import planeModel.domain.Model;
import planeStatus.application.GetAllStatus;
import planeStatus.application.GetStatusById;
import planeStatus.domain.Status;
import utils.Validation;

public class PlaneController {
    private final DeletePlaneByPlate deletePlaneByPlate;
    private final GetAllPlanes getAllPlanes;
    private final UpdatePlaneByPlate updatePlaneByPlate;
    private final GetAllStatus getAllStatus;
    private final CreatePlane createPlane;
    private final GetAllModels getAllModels;
    private final GetPlaneByPlate getPlaneByPlate;
    private final GetModelById getModelById;
    private final GetStatusById getStatusById;
    private final Scanner scanner = new Scanner(System.in);

    public PlaneController(DeletePlaneByPlate deletePlaneByPlate, GetAllPlanes getAllPlanes,
            UpdatePlaneByPlate updatePlaneByPlate, GetAllStatus getAllStatus, CreatePlane createPlane,
            GetAllModels getAllModels, GetPlaneByPlate getPlaneByPlate, GetModelById getModelById,
            GetStatusById getStatusById) {
        this.deletePlaneByPlate = deletePlaneByPlate;
        this.getAllPlanes = getAllPlanes;
        this.updatePlaneByPlate = updatePlaneByPlate;
        this.getAllStatus = getAllStatus;
        this.createPlane = createPlane;
        this.getAllModels = getAllModels;
        this.getPlaneByPlate = getPlaneByPlate;
        this.getModelById = getModelById;
        this.getStatusById = getStatusById;
    }

    public void registerPlane() {
        Plane avion = new Plane();
        String matricula = Validation.leerdato("Digite la matricula del avion: ", scanner);

        ArrayList<Plane> listaAviones = getAllPlanes.execute();
        Boolean matriculaExist = listaAviones.stream().anyMatch((e) -> e.getMatricula().equals(matricula));

        if (matriculaExist) {
            System.out.println("Error la matricula que esta digitando ya existe");
            return;
        }

        avion.setMatricula(matricula);
        avion.setCapacidad(Validation.leerNumero("Digite la capacidad del avion: ", scanner));
        avion.setFechaFabricacion(Validation.LeerFecha("Digite la fecha de Fabricacion (yyyy-mm-dd): ", scanner));

        ArrayList<Model> listaModelos = getAllModels.execute();
        listaModelos.forEach((e) -> System.out.println("id: " + e.getId() + " Nombre: " + e.getNombre()));

        Integer idModelo = Validation.leerNumero("Digite el codigo del Modelo: ", scanner);
        Boolean modeloExists = listaModelos.stream().anyMatch((e) -> e.getId() == idModelo);

        if (!modeloExists) {
            System.out.println("Error id Invalido");
            return;
        }
        avion.setModeloId(idModelo);

        ArrayList<Status> listaEstados = getAllStatus.execute();
        listaEstados.forEach((e) -> System.out.println("id: " + e.getId() + " Nombre: " + e.getName()));

        Integer idStatus = Validation.leerNumero("Digite el codigo del Status: ", scanner);
        Boolean a = listaEstados.stream().anyMatch((e) -> e.getId() == idStatus);
        if (!a) {
            System.out.println("Error id Invalido");
            return;
        }
        avion.setEstadoId(idStatus);

        try {
            createPlane.execute(avion);
            System.out.println("Avion creado exitosamente");
        } catch (Exception e) {
            System.out.println("Error al crear el avion");
        }

    }

    public void deletePlane() {
        while (true) {
            
        
        ArrayList<Plane> listaAviones = getAllPlanes.execute();
        listaAviones.forEach((e) -> System.out.println("id: " + e.getId() + " Matricula: " + e.getMatricula()));
        String matricula = Validation.leerdato("Digite la matricula del avion a eliminar: ", scanner);

        Boolean matriculaExist = listaAviones.stream().anyMatch((e) -> e.getMatricula().equals(matricula));
        

        if (!matriculaExist) {
            System.out.println("Error la matricula no existe en el sistema");
            
        }
        deletePlaneByPlate.execute(matricula);
    }
    }

    public void updatePlaneByPlate() {
        ArrayList<Plane> listaAviones = getAllPlanes.execute();
        listaAviones.forEach((e) -> System.out.println("Matricula: " + e.getMatricula()));
        String matricula = Validation.leerdato("Digite la matricula del avion a actualizar: ", scanner);

        Optional<Plane> ojt = listaAviones.stream().filter((e) -> e.getMatricula().equals(matricula)).findFirst();
        if (!ojt.isPresent()) {
            System.out.println("Error matricula no encontrada");
            return;
        }

        System.out.println("Datos del Avion");
        System.out.println("1.Capacidad");
        System.out.println("2.Fecha Fabricacion");
        System.out.println("3.Modelo");
        System.out.println("4.Status");
        int opcion = Validation.leerNumero("Digite la opcion correspondiente: ", scanner);

        Plane avion = ojt.get();
        switch (opcion) {
            case 1:
                System.out.println("\nValor actual: " + avion.getCapacidad());
                avion.setCapacidad(Validation.leerNumero("Digite el valor a actualizar: ", scanner));
                break;
            case 2:
                System.out.println("\nValor actual: " + avion.getFechaFabricacion());
                avion.setFechaFabricacion(Validation.LeerFecha("Digite el valor a actualizar: ", scanner));
                break;
            case 3:
                ArrayList<Model> listaModelos = getAllModels.execute();
                Optional<Model> mdl = listaModelos.stream().filter((e) -> e.getId().equals(avion.getModeloId()))
                        .findFirst();
                Model modelo = mdl.get();
                System.out.println("\nValor actual: " + modelo.getNombre());
                listaModelos.forEach((e) -> System.out.println("id: " + e.getId() + " Nombre: " + e.getNombre()));
                Integer idModelo = Validation.leerNumero("Digite el codigo del Modelo: ", scanner);
                Boolean modeloExists = listaModelos.stream().anyMatch((e) -> e.getId() == idModelo);
                if (!modeloExists) {
                    System.out.println("Error id Invalido");
                    return;
                }
                avion.setModeloId(idModelo);
                break;
            case 4:
                ArrayList<Status> listaEstados = getAllStatus.execute();
                Optional<Status> st = listaEstados.stream().filter((e) -> e.getId().equals(avion.getEstadoId()))
                        .findFirst();
                Status estado = st.get();
                System.out.println("\nValor actual: " + estado.getName());
                listaEstados.forEach((e) -> System.out.println("id: " + e.getId() + " Nombre: " + e.getName()));
                Integer idStatus = Validation.leerNumero("Digite el codigo del Status: ", scanner);
                Boolean a = listaEstados.stream().anyMatch((e) -> e.getId() == idStatus);
                if (!a) {
                    System.out.println("Error id Invalido");
                    return;
                }
                avion.setEstadoId(idStatus);
                break;
            default:
                break;
        }

        updatePlaneByPlate.execute(avion);
    }

    public void getPlane() {
        ArrayList<Plane> listaAviones = getAllPlanes.execute();
        System.out.println("-------------------------");
        listaAviones.forEach((e) -> System.out.println("Matricula |" + " " +e.getMatricula()));
        System.out.println("---------------------------");
        String matricula = Validation.leerdato("Digite la matricula del avion: ", scanner);
        Boolean matriculaExist = listaAviones.stream().anyMatch((e) -> e.getMatricula().equals(matricula));
        if (!matriculaExist) {
            System.out.println("Error la matricula no existe en el sistema");
            return;
        }

        Plane avion = getPlaneByPlate.execute(matricula);

        System.out.println("Informacion del avion");
        System.out.println("id : " + avion.getId());
        System.out.println("Matricula : " + avion.getMatricula());
        System.out.println("Capacidad : " + avion.getCapacidad());

        Model modelo = getModelById.execute(avion.getModeloId());
        Status estado = getStatusById.execute(avion.getEstadoId());

        System.out.println("Modelo: " + modelo.getNombre());
        System.out.println("Status: " + estado.getName());

    }

}
