package documentType.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DataBaseConfig;
import documentType.domain.Documento;
import documentType.domain.ServiceDocumentType;

public class DocumentTypeRepository implements ServiceDocumentType{

    @Override
    public ArrayList<Documento> getAllDocumentTypes() {
         ArrayList <Documento> listaTiposDocument = new ArrayList<>();
        String sql = "Select id, nombre from Documento";
        try(Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            try(ResultSet rs = statement.executeQuery(sql)){
                while(rs.next()){
                    Documento documento = new Documento();
                    documento.setIdDocumento(rs.getInt("id"));
                    documento.setTipo(rs.getString("nombre"));
                    listaTiposDocument.add(documento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTiposDocument;
    }

    @Override
    public Documento getDocumentTypeById(int id) {
        System.out.println(id);
        Documento documento = new Documento();
        String sql = "SELECT id, nombre " + 
        "FROM Documento " +
        "WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        documento.setIdDocumento(rs.getInt("id"));
                        documento.setTipo(rs.getString("nombre"));
                    } else {
                        System.out.println("Tipo de documento no encontrado");
                        return null;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return documento;
    }

    @Override
    public void createDocumentType(Documento documento) {
        String slq = "Insert into Documento (nombre) VALUES(?)";
        try(Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement =  connection.prepareStatement(slq,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1,documento.getTipo());
            statement.executeUpdate();
            try(ResultSet generateKeys = statement.getGeneratedKeys()){;
                if(generateKeys.next()){
                    documento.setIdDocumento(generateKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateDocumentType(Documento documento) {
        String sql = "UPDATE Documento SET nombre = ?  WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, documento.getTipo());
            statement.setInt(2, documento.getIdDocumento());
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
    public void deleteDocumentType(int id) {
        String deleteSQL = "DELETE FROM Documento WHERE id=?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            int rowDelete = statement.executeUpdate(); // Ejecuta la actualización sin pasar el SQL nuevamente
            if(rowDelete>0){
                System.out.println("Tipo de documento eliminado Satisfactoriamente");
            }else{
                System.out.println("Error  Tipo de documento no se elimino correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
