package airCrew.domain;

public class AirCrew {
    private int id;
    private int idEmpleado;
    private int idViaje; //escalas

    public AirCrew(int id, int idEmpleado, int idViaje) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idViaje = idViaje;
    }

    public AirCrew() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }
    
}
