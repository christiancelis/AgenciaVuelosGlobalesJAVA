package rol.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import rol.domain.Rol;
import rol.domain.ServiceRol;

public class RolRepository implements ServiceRol {

    @Override
    public Rol RolId(int id) {
        
        return null;
    }

    @Override
    public ArrayList<Rol> getAllRol() {
         ArrayList<Rol> listaRoles = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Rol";
        try(Connection connection = DataBaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);) {
            
            try(ResultSet rs = statement.executeQuery(sql);){
                while (rs.next()) {
                    Rol rol = new Rol();
                    rol.setId(rs.getInt("id"));
                    rol.setNombre(rs.getString("nombre"));
                    listaRoles.add(rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRoles;
        
    }

}
