package viaje.infrastructure.out;

import viaje.domain.ServiceFlight;
import viaje.domain.FlightRecord;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import config.DataBaseConfig;

public class FlightRepositoryImpl implements ServiceFlight {

    @Override
    public void GuardarViaje(FlightRecord flightRecord) throws SQLException {
        String sql = "INSERT INTO Viaje (originAirport, originCity, destinationAirport, destinationCity, precio, fechaViaje, hora, idAvion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) { // Request generated keys
    
            statement.setString(1, flightRecord.getOriginAirport());
            statement.setString(2, flightRecord.getOriginCity());
            statement.setString(3, flightRecord.getDestinationAirport());
            statement.setString(4, flightRecord.getDestinationCity());
            statement.setDouble(5, flightRecord.getPrice());
            statement.setDate(6, flightRecord.getTravelDate()); 
            statement.setTime(7, flightRecord.getHora());
            statement.setInt(8, flightRecord.getIdAvion());
    
            statement.executeUpdate();
    
           
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    flightRecord.setId(generatedKeys.getInt(1)); // Set the generated ID in the FlightRecord object
                } else {
                    throw new SQLException("Failed to obtain ID for the new flight record.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<FlightRecord> GetAllVuelos() {
        ArrayList<FlightRecord> listaVuelos = new ArrayList<>();
        String sql = "SELECT id, originAirport, originCity, destinationAirport, destinationCity, precio, fechaViaje, hora, idAvion FROM Viaje";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            try (ResultSet resultSet = statement.executeQuery()) {
    
                while (resultSet.next()) {
                    FlightRecord flightRecord = new FlightRecord();
                    flightRecord.setId(resultSet.getInt("id"));
                    flightRecord.setOriginAirport(resultSet.getString("originAirport"));
                    flightRecord.setOriginCity(resultSet.getString("originCity"));
                    flightRecord.setDestinationAirport(resultSet.getString("destinationAirport"));
                    flightRecord.setDestinationCity(resultSet.getString("destinationCity"));
                    flightRecord.setPrice(resultSet.getDouble("precio"));
                    flightRecord.setTravelDate(resultSet.getDate("fechaViaje"));
                    flightRecord.setHora(resultSet.getTime("hora"));
                    flightRecord.setIdAvion(resultSet.getInt("idAvion"));
                    
                    listaVuelos.add(flightRecord);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVuelos;
    }

    @Override
    public void EliminarVueloById(int id) {
        ArrayList<FlightRecord> listaVuelos = GetAllVuelos();
    
        if (listaVuelos.isEmpty()) {
            System.out.println("No hay vuelos disponibles para mostrar.");
        } else {
            System.out.println("CARGANDO .....");
        }
    
        String sql = "DELETE FROM Viaje WHERE id = ?";
    
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Vuelo con ID " + id + " eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún vuelo con ID " + id + ".");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al intentar eliminar el vuelo con ID " + id);
        }
    }

    public FlightRecord getFlightById(int id) {
        String sql = "SELECT id, originAirport, originCity, destinationAirport, destinationCity, precio, fechaViaje, hora, idAvion FROM Viaje WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    FlightRecord flightRecord = new FlightRecord();
                    flightRecord.setId(resultSet.getInt("id"));
                    flightRecord.setOriginAirport(resultSet.getString("originAirport"));
                    flightRecord.setOriginCity(resultSet.getString("originCity"));
                    flightRecord.setDestinationAirport(resultSet.getString("destinationAirport"));
                    flightRecord.setDestinationCity(resultSet.getString("destinationCity"));
                    flightRecord.setPrice(resultSet.getDouble("precio"));
                    flightRecord.setTravelDate(resultSet.getDate("fechaViaje"));
                    flightRecord.setHora(resultSet.getTime("hora"));
                    flightRecord.setIdAvion(resultSet.getInt("idAvion"));
                    return flightRecord;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el vuelo con ID " + id);
        }
        return null;
    }

    @Override
    public FlightRecord UpdateVueloById(int id, String originAirport, String originCity, String destinationAirport,
            String destinationCity, Double precio, Date fechaViaje, Time hora, int idAvion) {

        String sql = "UPDATE Viaje SET originAirport = ?, originCity = ?, destinationAirport = ?, destinationCity = ?, precio = ?, fechaViaje = ?, hora = ?, idAvion = ? WHERE id = ?";
        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, originAirport);
            statement.setString(2, originCity);
            statement.setString(3, destinationAirport);
            statement.setString(4, destinationCity);
            statement.setDouble(5, precio);
            statement.setDate(6, fechaViaje);
            statement.setTime(7, hora);
            statement.setInt(8, idAvion);
            statement.setInt(9, id);
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Vuelo con ID " + id + " actualizado exitosamente.");
                
                return getFlightById(id);
            } else {
                System.out.println("No se encontró ningún vuelo con ID " + id + ".");
                return null;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al intentar actualizar el vuelo con ID " + id);
            return null;
        }
    }

    @Override
    public FlightRecord obtenerVueloById(int id) throws SQLException {
        String sql = "SELECT id, originAirport, originCity, destinationAirport, destinationCity, precio, fechaViaje, hora, idAvion FROM Viaje WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    FlightRecord flightRecord = new FlightRecord();
                    flightRecord.setId(resultSet.getInt("id"));
                    flightRecord.setOriginAirport(resultSet.getString("originAirport"));
                    flightRecord.setOriginCity(resultSet.getString("originCity"));
                    flightRecord.setDestinationAirport(resultSet.getString("destinationAirport"));
                    flightRecord.setDestinationCity(resultSet.getString("destinationCity"));
                    flightRecord.setPrice(resultSet.getDouble("precio"));
                    flightRecord.setTravelDate(resultSet.getDate("fechaViaje"));
                    flightRecord.setHora(resultSet.getTime("hora"));
                    flightRecord.setIdAvion(resultSet.getInt("idAvion"));
                    return flightRecord;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el vuelo con ID " + id);
        }
        return null;
    }

    @Override
    public void ActualizarVuelo(FlightRecord vuelo) throws SQLException {
        String sql = "UPDATE Viaje SET originAirport = ?, originCity = ?, destinationAirport = ?, destinationCity = ?, precio = ?, fechaViaje = ?, hora = ?, idAvion = ? WHERE id = ?";
        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, vuelo.getOriginAirport());
            statement.setString(2, vuelo.getOriginCity());
            statement.setString(3, vuelo.getDestinationAirport());
            statement.setString(4, vuelo.getDestinationCity());
            statement.setDouble(5, vuelo.getPrice());
            statement.setDate(6, vuelo.getTravelDate());
            statement.setTime(7, vuelo.getHora());
            statement.setInt(8, vuelo.getIdAvion());
            statement.setInt(9, vuelo.getId());
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Vuelo con ID " + vuelo.getId() + " actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún vuelo con ID " + vuelo.getId() + ".");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al intentar actualizar el vuelo con ID " + vuelo.getId());
        }
    }
}
