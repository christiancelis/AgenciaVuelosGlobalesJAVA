package tarifa.domain;

public class Tarifa {
private int id;
private String descripcion;
private String detalle;
private Double valor;

public Tarifa() {
}

public Tarifa(int id, String descripcion, String detalle, Double valor) {
    this.id = id;
    this.descripcion = descripcion;
    this.detalle = detalle;
    this.valor = valor;
}

public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getDescripcion() {
    return descripcion;
}
public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}
public String getDetalle() {
    return detalle;
}
public void setDetalle(String detalle) {
    this.detalle = detalle;
}
public Double getValor() {
    return valor;
}
public void setValor(Double valor) {
    this.valor = valor;
}
@Override
public String toString() {
    return "Tarifa [id=" + id + ", descripcion=" + descripcion + ", detalle=" + detalle + ", valor=" + valor + "]";
}




}
