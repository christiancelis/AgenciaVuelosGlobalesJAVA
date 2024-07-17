package employee.application;

import employee.domain.Employee;
import employee.domain.ServiceEmployee;


public class CreateEmployee {

   private ServiceEmployee serviceEmployee;

public CreateEmployee(ServiceEmployee serviceEmployee) {
    this.serviceEmployee = serviceEmployee;
}
   
 public void execute(Employee employee){
        serviceEmployee.CreateEmployee(employee);
    }

}
