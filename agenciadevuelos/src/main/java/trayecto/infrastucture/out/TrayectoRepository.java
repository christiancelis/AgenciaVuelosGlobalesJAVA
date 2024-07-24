package trayecto.infrastucture.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import config.DataBaseConfig;

import trayecto.domain.ServiceTrayecto;
import trayecto.domain.Trayecto;


public class TrayectoRepository implements ServiceTrayecto {

  

    @Override
    public ArrayList<Trayecto> GetAllTrayecto() {
        ArrayList<Trayecto> trayectos = new ArrayList<>();
        String sql = "SELECT id,originCity, destinationCity, idViaje FROM Escala"; 

        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Trayecto trayecto = new Trayecto(
                );
                trayectos.add(trayecto);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return trayectos;
    }

    
    @Override
    public int GuardarTrayecto(Trayecto trayecto) {
        String sql = "INSERT INTO Trayecto (originCity, destinationCity) VALUES (?, ?)";
        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, trayecto.getOriginCity());
            statement.setString(2, trayecto.getDestinoCity());
            //statement.setInt(3, trayecto.getIdViaje());

            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                try(ResultSet generateKeys = statement.getGeneratedKeys()){;
                    if(generateKeys.next()){
                        trayecto.setId(generateKeys.getInt(1));
                    }
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trayecto.getId();
        
    }

    @Override
    public void actualizarTrayecto(Trayecto trayecto) throws SQLException {
      
        
    }

    @Override
    public void insertarVueloEscala(int idViaje, int idTrayecto) {
        String sql = "{CALL InsertVueloTrayecto(?, ?)}";
    
        try (Connection connection = DataBaseConfig.getConnection();
             CallableStatement callableStatement = (CallableStatement) connection.prepareCall(sql)) {
    
            // Establecer los parámetros del procedimiento almacenado
            callableStatement.setInt(1, idViaje);
            callableStatement.setInt(2, idTrayecto);
    
            // Ejecutar el procedimiento almacenado
            callableStatement.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    


    
}   