package employee.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import employee.domain.Employee;
import employee.domain.ServiceEmployee;

public class EmployeeRepository implements ServiceEmployee {

    @Override
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> listaEmpleados = new ArrayList<>();
        String sql = "select e.id , e.nombre , e.fechaIngreso , a.id as 'idAerolinea' , a.nombre AS 'nombreAerolinea'" +
         "from Empleado as e inner join Aerolinea as a on a.id=e.Aerolinea_id";
        try (Connection connection = DataBaseConfig.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            try(ResultSet rs = statement.executeQuery();){
                while (rs.next()) {
                    Employee empleado = new Employee();
                    empleado.setId(rs.getInt("id"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setFechaIngreso(rs.getString("fechaIngreso"));
                    empleado.setIdAirline(rs.getInt("idAerolinea"));
                    empleado.setNombreAirline(rs.getString("nombreAerolinea"));
                    listaEmpleados.add(empleado);
                }     
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        

        return listaEmpleados;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee empleado  = new Employee();
        String sql = "select e.id , e.nombre , e.fechaIngreso , a.id as 'idAerolinea' , a.nombre AS 'nombreAerolinea'" +
         "from Empleado as e inner join Aerolinea as a on a.id=e.Aerolinea_id WHERE id=?";
        try (Connection connection = DataBaseConfig.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            try(ResultSet rs = statement.executeQuery();){
                    if(rs.next()){
                        empleado.setId(rs.getInt("id"));
                        empleado.setNombre(rs.getString("nombre"));
                        empleado.setFechaIngreso(rs.getString("fechaIngreso"));
                        empleado.setIdAirline(rs.getInt("idAerolinea"));
                        empleado.setNombreAirline(rs.getString("nombreAerolinea"));
                    }else{
                        System.out.println("Empleado no encontrado");
                        return null;
                    }
                    
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }        

        return empleado;
    }

    @Override
    public ArrayList<Employee> getEmployeesByAirline(int idAirline) {
        ArrayList <Employee> listaEmpleados = new ArrayList<>();
        String sql = "select e.id , e.nombre , e.fechaIngreso , a.id as 'idAerolinea' , a.nombre AS 'nombreAerolinea'" +
         "from Empleado as e inner join Aerolinea as a on a.id=e.Aerolinea_id WHERE a.id=?";
        try (Connection connection = DataBaseConfig.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, idAirline);
            try(ResultSet rs = statement.executeQuery();){
                    while(rs.next()){
                        Employee empleado  = new Employee();
                        empleado.setId(rs.getInt("id"));
                        empleado.setNombre(rs.getString("nombre"));
                        empleado.setFechaIngreso(rs.getString("fechaIngreso"));
                        empleado.setIdAirline(rs.getInt("idAerolinea"));
                        empleado.setNombreAirline(rs.getString("nombreAerolinea"));
                        listaEmpleados.add(empleado);
                    }
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }        

        return listaEmpleados;
    }

   
   


}
