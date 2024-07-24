package airCrew.infrastructure.in;

import java.util.Scanner;

import com.mysql.cj.xdevapi.Schema.Validation;

import airCrew.application.UseCaseAddCrewToTrip;
import airCrew.application.UseCaseGetAirCrewToTrip;
import airCrew.application.UseCaseGetIdAirlineOfTrip;
import airCrew.application.UseCaseValidateAirCrew;
import airCrew.domain.ServiceAirCrew;
import airCrew.infrastructure.out.AirCrewRepository;
import airline.application.UseCaseGetAllAirlines;
import airline.domain.ServiceAirline;
import airline.infrastructure.out.AirlineRepository;
import employee.application.UseCaseGetAllEmployees;
import employee.application.UseCaseGetEmployeeByAirline;
import employee.application.UseCaseGetEmployeeById;
import employee.domain.ServiceEmployee;
import employee.infrastructure.out.EmployeeRepository;
import viaje.application.ListVuelos;
import viaje.domain.ServiceFlight;
import viaje.infrastructure.out.FlightRepositoryImpl;

public class AirCrewMenu {
        public void start(){
            ServiceAirCrew serviceAirCrew = new AirCrewRepository();
            ServiceFlight serviceFlight = new FlightRepositoryImpl();
            ServiceEmployee serviceEmployee = new EmployeeRepository();
            ServiceAirline serviceAirline = new AirlineRepository();

            Scanner scanner = new Scanner(System.in);

            UseCaseAddCrewToTrip addCrewToTrip = new UseCaseAddCrewToTrip(serviceAirCrew);
            UseCaseGetIdAirlineOfTrip getIdAirlineOfTrip = new UseCaseGetIdAirlineOfTrip(serviceAirCrew);
            UseCaseGetAirCrewToTrip getAirCrewToTrip = new UseCaseGetAirCrewToTrip(serviceAirCrew);
            UseCaseValidateAirCrew validateAirCrew = new UseCaseValidateAirCrew(serviceAirCrew);
            UseCaseGetAllEmployees getAllEmployees = new UseCaseGetAllEmployees(serviceEmployee);
            UseCaseGetEmployeeByAirline getEmployeeByAirline = new UseCaseGetEmployeeByAirline(serviceEmployee);
            UseCaseGetEmployeeById getEmployeeById = new UseCaseGetEmployeeById(serviceEmployee);
            UseCaseGetAllAirlines getAllAirlines = new UseCaseGetAllAirlines(serviceAirline);
            ListVuelos getAllVuelos = new ListVuelos(serviceFlight);

            AirCrewController airCrewController = new AirCrewController(addCrewToTrip, getAirCrewToTrip, getAllEmployees, getIdAirlineOfTrip, getAllVuelos, getEmployeeByAirline, getAllAirlines, getEmployeeById, validateAirCrew);

          

            while (true) {
                imprimirMenu();
                int Opcion = utils.Validation.leerNumero("Digite una opcion", scanner);
                switch (Opcion) {
                    case 1:
                        airCrewController.AsignarTripulacionViaje();
                        break;
                    case 2:
                        airCrewController.consultarAsignacionTripulacion();
                        break;
                    case 3:
                        return;
                
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }
        }

    private void imprimirMenu(){
        System.out.println( "Menu Tripulacion");
        System.out.println("1, Asignar tripulacion a viaje");
        System.out.println("2. Consultar Asignacion de tripulacion");
        System.out.println("3. Salir");
    }
}
