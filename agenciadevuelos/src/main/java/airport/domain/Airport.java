package airport.domain;

public class Airport {
    private int id;
    private String nombre;
    private int idCiudad;

    public Airport(int id, String nombre, int idCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.idCiudad = idCiudad;
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

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    
}
