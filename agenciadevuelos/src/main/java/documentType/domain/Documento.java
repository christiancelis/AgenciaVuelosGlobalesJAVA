package documentType.domain;

public class Documento {
    private int idDocumento ;
    private String tipo;
    
    public Documento(int idDocumento, String tipo) {
        this.idDocumento = idDocumento;
        this.tipo = tipo;
    }

    public Documento() {
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

    
   
    
    

    

}
