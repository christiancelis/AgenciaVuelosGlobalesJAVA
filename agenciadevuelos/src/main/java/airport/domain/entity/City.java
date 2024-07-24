package airport.domain.entity;

public class City extends Country{
    private int idCiudad;
    private String nombreciudad;

    public City() {
        super();
    }
    
    public City(int idPais, String nombrePais, int idCiudad, String nombreciudad) {
        super(idPais, nombrePais);
        this.idCiudad = idCiudad;
        this.nombreciudad = nombreciudad;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreciudad() {
        return nombreciudad;
    }

    public void setNombreciudad(String nombreciudad) {
        this.nombreciudad = nombreciudad;
    }

}
