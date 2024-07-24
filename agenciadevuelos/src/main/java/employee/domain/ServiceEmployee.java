package employee.domain;

import java.util.ArrayList;

public interface ServiceEmployee {
    ArrayList<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    ArrayList<Employee> getEmployeesByAirline(int idAirline);    
}
