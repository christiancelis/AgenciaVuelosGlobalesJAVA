package planeModel.domain;

public class Model {
    private Integer id;
    private String nombre;
    private Integer idFabricante;

    public Model(Integer id, String nombre, Integer idFabricante) {
        this.id = id;
        this.nombre = nombre;
        this.idFabricante = idFabricante;
    }

    public Model() {
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

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }
        
}
