package trayecto.domain;

public class Trayecto  {
    private int id;
    private String originCity;
    private String destinoCity;
    private int idViaje;


    
    public Trayecto() {
    }
    public Trayecto(int id, String originCity, String destinoCity, int idViaje) {
        this.id = id;
        this.originCity = originCity;
        this.destinoCity = destinoCity;
        this.idViaje = idViaje;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOriginCity() {
        return originCity;
    }
    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }
    public String getDestinoCity() {
        return destinoCity;
    }
    public void setDestinoCity(String destinoCity) {
        this.destinoCity = destinoCity;
    }
  
    public int getIdViaje() {
        return idViaje;
    }
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }
   
    @Override
    public String toString() {
        return "Trayecto [originCity=" + originCity + ", destinoCity=" + destinoCity + ", idViaje=" + idViaje + "]";
    }

   
}
