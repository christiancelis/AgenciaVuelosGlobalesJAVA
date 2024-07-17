package planeManufacture.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.MBeanNotificationInfo;

import config.DataBaseConfig;
import planeManufacture.domain.Manufacture;
import planeManufacture.domain.ServiceManufacture;
import planeModel.domain.Model;

public class ManufactureRepository implements ServiceManufacture{

    @Override
    public ArrayList<Manufacture> getAllManufactures() {
        ArrayList<Manufacture> listaFabricantes = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Fabricante";
        try(Connection connection = DataBaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);) {
            
            try(ResultSet rs = statement.executeQuery(sql);){
                while (rs.next()) {
                    Manufacture fabricante = new Manufacture();
                    fabricante.setId(rs.getInt("id"));
                    fabricante.setNombre(rs.getString("nombre"));
                    listaFabricantes.add(fabricante);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaFabricantes;
    }

    @Override
    public Manufacture getManufactureById(Integer id) {
        String sql = "SELECT id, nombre FROM Fabricante WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Manufacture fabricante = new Manufacture();
                    fabricante.setId(rs.getInt("id"));
                    fabricante.setNombre(rs.getString("nombre"));
                    return fabricante;
                }
            }
        } catch (SQLException e) {
            // Manejar excepciones, por ejemplo, loggear o lanzar una excepción de aplicación
            e.printStackTrace();
        }
        return null; // Retornar null si no se encuentra el modelo con el ID dado
    }
}
