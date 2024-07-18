package trip.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import trip.domain.ServiceTrip;
import trip.domain.Trip;

public class TripRepository implements ServiceTrip {

    public ArrayList<Trip> getAllTrip(int limit, int offset) {
        ArrayList<Trip> trips = new ArrayList<>();
        String sql = "SELECT v.id AS idViaje, a1.nombre AS origenAero, vp1.ciudad AS ciudadOrigen, " +
                     "a2.nombre AS destinoAero, vp2.ciudad AS ciudadDestino, " +
                     "v.fechaViaje AS fecha, v.precio AS precio " +
                     "FROM Viaje AS v " +
                     "JOIN Aeropuerto AS a1 ON v.aeropuertoOrigen = a1.id " +
                     "JOIN Aeropuerto AS a2 ON v.aeropuertoDestino = a2.id " +
                     "JOIN VistaPaisAero AS vp1 ON vp1.idCiudad = a1.Ciudad_id " +
                     "JOIN VistaPaisAero AS vp2 ON vp2.idCiudad = a2.Ciudad_id " +
                     "LIMIT ? OFFSET ?";

        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, limit);
            statement.setInt(2, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trip trip = new Trip();
                    trip.setId(resultSet.getInt("idViaje"));
                    trip.setAeropuertoOrigen(resultSet.getString("origenAero"));
                    trip.setCiudadOrigen(resultSet.getString("ciudadOrigen"));
                    trip.setAeropuertoDestino(resultSet.getString("destinoAero"));
                    trip.setCiudadDestino(resultSet.getString("ciudadDestino"));
                    trip.setFechaViaje(resultSet.getDate("fecha"));
                    trip.setPrecio(resultSet.getInt("precio"));

                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trips;
    }

    @Override
    public ArrayList<Trip> getAllTrip() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTrip'");
    }
}
