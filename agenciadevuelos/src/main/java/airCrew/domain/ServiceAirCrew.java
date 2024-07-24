package airCrew.domain;

import java.util.ArrayList;

import employee.domain.Employee;

public interface ServiceAirCrew {
    void AddAirCrewToTrip(AirCrew airCrew);
    ArrayList<Employee> GetAirCrewToTrip(int idViaje);
    ArrayList<AirCrew> GetAllCrewInfo(int Viaje);
    int GetIdAirlineOfTrip(int idViaje);
    AirCrew ValidateAirCrew(int idEmpleado);
}
