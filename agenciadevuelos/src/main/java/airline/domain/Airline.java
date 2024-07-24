package airline.domain;

public class Airline {
    private int idAirline;
    private String nombreAirline;

    

    public Airline() {
    }

    public Airline(int idAirline, String nombreAirline) {
        this.idAirline = idAirline;
        this.nombreAirline = nombreAirline;
    }

    public int getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }

    public String getNombreAirline() {
        return nombreAirline;
    }

    public void setNombreAirline(String nombreAirline) {
        this.nombreAirline = nombreAirline;
    }

    

    
    
}
