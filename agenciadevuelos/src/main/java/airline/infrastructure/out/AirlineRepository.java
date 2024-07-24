package airline.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import airline.domain.Airline;
import airline.domain.ServiceAirline;
import config.DataBaseConfig;

public class AirlineRepository implements ServiceAirline {

    @Override
    public Airline getAirlineById(int id) {
        Airline aerolinea = new Airline();
        String sql = "SELECT id, nombre " + 
        "FROM Aerolinea " +
        "WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        aerolinea.setIdAirline(rs.getInt("id"));
                        aerolinea.setNombreAirline(rs.getString("nombre"));
                    } else {
                        System.out.println("Aerolinea no encontrada");
                        return null;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return aerolinea;
    }

    @Override
    public ArrayList<Airline> getAllAirlines() {
        ArrayList <Airline> listaerolineas = new ArrayList<>();
        String sql = "Select id, nombre from Aerolinea";
        try(Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            try(ResultSet rs = statement.executeQuery(sql)){
                while(rs.next()){
                    Airline aerolinea = new Airline();
                    aerolinea.setIdAirline(rs.getInt("id"));
                    aerolinea.setNombreAirline(rs.getString("nombre"));
                    listaerolineas.add(aerolinea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaerolineas;
    }
    
}
