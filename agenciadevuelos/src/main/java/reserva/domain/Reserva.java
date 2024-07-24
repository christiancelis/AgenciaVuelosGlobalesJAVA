package reserva.domain;

import java.sql.Date;

public class Reserva {
  private  int idDetalleReserva ;
  private int idCliente;
  private  int idReservaViaje ;
  private int viajeId ;


  
  
public Reserva(int idDetalleReserva2, int idCliente2, int idReservaViaje2, Date fechaD, int viajeId2) {
}
public Reserva(int idDetalleReserva, int idCliente, int idReservaViaje, int viajeId) {
    this.idDetalleReserva = idDetalleReserva;
    this.idCliente = idCliente;
    this.idReservaViaje = idReservaViaje;
    this.viajeId = viajeId;
}
public int getIdDetalleReserva() {
    return idDetalleReserva;
}
public void setIdDetalleReserva(int idDetalleReserva) {
    this.idDetalleReserva = idDetalleReserva;
}
public int getIdCliente() {
    return idCliente;
}
public void setIdCliente(int idCliente) {
    this.idCliente = idCliente;
}
public int getIdReservaViaje() {
    return idReservaViaje;
}
public void setIdReservaViaje(int idReservaViaje) {
    this.idReservaViaje = idReservaViaje;
}
public int getViajeId() {
    return viajeId;
}
public void setViajeId(int viajeId) {
    this.viajeId = viajeId;
}
@Override
public String toString() {
    return "Reserva [idDetalleReserva=" + idDetalleReserva + ", idCliente=" + idCliente + ", idReservaViaje="
            + idReservaViaje + ", viajeId=" + viajeId + "]";
}


}
