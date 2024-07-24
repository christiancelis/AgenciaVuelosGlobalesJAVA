package airport.domain.entity;

public class Airport extends City{
    private int id;
    private String nombre;


    public Airport() {
       super();
    }


    public Airport(int idPais, String nombrePais, int idCiudad, String nombreciudad, int id, String nombre) {
        super(idPais, nombrePais, idCiudad, nombreciudad);
        this.id = id;
        this.nombre = nombre;
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
    
}
