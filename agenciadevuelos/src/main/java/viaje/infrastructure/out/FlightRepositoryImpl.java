package viaje.infrastructure.out;

import viaje.domain.ServiceFlightRepository;
import viaje.domain.FlightRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DataBaseConfig;

public class FlightRepositoryImpl implements ServiceFlightRepository {

   
    @Override
    public void GuardarViaje(FlightRecord flightRecord) throws SQLException {
        String sql = "INSERT INTO Viaje (originAirport, originCity, destinationAirport, destinationCity, precio, fechaViaje,hora) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, flightRecord.getOriginAirport().getName());
            statement.setString(2, flightRecord.getOriginCity());
            statement.setString(3, flightRecord.getDestinationAirport().getName());
            statement.setString(4, flightRecord.getDestinationCity());
            statement.setDouble(5, flightRecord.getPrice());
            statement.setDate(6, flightRecord.getTravelDate());
            statement.setTime(7, flightRecord.getHora());
            statement.executeUpdate();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }


    

