package layover.domain;

public class Layover {
private int id;
private int aeropuertoDestinoId;
private int aeropuertoOrigenId;
private int avionId;
private int viajeId;



public Layover() {
}

public Layover(int id, int aeropuertoDestinoId, int aeropuertoOrigenId, int avionId, int viajeId) {
    this.id = id;
    this.aeropuertoDestinoId = aeropuertoDestinoId;
    this.aeropuertoOrigenId = aeropuertoOrigenId;
    this.avionId = avionId;
    this.viajeId = viajeId;
}

public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public int getAeropuertoDestinoId() {
    return aeropuertoDestinoId;
}
public void setAeropuertoDestinoId(int aeropuertoDestinoId) {
    this.aeropuertoDestinoId = aeropuertoDestinoId;
}
public int getAeropuertoOrigenId() {
    return aeropuertoOrigenId;
}
public void setAeropuertoOrigenId(int aeropuertoOrigenId) {
    this.aeropuertoOrigenId = aeropuertoOrigenId;
}
public int getAvionId() {
    return avionId;
}
public void setAvionId(int avionId) {
    this.avionId = avionId;
}
public int getViajeId() {
    return viajeId;
}
public void setViajeId(int viajeId) {
    this.viajeId = viajeId;
}



}
