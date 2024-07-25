package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConfig {
    

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://roundhouse.proxy.rlwy.net:23964/railway";
        String user = "root"; 
        String password = "SGdeJOTGDpxhZvVLlnRQTNrhNGuzzRLv"; 
       // String url = "jdbc:mysql://localhost:3306/prueba2";
    
        return DriverManager.getConnection(url, user, password);
    }
}

