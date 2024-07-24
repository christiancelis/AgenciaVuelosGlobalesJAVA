package employee.application;

import java.util.ArrayList;

import employee.domain.Employee;
import employee.domain.ServiceEmployee;

public class UseCaseGetAllEmployees {
    ServiceEmployee serviceEmployee;

    public UseCaseGetAllEmployees(ServiceEmployee serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public ArrayList <Employee> execute(){
        return serviceEmployee.getAllEmployees();
    }
    

}
