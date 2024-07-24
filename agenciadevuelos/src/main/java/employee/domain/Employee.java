package employee.domain;

import airline.domain.Airline;

public class Employee extends Airline    {
    private int id;
    private String nombre;
    private String fechaIngreso;
    
    public Employee() {
        super();
    }

    public Employee(int id, String nombre, int id2, String nombre2, String fechaIngreso) {
        super(id, nombre);
        id = id2;
        nombre = nombre2;
        this.fechaIngreso = fechaIngreso;
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

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
}
