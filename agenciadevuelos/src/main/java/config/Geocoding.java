package config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Geocoding {
    private static final String API_URL = "https://nominatim.openstreetmap.org/search";

    public static LocationDetails getLocationDetails(String city) throws Exception {
        String url = API_URL + "?q=" + city + "&format=json&addressdetails=1&limit=1";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed to get data from API. Response Code: " + responseCode);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            JSONArray jsonArray = new JSONArray(response.toString());
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");
                JSONObject address = jsonObject.getJSONObject("address");

                String cityName = address.optString("city"); 
                        address.optString("town", 
                        address.optString("village", 
                        address.optString("county", "Unknown city")));
                String countryName = address.optString("country", "Unknown country");

                return new LocationDetails(lat, lon, cityName, countryName);
            } else {
                throw new Exception("No results found for the city: " + city);
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

        public double getLat() { return lat; }
        public double getLon() { return lon; }
        public String getCity() { return city; }
        public String getCountry() { return country; }
    }
}
