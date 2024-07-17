package employee.domain;

import java.sql.Date;

public class Employee {
private int id;
private String nombre;
private Date fechaIngreso;
private int rolId;
private int aerolineaId;



public Employee() {

}
public Employee(int id, String nombre, Date fechaIngreso, int rolId, int aerolineaId) {
    this.id = id;
    this.nombre = nombre;
    this.fechaIngreso = fechaIngreso;
    this.rolId = rolId;
    this.aerolineaId = aerolineaId;
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
public Date getFechaIngreso() {
    return fechaIngreso;
}
public void setFechaIngreso(Date fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
}
public int getRolId() {
    return rolId;
}
public void setRolId(int rolId) {
    this.rolId = rolId;
}
public int getAerolineaId() {
    return aerolineaId;
}
public void setAerolineaId(int aerolineaId) {
    this.aerolineaId = aerolineaId;
}
@Override
public String toString() {
    return "Employee [id=" + id + ", nombre=" + nombre + ", fechaIngreso=" + fechaIngreso + ", rolId=" + rolId
            + ", aerolineaId=" + aerolineaId + "]";
}


}
