package revision.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import revision.domain.Revision;
import revision.domain.ServiceRevision;

public class RevisionRepository implements ServiceRevision{

    @Override
    public void createRevision(Revision revision) {
       String slq = "call CreateRevision(?,?,?,?)";
        try(Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement =  connection.prepareStatement(slq,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1,revision.getFechaRevision().toString());
            statement.setInt(2, revision.getIdAvion());
            statement.setInt(3, revision.getIdEmpleado());
            statement.setString(4, revision.getDescripcion());
            statement.executeUpdate();
            try(ResultSet generateKeys = statement.getGeneratedKeys()){;
                if(generateKeys.next()){
                    revision.setId(generateKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRevision(Revision revision) {
        String sql = "call ActualizarRevision(?,?,?,?,?) ";
        try (Connection connection = DataBaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1, revision.getId());
        statement.setString(2,revision.getFechaRevision().toString());
        statement.setInt(3, revision.getIdAvion());
        statement.setInt(4, revision.getIdEmpleado());
        statement.setString(5, revision.getDescripcion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("El documento fue actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el documento con el id proporcionado.");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRevision(int id) {
        String deleteSQL = "delete FROM Revision where id=?";
        try (Connection connection = DataBaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
       statement.setInt(1, id);
       int rowDelete = statement.executeUpdate(); // Ejecuta la actualización sin pasar el SQL nuevamente
       if(rowDelete>0){
           System.out.println("Revision eliminada Satisfactoriamente");
       }else{
           System.out.println("Error  Revision  no se elimino correctamente");
       }
   } catch (Exception e) {
       e.printStackTrace();
   }

    }

    @Override
    public ArrayList<Revision> getAllRevision() {
        ArrayList <Revision> listaRevisiones= new ArrayList<>();
        String sql = "select r.id, r.fechaRevision , r.Avion_id , re.id AS idRevisionEmpleado, re.Empleado_id , re.descripcion \n" + //
                    "from Revision as r\n" + //
                    "inner join Revision_Empleado as re on re.Revision_id= r.id;";
        try(Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            try(ResultSet rs = statement.executeQuery(sql)){
                while(rs.next()){
                    Revision revision = new Revision();
                    revision.setId(rs.getInt("id"));
                    revision.setFechaRevision(rs.getDate("fechaRevision"));
                    revision.setIdAvion(rs.getInt("Avion_id"));
                    revision.setIdEmpleado(rs.getInt("Empleado_id"));
                    revision.setDescripcion(rs.getString("descripcion"));
                    listaRevisiones.add(revision);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }           
        return listaRevisiones;
    }

    @Override
    public Revision getRevisionById(int id) {
        Revision revision = null;
        String sql = "SELECT r.id, r.fechaRevision, r.Avion_id, re.id AS idRevisionEmpleado, re.Empleado_id, re.descripcion " +
                     "FROM Revision AS r " +
                     "INNER JOIN Revision_Empleado AS re ON r.id = re.Revision_id " +
                     "WHERE r.id = ?";
        
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    revision = new Revision();
                    revision.setId(rs.getInt("id"));
                    revision.setFechaRevision(rs.getDate("fechaRevision"));
                    revision.setIdAvion(rs.getInt("Avion_id"));
                    revision.setIdEmpleado(rs.getInt("Empleado_id"));
                    revision.setDescripcion(rs.getString("descripcion"));
                } else {
                   System.out.println("La revision no se encontro");
                   return null;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return revision;
    }
    
}
