package cliente.domain;

import documentType.domain.Documento;

public class Client extends Documento{

    private int id;
    private String nombre;
    private String document;
    private int edad;

    public Client(int idDocumento, String tipo, int id, String nombre, String document, int edad) {
        super(idDocumento, tipo);
        this.id = id;
        this.nombre = nombre;
        this.document = document;
        this.edad = edad;
    }

    public Client() {
        super();
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    

}
