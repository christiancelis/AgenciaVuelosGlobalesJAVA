package planeStatus.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import planeStatus.domain.ServiceStatus;
import planeStatus.domain.Status;

public class StatusRepository implements ServiceStatus{

    @Override
    public ArrayList<Status> getAllStatus() {
       ArrayList<Status> listaEstados = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Estado";
        try(Connection connection = DataBaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);) {
            
            try(ResultSet rs = statement.executeQuery(sql);){
                while (rs.next()) {
                    Status estado = new Status();
                    estado.setId(rs.getInt("id"));
                    estado.setName(rs.getString("nombre"));
                    listaEstados.add(estado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEstados;
    }

    @Override
    public Status getStatusById(Integer id) {
        String sql = "SELECT id, nombre FROM Estado WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Status status = new Status();
                    status.setId(rs.getInt("id"));
                    status.setName(rs.getString("nombre"));
                    return status;
                }
            }
        } catch (SQLException e) {
            // Manejar excepciones, por ejemplo, loggear o lanzar una excepción de aplicación
            e.printStackTrace();
        }
        return null; // Retornar null si no se encuentra el status con el ID dado
    }


}
