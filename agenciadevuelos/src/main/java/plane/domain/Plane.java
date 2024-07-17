package plane.domain;

import java.sql.Date;

public class Plane {
    private int id;
    private String matricula;
    private int capacidad;
    private Date fechaFabricacion;
    private int modeloId;
    private int estadoId;
    
    public Plane() {

    }

    public Plane(int id, String matricula, int capacidad, Date fechaFabricacion, int modeloId, int estadoId) {
        this.id = id;
        this.matricula = matricula;
        this.capacidad = capacidad;
        this.fechaFabricacion = fechaFabricacion;
        this.modeloId = modeloId;
        this.estadoId = estadoId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }
    public void setFechaFabricacion(Date fechaFabricacion2) {
        this.fechaFabricacion = fechaFabricacion2;
    }
    public int getModeloId() {
        return modeloId;
    }
    public void setModeloId(int modeloId) {
        this.modeloId = modeloId;
    }
    public int getEstadoId() {
        return estadoId;
    }
    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    } 
}
