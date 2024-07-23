package trayecto.infrastucture.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public void guardarTrayecto(Trayecto trayecto) {
        String sql = "INSERT INTO Trayecto (originCity, destinationCity, idViaje) VALUES (?, ?, ?)";
        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, trayecto.getOriginCity());
            statement.setString(2, trayecto.getDestinoCity());
            statement.setInt(3, trayecto.getIdViaje());

            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long generatedId = generatedKeys.getLong(1);
                        System.out.println("Inserted record's ID: " + generatedId);
                        // Puedes usar el ID generado si es necesario
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarTrayecto(Trayecto trayecto) throws SQLException {
        // TODO Auto-generated method stub
        
    }


    
}   