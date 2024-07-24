package aeropuerto.domain;

public class Airport {
    private final String ident;
    private final String type;
    private final String name;
    private final double latitude;
    private final double longitude;
    private final String iataCode;

    public Airport(String ident, String type, String name, double latitude, double longitude, String iataCode) {
        this.ident = ident;
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.iataCode = iataCode;
    }

   

    public String getIdent() {
        return ident;
    }



    public String getType() {
        return type;
    }



    public String getName() {
        return name;
    }



    public double getLatitude() {
        return latitude;
    }



    public double getLongitude() {
        return longitude;
    }



    public String getIataCode() {
        return iataCode;
    }



    @Override
    public String toString() {
        return "Airport{" +
               "name='" + name + '\'' +
               ", iataCode='" + iataCode + '\'' +
               '}';
    }
}

