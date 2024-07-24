package airCrew.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import airCrew.domain.AirCrew;
import airCrew.domain.ServiceAirCrew;
import config.DataBaseConfig;
import employee.domain.Employee;

public class AirCrewRepository implements ServiceAirCrew {

    @Override
    public void AddAirCrewToTrip(AirCrew airCrew) {
          String slq = "Insert into Tripulacion (Empleado_id,id_viaje) VALUES(?,?)";
        try(Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement =  connection.prepareStatement(slq,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,airCrew.getIdEmpleado());
            statement.setInt(2,airCrew.getIdViaje());
            statement.executeUpdate();
            try(ResultSet generateKeys = statement.getGeneratedKeys()){;
                if(generateKeys.next()){
                    airCrew.setId(generateKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Employee> GetAirCrewToTrip(int idViaje) {
        ArrayList<Employee> listaTripulantes = new ArrayList<>();
        String sql = "SELECT e.id, e.nombre, e.fechaIngreso, aero.nombre AS 'Aerolinea' " +
                     "FROM Empleado AS e " +
                     "INNER JOIN Tripulacion AS a ON a.Empleado_id = e.id " +
                     "INNER JOIN Aerolinea AS aero ON aero.id = e.Aerolinea_id " +
                     "WHERE a.id_viaje = ?";
    
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idViaje);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Employee empleado = new Employee();
                    empleado.setId(rs.getInt("id"));
                    empleado.setNombre(rs.getString("nombre")); // Agregué esta línea
                    empleado.setFechaIngreso(rs.getString("fechaIngreso"));
                    empleado.setNombreAirline(rs.getString("Aerolinea"));
                    listaTripulantes.add(empleado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return listaTripulantes;
    }

    @Override
    public ArrayList<AirCrew> GetAllCrewInfo(int idViaje) {
        ArrayList<AirCrew> listaTripulantes = new ArrayList<>();
        String sql = "SELECt id , Empleado_id, id_viaje " +
                     "FROM Tripulacion " +
                     "WHERE id_viaje = ?";
    
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idViaje);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    AirCrew tripulacion = new AirCrew();
                    tripulacion.setId(rs.getInt("id"));
                    tripulacion.setIdEmpleado(rs.getInt("Empleado_id")); // Agregué esta línea
                    tripulacion.setIdViaje(rs.getInt("id_viaje"));
                    listaTripulantes.add(tripulacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return listaTripulantes;
    }

    @Override
    public int GetIdAirlineOfTrip(int idViaje) {
        int idAerolinea = 0;
        String sql = "SELECT DISTINCT e.Aerolinea_id AS Aerolinea " +
                     "FROM Empleado AS e " +
                     "INNER JOIN Tripulacion AS a ON a.Empleado_id = e.id " +
                     "WHERE a.id_viaje = ?";
    
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idViaje);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {  // Verificamos si hay un resultado
                    idAerolinea = rs.getInt("Aerolinea");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return idAerolinea;
    }

    @Override
    public AirCrew ValidateAirCrew(int idEmpleado) {
        AirCrew airCrew = new AirCrew();
        String sql = "select a.id, a.Empleado_id, a.id_viaje "+ 
                    "from Empleado as e inner join Tripulacion as a on a.Empleado_id=e.id " +
                    "where e.id=?";
                    try (Connection connection = DataBaseConfig.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)) {
                   statement.setInt(1, idEmpleado);
                   try (ResultSet rs = statement.executeQuery()) {
                       if (rs.next()) {  // Verificamos si hay un resultado
                           airCrew.setId(rs.getInt("id"));
                           airCrew.setIdEmpleado(rs.getInt("Empleado_id"));
                           airCrew.setIdViaje(rs.getInt("id_viaje"));
                       }else{
                        System.out.println("No se encontro empleado en tripulacion");
                        return null;
                       }
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               return airCrew;

    }
    

}


