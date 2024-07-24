package reserva.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import config.DataBaseConfig;

public class ServiceReserva {
    private Connection connection;

    public ServiceReserva(Connection connection) {
        this.connection = connection;
    }

    public void crearReserva(int idDetalleReserva, int idCliente, int idReservaViaje, java.sql.Date fechaD, int viajeId) {
        String sql = "{call ReservaD(?,?,?,?,?)}";
        try (Connection connection = DataBaseConfig.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)){
                callableStatement.setInt(1, idDetalleReserva);
                callableStatement.setInt(2, idCliente);
                callableStatement.setInt(3, idReservaViaje);
                callableStatement.setDate(4, fechaD);
                callableStatement.setInt(5, viajeId);
                callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
