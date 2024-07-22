package viaje.infrastructure.out;

import viaje.domain.ServiceFlightRepository;
import viaje.domain.FlightRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import plane.domain.Plane;
import rol.domain.Rol;

public class FlightRepositoryImpl implements ServiceFlightRepository {

   
    @Override
    public void GuardarViaje(FlightRecord flightRecord) throws SQLException {
        String sql = "INSERT INTO Viaje (originAirport, originCity, destinationAirport, destinationCity, precio, fechaViaje, hora) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, flightRecord.getOriginAirport());
            statement.setString(2, flightRecord.getOriginCity());
            statement.setString(3, flightRecord.getDestinationAirport());
            statement.setString(4, flightRecord.getDestinationCity());
            statement.setDouble(5, flightRecord.getPrice());
            statement.setDate(6, flightRecord.getTravelDate()); // LocalDateTime to SQL timestamp
            statement.setTime(7, flightRecord.getHora());
    
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<FlightRecord> GetAllVuelos() {
        ArrayList<FlightRecord> listaVuelos = new ArrayList<>();
        String sql = "SELECT id, originAirport, originCity, destinationAirport, destinationCity, precio, fechaViaje, hora FROM Viaje";
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
        // Listar todos los vuelos
        ArrayList<FlightRecord> listaVuelos = GetAllVuelos();
    
        // Mostrar todos los vuelos
        if (listaVuelos.isEmpty()) {
            System.out.println("No hay vuelos disponibles para mostrar.");
        } else {
            System.out.println("Lista de vuelos:");
            for (FlightRecord vuelo : listaVuelos) {
                System.out.println("ID: " + vuelo.getId() + ", Origen: " + vuelo.getOriginCity() + 
                                   ", Destino: " + vuelo.getDestinationCity() + 
                                   ", Precio: " + vuelo.getPrice() +
                                   ", Fecha: " + vuelo.getTravelDate() + 
                                   ", Hora: " + vuelo.getHora());
            }
        }
    
        // Eliminar el vuelo con el ID especificado
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



   
    }


    

