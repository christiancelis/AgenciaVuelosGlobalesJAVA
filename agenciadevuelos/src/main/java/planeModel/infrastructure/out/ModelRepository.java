package planeModel.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import planeModel.domain.Model;
import planeModel.domain.ServiceModel;

public class ModelRepository implements ServiceModel{

    @Override
    public ArrayList<Model> getAllModels() {
        ArrayList<Model> listaModelos = new ArrayList<>();
        String sql = "SELECT id, nombre, Fabricante_id FROM Modelo";
        try(Connection connection = DataBaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);) {
            
            try(ResultSet rs = statement.executeQuery(sql);){
                while (rs.next()) {
                    Model modelo = new Model();
                    modelo.setId(rs.getInt("id"));
                    modelo.setNombre(rs.getString("nombre"));
                    modelo.setIdFabricante(rs.getInt("Fabricante_id"));
                    listaModelos.add(modelo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaModelos;
    }

    @Override
    public Model getModelById(Integer id) {
           String sql = "SELECT id, nombre, Fabricante_id FROM Modelo WHERE id = ?";
           Model modelo = new Model();
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    modelo.setId(rs.getInt("id"));
                    modelo.setNombre(rs.getString("nombre"));
                    modelo.setIdFabricante(rs.getInt("Fabricante_id"));
                    return modelo;
                }
            }
        } catch (SQLException e) {
            // Manejar excepciones, por ejemplo, loggear o lanzar una excepción de aplicación
            e.printStackTrace();
        }
        return modelo;
    }
}
    

