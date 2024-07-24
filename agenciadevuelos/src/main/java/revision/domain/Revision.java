package revision.domain;

import java.util.Date;

public class Revision {
    private int id;
    private Date fechaRevision;
    private int idAvion;
    private int idEmpleado;
    private String descripcion;

    public Revision(int id, Date fechaRevision, int idAvion, int idEmpleado, String descripcion) {
        this.id = id;
        this.fechaRevision = fechaRevision;
        this.idAvion = idAvion;
        this.idEmpleado = idEmpleado;
        this.descripcion = descripcion;
    }

    public Revision() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

    

}
