package plane.infrastructure.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import plane.domain.Plane;
import plane.domain.ServicePlane;


public class PlaneRepository implements ServicePlane {

    @Override
    public void createPlane(Plane avion) {
        String sql = "INSERT INTO Avion(matricula,capacidad,fechaFabricacion,Modelo_id,Estado_id) VALUES (?,?,?,?,?)";
        try (
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1, avion.getMatricula());
            statement.setInt(2, avion.getCapacidad());
            statement.setDate(3,avion.getFechaFabricacion());
            statement.setInt(4,avion.getModeloId());
            statement.setInt(5,avion.getEstadoId());
            statement.executeUpdate();
            try(ResultSet generateKeys = statement.getGeneratedKeys()){;
                if(generateKeys.next()){
                    avion.setId(generateKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Plane> getAllPlane() {
        ArrayList<Plane> listaAviones = new ArrayList<>();
        String sql = "SELECT id, matricula, capacidad, fechaFabricacion, Modelo_id, Estado_id FROM Avion";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Plane avion = new Plane();
                    avion.setId(rs.getInt("id"));
                    avion.setMatricula(rs.getString("matricula"));
                    avion.setCapacidad(rs.getInt("capacidad"));
                    avion.setFechaFabricacion(rs.getDate("fechaFabricacion"));
                    avion.setModeloId(rs.getInt("Modelo_id"));
                    avion.setEstadoId(rs.getInt("Estado_id"));
                    listaAviones.add(avion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAviones;
    }

    @Override
    public void DeletePlaneByPlate(String plate) {
        String deleteSQL = "DELETE FROM Avion WHERE matricula=?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setString(1, plate);
            int rowDelete = statement.executeUpdate(); // Ejecuta la actualización sin pasar el SQL nuevamente
            if(rowDelete>0){
                System.out.println("Avion eliminado Satisfactoriamente");
            }else{
                System.out.println("Error Avion no se elimino correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlaneByPlate(Plane plane) {
        String updateSQL = "UPDATE Avion SET matricula = ?, capacidad = ?, fechaFabricacion = ?, Modelo_id = ?, Estado_id = ? WHERE matricula = ?";
    try (Connection connection = DataBaseConfig.getConnection();
         PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            
        statement.setString(1, plane.getMatricula());
        statement.setInt(2, plane.getCapacidad());
        statement.setDate(3, plane.getFechaFabricacion());
        statement.setInt(4, plane.getModeloId());
        statement.setInt(5, plane.getEstadoId());
        statement.setString(6, plane.getMatricula());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("El avión fue actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el avión con la matricula proporcionado.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
public Plane getPlaneByPlate(String plate) {
    Plane avion = null;
    String sql = "SELECT id, matricula, capacidad, fechaFabricacion, Modelo_id, Estado_id FROM Avion WHERE matricula = ?";
    
    try (Connection connection = DataBaseConfig.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, plate);
        
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) { // Mover el cursor al primer resultado (si existe)
                avion = new Plane();
                avion.setId(rs.getInt("id"));
                avion.setMatricula(rs.getString("matricula"));
                avion.setCapacidad(rs.getInt("capacidad"));
                avion.setFechaFabricacion(rs.getDate("fechaFabricacion"));
                avion.setModeloId(rs.getInt("Modelo_id"));
                avion.setEstadoId(rs.getInt("Estado_id"));
            } else {
                System.out.println("No se encontró ningún avión con la matrícula: " + plate);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return avion;
}
}
