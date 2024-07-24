package reserva.domain;

import java.sql.Date;

public interface ServiceReserva {
void crearReserva(int idDetalleReserva, int idCliente, int idReservaViaje, int viajeId);
}
