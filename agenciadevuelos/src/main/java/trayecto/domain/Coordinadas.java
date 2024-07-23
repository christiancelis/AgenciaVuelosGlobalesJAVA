
package trayecto.domain;

public class Coordinadas {
    private final String id;
    private final String type;
    private final String name;
    private final double latitude;
    private final double longitude;
    private final String city;

    public Coordinadas(String id, String type, String name, double latitude, double longitude, String city) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getCity() { return city; }
}
