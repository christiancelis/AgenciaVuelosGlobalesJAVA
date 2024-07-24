package employee.application;

import employee.domain.Employee;
import employee.domain.ServiceEmployee;

public class UseCaseGetEmployeeById {
    ServiceEmployee serviceEmployee;

    public UseCaseGetEmployeeById(ServiceEmployee serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public Employee execute(int id){
        return serviceEmployee.getEmployeeById(id);   
    }
    
}
