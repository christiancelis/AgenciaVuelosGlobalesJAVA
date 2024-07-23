package Escala.infrastucture.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Escala.domain.Escala;
import Escala.domain.ServiceEscala;
import config.DataBaseConfig;


public class EscalaRepository implements ServiceEscala {

  

    @Override
    public ArrayList<Escala> GetAllEscalas() {
        ArrayList<Escala> escalas = new ArrayList<>();
        String sql = "SELECT * FROM Escala"; // Corrected table name

        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Escala escala = new Escala(
                        resultSet.getInt("id"),
                        resultSet.getInt("idViaje"),
                        resultSet.getString("originAirport"),
                        resultSet.getString("originCity"),
                        resultSet.getString("destinationAirport"),
                        resultSet.getString("destinationCity"),
                        resultSet.getDouble("precio"),
                        resultSet.getDate("fechaViaje"),
                        resultSet.getTime("hora")
                );
                escalas.add(escala);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return escalas;
    }

    @Override
    public void guardarEscala(Escala escala) {
        String sql = "INSERT INTO escala (origin_airport, origin_city, destination_airport, destination_city, price, travel_date, travel_time, id_avion, viaje_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, escala.getOriginAirport());
            statement.setString(2, escala.getOriginCity());
            statement.setString(3, escala.getDestinationAirport());
            statement.setString(4, escala.getDestinationCity());
            statement.setDouble(5, escala.getPrice());
            statement.setDate(6, escala.getTravelDate());
            statement.setInt(7, escala.getIdAvion());
            statement.setInt(8, escala.getViajeId());

            statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
