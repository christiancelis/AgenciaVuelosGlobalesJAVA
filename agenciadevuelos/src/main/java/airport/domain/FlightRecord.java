// package airport.domain;

// import utils.AirportDatabase;
// import java.sql.Connection;
// import java.sql.Date;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

// import config.DataBaseConfig;

// public  class FlightRecord {
//     private AirportDatabase.Airport originAirport;
//     private AirportDatabase.Airport destinationAirport;
//     private double price;
//     private Date travelDate; // Añadir fecha de viaje
//     private String originCity;
//     private String destinationCity;

//     public FlightRecord(AirportDatabase.Airport originAirport, AirportDatabase.Airport destinationAirport, double price, Date travelDate, String originCity, String destinationCity) {
//         this.originAirport = originAirport;
//         this.destinationAirport = destinationAirport;
//         this.price = price;
//         this.travelDate = travelDate;
//         this.originCity = originCity;
//         this.destinationCity = destinationCity;
//     }

//     public AirportDatabase.Airport getOriginAirport() {
//         return originAirport;
//     }

//     public AirportDatabase.Airport getDestinationAirport() {
//         return destinationAirport;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public Date getTravelDate() {
//         return travelDate;
//     }

//     public String getOriginCity() {
//         return originCity;
//     }

//     public String getDestinationCity() {
//         return destinationCity;
//     }

//     public void saveToDatabase() throws SQLException {
//         String sql = "INSERT INTO Viaje (originAirport, destinationAirport, precio, fechaViaje, originCity, destinationCity) VALUES (?, ?, ?, ?, ?, ?)";
//         try (Connection connection = DataBaseConfig.getConnection();
//              PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setString(1, originAirport.getName()); 
//             statement.setString(2, destinationAirport.getName()); 
//             statement.setDouble(3, price);
//             statement.setDate(4, travelDate);
//             statement.setString(5, originCity); 
//             statement.setString(6, destinationCity); 
//             statement.executeUpdate();
//         }
//     }

//     @Override
//     public String toString() {
//         return "FlightRecord{" +
//                 "Aeropuerto salida=" + originAirport +
//                 ", Aeropuerto llegada=" + destinationAirport +
//                 ", precio=" + price +
//                 ", fecha de viaje=" + travelDate +
//                 ", ciudad origen=" + originCity +
//                 ", ciudad destino=" + destinationCity +
//                 '}';
//     }
// }
