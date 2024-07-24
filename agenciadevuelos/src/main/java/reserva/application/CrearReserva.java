package reserva.application;

import reserva.domain.ServiceReserva;
import java.sql.Date;

public class CrearReserva {
    private ServiceReserva serviceReserva;

    public CrearReserva(ServiceReserva serviceReserva) {
        this.serviceReserva = serviceReserva;
    }

    public void execute(int idDetalleReserva, int idCliente, int idReservaViaje,  int viajeId) {
        serviceReserva.crearReserva(idDetalleReserva, idCliente, idReservaViaje, viajeId);
    }
}

