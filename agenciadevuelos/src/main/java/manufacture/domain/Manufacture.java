package manufacture.domain;

public class Manufacture {
    private Integer id;
    private String nombre;

    public Manufacture(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Manufacture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
