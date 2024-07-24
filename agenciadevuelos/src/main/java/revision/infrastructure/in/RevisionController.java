package revision.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import employee.application.UseCaseGetAllEmployees;
import employee.domain.Employee;
import plane.application.GetAllPlanes;
import plane.domain.Plane;
import revision.application.UseCaseCreateRevision;
import revision.application.UseCaseDeleteRevision;
import revision.application.UseCaseGetAllRevision;
import revision.application.UseCaseGetRevisionById;
import revision.application.UseCaseUpdateRevision;
import revision.domain.Revision;
import utils.Validation;

public class RevisionController {
    private final UseCaseCreateRevision useCaseCreateRevision;
    private final UseCaseUpdateRevision useCaseUpdateRevision;
    private final UseCaseDeleteRevision useCaseDeleteRevision;
    private final UseCaseGetAllRevision useCaseGetAllRevision;
    private final UseCaseGetRevisionById useCaseGetRevisionById;
    private final GetAllPlanes useCaseGetAllPlanes;
    private final UseCaseGetAllEmployees useCaseGetAllEmployees;
    private final Scanner scanner = new Scanner(System.in);

    
    public RevisionController(UseCaseCreateRevision useCaseCreateRevision, UseCaseUpdateRevision useCaseUpdateRevision,
            UseCaseDeleteRevision useCaseDeleteRevision, UseCaseGetAllRevision useCaseGetAllRevision,
            UseCaseGetRevisionById useCaseGetRevisionById, GetAllPlanes useCaseGetAllPlanes,
            UseCaseGetAllEmployees useCaseGetAllEmployees) {
        this.useCaseCreateRevision = useCaseCreateRevision;
        this.useCaseUpdateRevision = useCaseUpdateRevision;
        this.useCaseDeleteRevision = useCaseDeleteRevision;
        this.useCaseGetAllRevision = useCaseGetAllRevision;
        this.useCaseGetRevisionById = useCaseGetRevisionById;
        this.useCaseGetAllPlanes = useCaseGetAllPlanes;
        this.useCaseGetAllEmployees = useCaseGetAllEmployees;
    }


    public void CrearRevision(){
        
        Revision revision = new Revision();
        revision.setFechaRevision(Validation.LeerFecha("Digite la fecha de revision: ", scanner));
        
        imprimirAviones();
        int idAvion = Validation.leerNumero("Digite el id del avion: ", scanner);
        Boolean existeAvion = validarExistenciaAvion(idAvion);
        if(existeAvion==false){System.out.println("El avion con el id digitado no existe");return;}
        revision.setIdAvion(idAvion);


        imprimirEmpleados();
        int idEmpleado = Validation.leerNumero("Digite el id del Empleado: ", scanner);
        Boolean existeEmpleado = validarExistenciaEmpleado(idEmpleado);
        if(existeEmpleado==false){System.out.println("El Empleado con el id digitado no existe");return;}
        revision.setIdEmpleado(idEmpleado);

        revision.setDescripcion(Validation.leerdato("Digite la descripcion de la revision: ", scanner));

        try {
            useCaseCreateRevision.execute(revision);
            System.out.println("Revision registrada exitosamente");
        } catch (Exception e) {
            System.out.println("Error al registrar la revision");
            e.printStackTrace();
        }
    }

    public void updateRevision(){
        imprimirRevisiones();
        int idRevision = Validation.leerNumero("Digite el id de la revision a actualizar: ", scanner);
        Revision revision = useCaseGetRevisionById.execute(idRevision);
        if(revision==null){return;}

        imprimirMenuRevision();
        int opcion = Validation.leerNumero("Digite una opcion: ", scanner);
        switch (opcion) {
            case 1:
                System.out.println("Valor Actual: " + revision.getFechaRevision());
                revision.setFechaRevision(Validation.LeerFecha("Digite el nuevo valor: ", scanner));
                break;
            case 2:
                imprimirAviones();
                System.out.println("Id Actual: " + revision.getIdAvion());
                int idAvion = Validation.leerNumero("Digite el id del avion: ", scanner);
                Boolean existeAvion = validarExistenciaAvion(idAvion);
                if(existeAvion==false){System.out.println("El avion con el id digitado no existe");return;}
                revision.setIdAvion(idAvion);
                break;
            case 3:
                imprimirEmpleados();
                System.out.println("Id Actual: " + revision.getIdEmpleado());
                int idEmpleado = Validation.leerNumero("Digite el id del Empleado: ", scanner);
                Boolean existeEmpleado = validarExistenciaEmpleado(idEmpleado);
                if(existeEmpleado==false){System.out.println("El Empleado con el id digitado no existe");return;}
                revision.setIdEmpleado(idEmpleado);
                break;
            case 4:
                System.out.println("Valor Actual: " + revision.getDescripcion());
                revision.setDescripcion(Validation.leerdato("Digite el nuevo valor: ", scanner));
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    
        try {
            useCaseUpdateRevision.execute(revision);
            System.out.println("Revision Actualizada con exito");
            imprimirRevision(revision);
        } catch (Exception e) {
            System.out.println("Error al actualizar la revision");
            e.printStackTrace();
        }
    }


    public void deleteRevision(){
        imprimirRevisiones();
        int idRevision = Validation.leerNumero("Digite el id de la revision a eliminar: ", scanner);
        Revision revision = useCaseGetRevisionById.execute(idRevision);
        if(revision==null){System.out.println("El id no existe");return;}
        try {
            useCaseDeleteRevision.execute(idRevision);
            System.out.println("Revision eliminada exitosamente");   
        } catch (Exception e) {
           System.out.println("Error al eliminar la revision");
           e.printStackTrace();
        }

    }

    private boolean validarExistenciaAvion(int idAvion){
        ArrayList<Plane> listaAviones = useCaseGetAllPlanes.execute();
        Boolean existe = listaAviones.stream().anyMatch((e)-> e.getId()==idAvion);
        if(existe){return true;}
        return false;
    }

    private boolean validarExistenciaEmpleado(int idEmpleado){
        ArrayList<Employee> listaEmployees = useCaseGetAllEmployees.execute();
        Boolean existe = listaEmployees.stream().anyMatch((e)-> e.getId()==idEmpleado);
        if(existe){return true;}
        return false;
    }

    private void imprimirAviones(){
        ArrayList<Plane> listaAviones = useCaseGetAllPlanes.execute();
        Consumer <Plane> imprimirAviones = (e)-> System.out.println("id: " + e.getId() + "Matricula: " + e.getMatricula());
        listaAviones.forEach(imprimirAviones);
    }

    private void imprimirEmpleados(){
        ArrayList<Employee> listaEmpleados = useCaseGetAllEmployees.execute();
        Consumer <Employee> imprimirEmpleados = (e)-> System.out.println("id: " + e.getId() + "nombre: " + e.getNombre());
        listaEmpleados.forEach(imprimirEmpleados);
    }



    private void imprimirRevision(Revision revision){
        System.out.println("Informacion Revision");
        System.out.println("id:" + revision.getId() + " Fecha Revision: " +revision.getFechaRevision()+" Descripcion: "+ revision.getDescripcion() + " Id Avion: " + revision.getIdAvion() +" Id Empleado: "+ revision.getIdEmpleado());
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void imprimirRevisiones(){
        ArrayList <Revision> listaRevisions= useCaseGetAllRevision.execute();
        Consumer <Revision> imprimirRevisiones = (e)-> System.out.println("Id: " + e.getId() + " Fecha Revision:" + e.getFechaRevision() + " descripcion: " + e.getDescripcion());
        listaRevisions.forEach(imprimirRevisiones);
    }

    private void imprimirMenuRevision(){
        System.out.println("1. Fecha Revision");
        System.out.println("2. Avion Revision");
        System.out.println("3. Empleado Revision");
        System.out.println("4. Descripcion de la Revision");
    }

}   
