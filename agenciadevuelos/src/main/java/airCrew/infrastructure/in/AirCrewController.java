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
        int idViaje = Validation.leerNumero("Digite el ID del viaje", scanner);
        Boolean existeViaje = verificarExistenciaViaje(idViaje);
        if(!existeViaje){
            System.out.println("ID del viaje no válido");
            return;
        }

        int idAeroViaje = useCaseGetIdAirlineOfTrip.execute(idViaje);
        if(idAeroViaje == 0){
            imprimirAerolienas();
            idAerolinea = Validation.leerNumero("Digite el ID de la aerolínea", scanner);
            Boolean existe = verificarExistenciaAerolinea(idAerolinea);
            if(!existe){
                System.out.println("ID de aerolínea no válido");
                return;
            }
        } else {
            ArrayList<Airline> listaAerolineas = useCaseGetAllAirlines.execute();
            Optional<Airline> obj = listaAerolineas.stream().filter(e -> e.getIdAirline() == idAeroViaje).findFirst();
            if(!obj.isPresent()){
                System.out.println("No se pudo obtener la aerolínea");
                return;
            }
            Airline aerolinea = obj.get();
            System.out.println("La tripulación del viaje pertenece a la aerolínea " + aerolinea.getNombreAirline());
            idAerolinea = idAeroViaje;
        }

        ArrayList<Employee> listaEmpleados = useCaseGetAirCrewToTrip.execute(idViaje);
        if(listaEmpleados == null){
            imprimirEmpleadosPorAerolinea(idAerolinea);
            int idEmpleado = Validation.leerNumero("Digite el ID del empleado", scanner);
            boolean existeEmpleado = verificarEmpleadoEnAerolinea(idEmpleado, idAerolinea);
            if(!existeEmpleado){
                System.out.println("El empleado no se encuentra en la aerolínea");
                return;
            }
            AirCrew tripulacion = new AirCrew();
            tripulacion.setIdEmpleado(idEmpleado);
            tripulacion.setIdViaje(idViaje);
            useCaseAddCrewToTrip.execute(tripulacion);
            System.out.println("Empleado agregado a la tripulación");
        } else {
            System.out.println("Agregar tripulación: ");
            imprimirEmpleadosPorAerolinea(idAerolinea);
            int idEmpleado = Validation.leerNumero("Digite el ID del empleado", scanner);
            boolean existeEmpleado = verificarEmpledoEnViaje(idEmpleado, idViaje);
            if(existeEmpleado){
                System.out.println("El empleado ya se encuentra asignado");
                return;
            }
            boolean existeEmp = verificarEmpleadoEnAerolinea(idEmpleado, idAerolinea);
            if(!existeEmp){
                System.out.println("El empleado no se encuentra en la aerolínea");
                return;
            }
            
            AirCrew tripulacion = new AirCrew();
            tripulacion.setIdEmpleado(idEmpleado);
            tripulacion.setIdViaje(idViaje);
            useCaseAddCrewToTrip.execute(tripulacion);
            System.out.println("Empleado agregado a la tripulación");
        }
    }

    public void consultarAsignacionTripulacion(){
        imprimirEmpleados();
        int idEmpleado = Validation.leerNumero("Digite el ID del empleado", scanner);

        AirCrew tripulacion = useCaseValidateAirCrew.execute(idEmpleado);
        if(tripulacion == null){
            System.out.println("El empleado no se encuentra en una tripulación");
            return;
        }

        ArrayList<Employee> listEmpleado = useCaseGetAllEmployees.execute();
        Optional<Employee> objemp = listEmpleado.stream().filter(e -> e.getId() == idEmpleado).findFirst();
        Employee empleado = objemp.get();   
        ArrayList<Airline> listaAerolinea = useCaseGetAllAirlines.execute();
        Optional<Airline> objAerolinea = listaAerolinea.stream().filter(e -> e.getIdAirline() == empleado.getIdAirline()).findFirst();
        Airline aerolinea = objAerolinea.get();
        ArrayList<FlightRecord> listaViajes = useCaseGetAllVuelos.execute();
        Optional<FlightRecord> objViaje = listaViajes.stream().filter(e -> e.getId() == tripulacion.getIdViaje()).findFirst();
        FlightRecord viaje = objViaje.get();
        System.out.println("Empleado en tripulación:");
        System.out.println("========================");
        System.out.println("Aerolínea: " + aerolinea.getNombreAirline());
        System.out.println("Nombre del empleado: " + empleado.getNombre());
        System.out.println("Viaje asignado: ");
        System.out.println(" - Ciudad de origen: " + viaje.getOriginCity());
        System.out.println(" - Ciudad destino: " + viaje.getDestinationCity());
    }

    private boolean verificarExistenciaAerolinea(int idAerolinea){
        ArrayList<Airline> listaAerolineas = useCaseGetAllAirlines.execute();
        Boolean existe = listaAerolineas.stream().anyMatch(e -> e.getIdAirline() == idAerolinea);
        return existe;
    }

    private boolean verificarEmpleadoEnAerolinea(int idEmpleado, int idAerolinea){
        ArrayList<Employee> listaEmpleados = useCaseGetEmployeeByAirline.execute(idAerolinea);
        Boolean existe = listaEmpleados.stream().anyMatch(e -> e.getId() == idEmpleado);
        return existe;
    }

    private boolean verificarEmpledoEnViaje(int idEmpleado, int idViaje){
        ArrayList<Employee> listaEmpleados = useCaseGetAirCrewToTrip.execute(idViaje);
        Boolean existe = listaEmpleados.stream().anyMatch(e -> e.getId() == idEmpleado);
        return existe;
    }

    private boolean verificarExistenciaViaje(int idViaje){
        ArrayList<FlightRecord> listaViajes = useCaseGetAllVuelos.execute();
        Boolean existe = listaViajes.stream().anyMatch(e -> e.getId() == idViaje);
        return existe;
    }

    private void imprimirAerolienas(){
        ArrayList<Airline> listaAerolineas = useCaseGetAllAirlines.execute();
        System.out.println("=== Lista de Aerolíneas ===");
        listaAerolineas.forEach(e -> System.out.println("ID: " + e.getIdAirline() + " | Nombre: " + e.getNombreAirline()));
        System.out.println("===========================");
    }

    private void imprimirEmpleadosPorAerolinea(int idAerolinea){
        ArrayList<Employee> listaEmpleados = useCaseGetEmployeeByAirline.execute(idAerolinea);
        System.out.println("=== Lista de Empleados de la Aerolínea ===");
        listaEmpleados.forEach(e -> System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre()));
        System.out.println("===========================================");
    }

    private void imprimirViajes() {
        ArrayList<FlightRecord> listaViajes = useCaseGetAllVuelos.execute();
        
        System.out.println("=================================");
        System.out.println("       Listado de Vuelos         ");
        System.out.println("=================================");
        System.out.println("ID    | Ciudad Origen             | Aeropuerto Origen         | Ciudad Destino            | Aeropuerto Destino");
        System.out.println("----------------------------------------------------------");

        listaViajes.forEach(v -> {
            System.out.printf("%-5d | %-25s | %-25s | %-25s | %-25s%n", 
                v.getId(), 
                v.getOriginCity(), 
                v.getOriginAirport(), 
                v.getDestinationCity(), 
                v.getDestinationAirport()
            );
        });
        
        System.out.println("=================================");
    }

    private void imprimirEmpleados(){
        ArrayList<Employee> listaEmpleados = useCaseGetAllEmployees.execute();
        System.out.println("=== Lista de Empleados ===");
        listaEmpleados.forEach(e -> System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre()));
        System.out.println("===========================");
    }
}
