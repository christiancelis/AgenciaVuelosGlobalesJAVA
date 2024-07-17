package customer.domain;

public class Customer {
private int id;
private String nombre;
private int edad;
private int documentoId;

public Customer() {

}
public Customer(int id, String nombre, int edad, int documentoId) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad;
    this.documentoId = documentoId;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public int getEdad() {
    return edad;
}
public void setEdad(int edad) {
    this.edad = edad;
}
public int getDocumentoId() {
    return documentoId;
}
public void setDocumentoId(int documentoId) {
    this.documentoId = documentoId;
}
@Override
public String toString() {
    return "customer [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", documentoId=" + documentoId + "]";
}

}
