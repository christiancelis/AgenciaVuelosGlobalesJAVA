package tarifa.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import tarifa.domain.ServiceTarifa;
import tarifa.domain.Tarifa;

public class TarifaRepository implements ServiceTarifa {

    @Override
    public void AggTarifa(int tarifa) {
        
        
    }

    @Override
    public ArrayList<Tarifa> ListTarifa() {
        ArrayList<Tarifa> tarifas = new ArrayList<>();
        String sql = "SELECT id, descripcion, detalle, valor FROM Tarifa";
        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            // Procesar el ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                String detalle = resultSet.getString("detalle");
                double valor = resultSet.getDouble("valor");
                
                // Crear un objeto Tarifa y añadirlo a la lista
                Tarifa tarifa = new Tarifa();
                tarifa.setId(id);
                tarifa.setDescripcion(descripcion);
                tarifa.setDetalle(detalle);
                tarifa.setValor(valor);
                tarifas.add(tarifa);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar tarifas: " + e.getMessage());
            e.printStackTrace();
        }
        
        return tarifas;
    }
    
        
  
    

}
