package employee.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import employee.application.UseCaseGetAllEmployees;
import employee.application.UseCaseGetEmployeeById;
import employee.domain.Employee;
import utils.Validation;

public class EmployeeeController {
    private final UseCaseGetAllEmployees useCaseGetAllEmployees;
    private final UseCaseGetEmployeeById useCaseGetEmployeeById;
    Scanner scanner = new Scanner(System.in);

    public EmployeeeController(UseCaseGetAllEmployees useCaseGetAllEmployees,
            UseCaseGetEmployeeById useCaseGetEmployeeById) {
        this.useCaseGetAllEmployees = useCaseGetAllEmployees;
        this.useCaseGetEmployeeById = useCaseGetEmployeeById;
    }

    public void ObtenerEmpleadoPorId(){
        imprimirEmpleados();
        int idEmpleado = Validation.leerNumero("Digite el id del empleado que desea consultar: ", scanner);
        Employee empleado = useCaseGetEmployeeById.execute(idEmpleado);
        if(empleado!=null){imprimirUnEmpleado(empleado);}
    }

    private void imprimirUnEmpleado(Employee empleado){
        System.out.println("Informacion ");
        System.out.println("id:" + empleado.getId() + " Nombre: " + empleado.getNombre());
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void imprimirEmpleados(){
        ArrayList <Employee> listaEmpleados = useCaseGetAllEmployees.execute();
        Consumer <Employee> imprimirEmpleados = (e)-> System.out.println("Id: " + e.getId() + " Tipo:" + e.getNombre());
        listaEmpleados.forEach(imprimirEmpleados);
    }





    
}   
