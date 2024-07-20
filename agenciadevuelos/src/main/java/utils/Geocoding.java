package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Geocoding {

    public static LocationDetails getLocationDetails(String city) throws Exception {
        String url = "https://nominatim.openstreetmap.org/search?q=" + city + "&format=json&addressdetails=1&limit=1";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Imprimir la respuesta completa
            System.out.println("Respuesta del servidor: " + response.toString());

            // Parse the JSON response
            if (response.toString().startsWith("[")) {
                // Handle JSONArray response
                JSONArray jsonArray = new JSONArray(response.toString());
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    double lat = jsonObject.getDouble("lat");
                    double lon = jsonObject.getDouble("lon");
                    JSONObject address = jsonObject.getJSONObject("address");

                    String cityName = address.optString("city", 
                                address.optString("town", 
                                address.optString("village", 
                                address.optString("county", "Unknown city"))));
                    String countryName = address.optString("country", "Unknown country");

                    return new LocationDetails(lat, lon, cityName, countryName);
                } else {
                    throw new Exception("No results found for the city: " + city);
                }
            } else if (response.toString().startsWith("{")) {
                // Handle JSONObject response (error message)
                JSONObject jsonObject = new JSONObject(response.toString());
                String message = jsonObject.optString("message", "Unknown error");
                throw new Exception("Error from API: " + message);
            } else {
                throw new Exception("Unexpected response format: " + response.toString());
            }
        }
    }

    public static class LocationDetails {
        private final double lat;
        private final double lon;
        private final String city;
        private final String country;

        public LocationDetails(double lat, double lon, String city, String country) {
            this.lat = lat;
            this.lon = lon;
            this.city = city;
            this.country = country;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }
    }
}
