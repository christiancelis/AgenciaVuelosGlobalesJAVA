package trip.domain;

import java.sql.Date;

public class Trip {
private int id;
private String aeropuertoOrigen;
private String ciudadOrigen;
private String aeropuertoDestino;
private String ciudadDestino;
private Date fechaViaje;
private int precio;




public Trip() {
}
public Trip(int id, String aeropuertoOrigen, String ciudadOrigen, String aeropuertoDestino, String ciudadDestino,
        Date fechaViaje, int precio) {
    this.id = id;
    this.aeropuertoOrigen = aeropuertoOrigen;
    this.ciudadOrigen = ciudadOrigen;
    this.aeropuertoDestino = aeropuertoDestino;
    this.ciudadDestino = ciudadDestino;
    this.fechaViaje = fechaViaje;
    this.precio = precio;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getAeropuertoOrigen() {
    return aeropuertoOrigen;
}
public void setAeropuertoOrigen(String aeropuertoOrigen) {
    this.aeropuertoOrigen = aeropuertoOrigen;
}
public String getCiudadOrigen() {
    return ciudadOrigen;
}
public void setCiudadOrigen(String ciudadOrigen) {
    this.ciudadOrigen = ciudadOrigen;
}
public String getAeropuertoDestino() {
    return aeropuertoDestino;
}
public void setAeropuertoDestino(String aeropuertoDestino) {
    this.aeropuertoDestino = aeropuertoDestino;
}
public String getCiudadDestino() {
    return ciudadDestino;
}
public void setCiudadDestino(String ciudadDestino) {
    this.ciudadDestino = ciudadDestino;
}
public Date getFechaViaje() {
    return fechaViaje;
}
public void setFechaViaje(Date fechaViaje) {
    this.fechaViaje = fechaViaje;
}
public int getPrecio() {
    return precio;
}
public void setPrecio(int precio) {
    this.precio = precio;
}
@Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", aeropuertoOrigen='" + aeropuertoOrigen + '\'' +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                ", aeropuertoDestino='" + aeropuertoDestino + '\'' +
                ", ciudadDestino='" + ciudadDestino + '\'' +
                ", fechaViaje=" + fechaViaje +
                ", precio=" + precio +
                '}';
    }





}
