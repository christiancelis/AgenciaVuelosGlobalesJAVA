package airport.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import airport.domain.ServiceAirport;
import airport.domain.entity.Airport;
import airport.domain.entity.City;
import airport.domain.entity.Country;
import config.DataBaseConfig;

public class AirportRepository implements ServiceAirport{

    @Override
    public void createAirport(Airport aeropuerto) {
        String sql = "INSERT INTO Aeropuerto(nombre,Ciudad_id) VALUES (?,?)";
        try (
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1, aeropuerto.getNombre());
            statement.setInt(2, aeropuerto.getIdCiudad());
            statement.executeUpdate();
            try(ResultSet generateKeys = statement.getGeneratedKeys()){;
                if(generateKeys.next()){
                    aeropuerto.setId(generateKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<City> getCities() {
        ArrayList<City> listaCiudades = new ArrayList<>();
        String sql = "SELECT id, nombre, Pais_id FROM Ciudad";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    City ciudad = new City();
                    ciudad.setIdCiudad(rs.getInt("id"));
                    ciudad.setNombreciudad(rs.getString("nombre"));
                    ciudad.setIdPais(rs.getInt("Pais_id"));
                    listaCiudades.add(ciudad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCiudades;
    }

    @Override
    public ArrayList<Country> getCountry(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCountry'");
    }

    @Override
    public Airport getAirport(int id){
        Airport aeropuerto = new Airport();
        String sql = "select ae.id, ae.nombre,ae.Ciudad_id ,ci.ciudad,ci.idPais, ci.pais from Aeropuerto as ae\n" + //
                        "inner join VistaPaisAero as ci on ci.idCiudad=ae.Ciudad_id\n" + //
                        "where ae.id = ?; ";
                        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                        aeropuerto.setId(rs.getInt("id"));
                        aeropuerto.setNombre(rs.getString("nombre"));
                        aeropuerto.setIdCiudad(rs.getInt("Ciudad_id"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aeropuerto;
    }

    @Override
    public ArrayList<Airport> getAllAirpots() {
        ArrayList <Airport> listaAeropuerto = new ArrayList<>();
        String sql = "select ae.id, ae.nombre,ae.Ciudad_id ,ci.ciudad,ci.idPais, ci.pais from Aeropuerto as ae\n" + //
                        "inner join VistaPaisAero as ci on ci.idCiudad=ae.Ciudad_id\n";
        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        Airport aeropuerto = new Airport();
                        aeropuerto.setId(rs.getInt("id"));
                        aeropuerto.setNombre(rs.getString("nombre"));
                        aeropuerto.setIdCiudad(rs.getInt("Ciudad_id"));
                        aeropuerto.setNombreciudad(rs.getString("ciudad"));
                        aeropuerto.setIdPais(rs.getInt("idPais"));
                        aeropuerto.setNombrePais(rs.getString("pais"));
                        listaAeropuerto.add(aeropuerto);
                    }
                       
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAeropuerto;
    }

    @Override
    public void deleteAirport(int id) {
        String deleteSQL = "DELETE FROM Aeropuerto WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            int rowDelete = statement.executeUpdate(); // Ejecuta la actualización sin pasar el SQL nuevamente
            if(rowDelete>0){
                System.out.println("Aeropuerto eliminado Satisfactoriamente");
            }else{
                System.out.println("Error Aeopuerto no se elimino correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAirport(Airport aeropuerto) {
        String updateSQL = "UPDATE Aeroùerto SET nombre = ?, Ciudad_id = ? WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSQL)) {
                
            statement.setString(1,aeropuerto.getNombre());
            statement.setInt(2, aeropuerto.getIdCiudad());
            statement.setInt(3, aeropuerto.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El aeropuerto fue actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el aeropuerto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
