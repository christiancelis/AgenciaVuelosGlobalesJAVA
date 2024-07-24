package employee.application;

import java.util.ArrayList;

import employee.domain.Employee;
import employee.domain.ServiceEmployee;

public class UseCaseGetEmployeeByAirline {
    ServiceEmployee serviceEmployee;

    public UseCaseGetEmployeeByAirline(ServiceEmployee serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public ArrayList <Employee> execute(int idAirline){
        return serviceEmployee.getEmployeesByAirline(idAirline);
    } 



    

}
