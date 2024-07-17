package user.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import user.domain.User;
import user.domain.RolPermisoUsuario.RolPermiso;
import user.domain.ServiceUser;

public class UserRepository implements ServiceUser {


    public static boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE usuario = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

    @Override
    public void CreateUser(User authUser) {
        String sql = "INSERT INTO Usuario (id, usuario, contraseña, rolId) VALUES (?,?,?,?)";

        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, authUser.getId());    
            statement.setString(2, authUser.getUsuario());
            statement.setString(3, authUser.getContraseña());
            statement.setInt(4, authUser.getRolId());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    authUser.setId(generatedKeys.getInt(1)); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User FindUserById(int id) {
        String sql = "select id,usuario,contraseña,rolId from Usuario where id = ?";
        User user = null;
        try (Connection connection = DataBaseConfig.getConnection();PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsuario(resultSet.getString("usuario"));
                    user.setContraseña(resultSet.getString("contraseña"));
                    user.setRolId(resultSet.getInt("rolId"));
                }
            }
            
           } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public User UpdateUserById(int id,String user,String contraseña,int rolnumber) {
       String sql = "UPDATE Usuario SET  usuario = ?, contraseña = ?, rolId = ? WHERE id = ?";

       try (Connection connection = DataBaseConfig.getConnection();PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user);
            statement.setString(2, contraseña);
            statement.setLong(3, rolnumber);
            statement.setInt(4, id);


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                 return FindUserById(id); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

    @Override
    public User ValidationUser(int id, String contraseña) {
        return null;
    }



    @Override
    public ArrayList<RolPermiso> getAllPermiso(String validacion, int idRol, int idUsuario) {
        ArrayList<RolPermiso> permisos = new ArrayList<>();
        String sql = "SELECT rp.idRol, rp.idPermiso, p.nombre AS nombrePermiso, rp.validacion, u.id " +
                     "FROM RolPermiso rp " +
                     "JOIN Permiso p ON p.id = rp.idPermiso " +
                     "JOIN Usuario u ON u.rolId = rp.idRol " +
                     "WHERE u.id = ?";
    
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, idUsuario);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RolPermiso rolPermiso = new RolPermiso();
                    rolPermiso.setIdRol(resultSet.getInt("idRol"));
                    rolPermiso.setIdPermiso(resultSet.getInt("idPermiso"));
                    rolPermiso.setNombrePermiso(resultSet.getString("nombrePermiso"));
                    rolPermiso.setValidacion(resultSet.getString("validacion"));
                    rolPermiso.setIdUsuario(resultSet.getInt("id"));
    
                    permisos.add(rolPermiso); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return permisos;
    }


   

   

  
    
}