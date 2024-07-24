package airCrew.infrastructure.in;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import airCrew.application.UseCaseAddCrewToTrip;
import airCrew.application.UseCaseGetAirCrewToTrip;
import airCrew.application.UseCaseGetIdAirlineOfTrip;
import airCrew.application.UseCaseValidateAirCrew;
import airCrew.domain.AirCrew;
import airline.application.UseCaseGetAllAirlines;
import airline.domain.Airline;

import employee.application.UseCaseGetAllEmployees;
import employee.application.UseCaseGetEmployeeByAirline;
import employee.application.UseCaseGetEmployeeById;
import employee.domain.Employee;
import utils.Validation;
import viaje.application.ListVuelos;
import viaje.domain.FlightRecord;


public class AirCrewController {
    private final UseCaseAddCrewToTrip useCaseAddCrewToTrip;
    private final UseCaseGetAirCrewToTrip useCaseGetAirCrewToTrip;
    private final UseCaseGetAllEmployees useCaseGetAllEmployees;
    private final UseCaseGetIdAirlineOfTrip useCaseGetIdAirlineOfTrip;
    private final ListVuelos useCaseGetAllVuelos;
    private final UseCaseGetEmployeeByAirline useCaseGetEmployeeByAirline;
    private final UseCaseGetAllAirlines useCaseGetAllAirlines;
    private final UseCaseGetEmployeeById useCaseGetEmployeeById;
    private final UseCaseValidateAirCrew useCaseValidateAirCrew;
    Scanner scanner = new Scanner(System.in);

    public AirCrewController(UseCaseAddCrewToTrip useCaseAddCrewToTrip, UseCaseGetAirCrewToTrip useCaseGetAirCrewToTrip,
            UseCaseGetAllEmployees useCaseGetAllEmployees, UseCaseGetIdAirlineOfTrip useCaseGetIdAirlineOfTrip,
            ListVuelos useCaseGetAllVuelos, UseCaseGetEmployeeByAirline useCaseGetEmployeeByAirline,
            UseCaseGetAllAirlines useCaseGetAllAirlines, UseCaseGetEmployeeById useCaseGetEmployeeById,
            UseCaseValidateAirCrew useCaseValidateAirCrew) {
        this.useCaseAddCrewToTrip = useCaseAddCrewToTrip;
        this.useCaseGetAirCrewToTrip = useCaseGetAirCrewToTrip;
        this.useCaseGetAllEmployees = useCaseGetAllEmployees;
        this.useCaseGetIdAirlineOfTrip = useCaseGetIdAirlineOfTrip;
        this.useCaseGetAllVuelos = useCaseGetAllVuelos;
        this.useCaseGetEmployeeByAirline = useCaseGetEmployeeByAirline;
        this.useCaseGetAllAirlines = useCaseGetAllAirlines;
        this.useCaseGetEmployeeById = useCaseGetEmployeeById;
        this.useCaseValidateAirCrew = useCaseValidateAirCrew;
    }

    public void AsignarTripulacionViaje(){
        int idAerolinea;
        imprimirViajes();
        int idViaje = Validation.leerNumero("Digite el id del viaje", scanner);
        Boolean existeViaje = verificarExistenciaViaje(idViaje);
        if(existeViaje==false){System.out.println("id Viaje no valido"); return;}

        int idAeroViaje= useCaseGetIdAirlineOfTrip.execute(idViaje);
        if(idAeroViaje==0){
            imprimirAerolienas();
            idAerolinea = Validation.leerNumero("Digite el id de la aerolinea", scanner);
            Boolean existe = verificarExistenciaAerolinea(idAerolinea);
            if(existe==false){System.out.println("id Aerolinea no valido"); return;}
        }else{
            ArrayList <Airline> listaAerolineas = useCaseGetAllAirlines.execute(); 
            Optional <Airline> obj = listaAerolineas.stream().filter((e)->e.getIdAirline()==idAeroViaje).findFirst();
            if(!obj.isPresent()){System.out.println("No se puedo obtener la aerolinea"); return;}
            Airline aerolinea = obj.get();
            System.out.println("La tripulacion del viaje pertenecen a la aerolinea " + aerolinea.getNombreAirline());
            idAerolinea = idAeroViaje;
        }

        ArrayList<Employee> listaEmpleados = useCaseGetAirCrewToTrip.execute(idViaje);
        if(listaEmpleados==null){
            imprimirEmpleadosPorAerolinea(idAerolinea);
            int idEmpleado = Validation.leerNumero("Digite el id del empleado", scanner);
            boolean existeEmpleado = verificarEmpleadoEnAerolinea(idEmpleado,idAerolinea);
            if(existeEmpleado==false){System.out.println("Empleado no se encuentra en la aerolinea"); return;}
            AirCrew tripulacion = new AirCrew();
            tripulacion.setIdEmpleado(idEmpleado);
            tripulacion.setIdViaje(idViaje);
            useCaseAddCrewToTrip.execute(tripulacion);
            System.out.println("Empleado Agregado a tripulacion");
        }else{
            System.out.println("Agregar Tripulacion: ");
            imprimirEmpleadosPorAerolinea(idAerolinea);
            int idEmpleado = Validation.leerNumero("Digite el id del empleado", scanner);
            boolean existeEmpleado = verificarEmpledoEnViaje(idEmpleado, idViaje);
            if(existeEmpleado==true){System.out.println("Empleado ya se encuentra asignado"); return;}
            boolean existeEmp = verificarEmpleadoEnAerolinea(idEmpleado,idAerolinea);
            if(existeEmp==false){System.out.println("Empleado no se encuentra en la aerolinea"); return;}
            
            AirCrew tripulacion = new AirCrew();
            tripulacion.setIdEmpleado(idEmpleado);
            tripulacion.setIdViaje(idViaje);
            useCaseAddCrewToTrip.execute(tripulacion);
            System.out.println("Empleado Agregado a tripulacion");
        }
    }

    public void consultarAsignacionTripulacion(){
        imprimirEmpleados();
        int idEmpleado = Validation.leerNumero("Digite el id del empleado", scanner);

        AirCrew tripulacion = useCaseValidateAirCrew.execute(idEmpleado);
        if(tripulacion==null){System.out.println("El empleado no se encuentra en una tripulacion"); return;}

        ArrayList<Employee> listEmpleado = useCaseGetAllEmployees.execute();
        Optional <Employee> objemp = listEmpleado.stream().filter((e)->e.getId()==idEmpleado).findFirst();
        Employee empleado = objemp.get();   
        ArrayList <Airline> listaAerolinea = useCaseGetAllAirlines.execute();
        Optional <Airline> objAerolinea = listaAerolinea.stream().filter((e)->e.getIdAirline()==empleado.getIdAirline()).findFirst();
        Airline aerolinea = objAerolinea.get();
        ArrayList<FlightRecord> listaViajes = useCaseGetAllVuelos.execute();
        Optional <FlightRecord> objViaje = listaViajes.stream().filter((e)-> e.getId()==tripulacion.getIdViaje()).findFirst();
        FlightRecord viaje = objViaje.get();
        System.out.println("Empleado en tripulacion");
        System.out.println("Aerolinea: " + aerolinea.getNombreAirline());
        System.out.println("Nombre Empleado: " + empleado.getNombre());
        System.out.println("Viaje Asignado: ");
        System.out.println("Ciudad de origen: "+ viaje.getOriginCity());
        System.out.println("Ciudad destino" + viaje.getDestinationCity());
    }


  private boolean verificarExistenciaAerolinea(int idAerolinea){
        ArrayList<Airline> listaAerolineas = useCaseGetAllAirlines.execute();
        Boolean existe = listaAerolineas.stream().anyMatch((e)-> e.getIdAirline()==idAerolinea);
        if(existe){return true;}
        return false;
    }


private boolean verificarEmpleadoEnAerolinea(int idEmpleado, int idAerolinea){
        ArrayList <Employee> listaEmpleados = useCaseGetEmployeeByAirline.execute(idAerolinea);
        Boolean existe = listaEmpleados.stream().anyMatch((e)-> e.getId()==idEmpleado);
        if(existe){return true;}
        return false;
}

    private boolean verificarEmpledoEnViaje(int idEmpleado, int idViaje){
        ArrayList <Employee> listaEmpleados = useCaseGetAirCrewToTrip.execute(idViaje);
        Boolean existe = listaEmpleados.stream().anyMatch((e)-> e.getId()==idEmpleado);
        if(existe){return true;}
        return false;
    }

    
  private boolean verificarExistenciaViaje(int idViaje){
    ArrayList<FlightRecord> listaViajes = useCaseGetAllVuelos.execute();
    Boolean existe = listaViajes.stream().anyMatch((e)-> e.getId()==idViaje);
    if(existe){return true;}
    return false;
}


    private void imprimirAerolienas(){
        ArrayList <Airline> listaAerolineas = useCaseGetAllAirlines.execute();
        Consumer <Airline> imprimirAerolineas = (e)-> System.out.println("Id: " + e.getIdAirline() + " Nombre:" + e.getNombreAirline());
        listaAerolineas.forEach(imprimirAerolineas);
    }

    private void imprimirEmpleadosPorAerolinea(int idAerolinea){
        ArrayList <Employee> listaEmpleados = useCaseGetEmployeeByAirline.execute(idAerolinea);
        Consumer <Employee> imprimirEmpleados = (e)-> System.out.println("Id: " + e.getId() + " Nombre:" + e.getNombre());
        listaEmpleados.forEach(imprimirEmpleados);
    }

    private void imprimirEmpleados(){
        ArrayList <Employee> listaEmpleados = useCaseGetAllEmployees.execute();
        Consumer <Employee> imprimirEmpleados = (e)-> System.out.println("Id: " + e.getId() + " Nombre:" + e.getNombre());
        listaEmpleados.forEach(imprimirEmpleados);
    }

    private void imprimirViajes(){
        ArrayList <FlightRecord> listaViajes = useCaseGetAllVuelos.execute();
        Consumer <FlightRecord> imprimirViaje = (e)-> System.out.println("Id: " + e.getId() + " Ciudad Origen:" + e.getOriginCity() + " Ciudad Destino" + e.getDestinationCity());
        listaViajes.forEach(imprimirViaje);
    }





    
}
