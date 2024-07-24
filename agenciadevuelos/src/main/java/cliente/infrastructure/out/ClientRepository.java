package cliente.infrastructure.out;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cliente.domain.Client;
import cliente.domain.ServiceClient;
import config.DataBaseConfig;


public class ClientRepository implements ServiceClient {

    @Override
    public Client getClientByDocument(String document) {
        Client cliente = new Client();
        String sql = "SELECT c.id, c.nombre, c.edad, c.documento_id, c.cedula, d.nombre AS tipoDocumento " + 
        "FROM Cliente AS c " +
        "INNER JOIN Documento AS d ON d.id = c.documento_id " + 
        "WHERE c.cedula = ?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, document);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        cliente.setId(rs.getInt("id"));
                        cliente.setNombre(rs.getString("nombre"));
                        cliente.setEdad(rs.getInt("edad"));
                        cliente.setIdDocumento(rs.getInt("documento_id"));
                        cliente.setDocument(rs.getString("cedula"));
                        cliente.setTipo(rs.getString("tipoDocumento"));
                    } else {
                        System.out.println("Cliente no encontrado");
                        return null;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cliente;
    }

    @Override 
    public ArrayList<Client> getAllClients() {
        ArrayList <Client> listaClientes = new ArrayList<>();
        String sql = "select c.id, c.nombre, c.edad, c.documento_id, c.cedula, d.id , d.nombre" + 
                    " from Cliente as c Inner join Documento as d on d.id= c.documento_id"; 
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                while(rs.next()){
                    Client cliente = new Client();
                    cliente.setId(rs.getInt("c.id"));
                    cliente.setNombre(rs.getString("c.nombre"));
                    cliente.setEdad(rs.getInt("c.edad"));
                    cliente.setIdDocumento(rs.getInt("c.documento_id"));
                    cliente.setDocument(rs.getString("c.cedula"));
                    cliente.setTipo(rs.getString("d.nombre"));
                    listaClientes.add(cliente);
                }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }
    @Override
    public void createClient(Client cliente) {
        String slq = "Insert into Cliente (nombre,edad,cedula,documento_id) VALUES(?,?,?,?)";
        try(Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement =  connection.prepareStatement(slq,PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1,cliente.getNombre());
            statement.setInt(2, cliente.getEdad());
            statement.setString(3, cliente.getDocument());
            statement.setInt(4, cliente.getIdDocumento());
            statement.executeUpdate();
            try(ResultSet generateKeys = statement.getGeneratedKeys()){;
                if(generateKeys.next()){
                    cliente.setId(generateKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   


    @Override
    public void UpdateClient(Client cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, edad = ?, cedula = ?, documento_id = ? WHERE cedula = ?";
        try (Connection connection = DataBaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cliente.getNombre());
            statement.setInt(2, cliente.getEdad());
            statement.setString(3, cliente.getDocument());
            statement.setInt(4, cliente.getIdDocumento());
            statement.setString(5, cliente.getDocument());
            int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("El cliente fue actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el cliente con la cedula proporcionado.");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteClientByDocument(String cedula) {
        String deleteSQL = "DELETE FROM Cliente WHERE cedula=?";
        try (Connection connection = DataBaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setString(1, cedula);
            int rowDelete = statement.executeUpdate();
            if(rowDelete>0){
                System.out.println("Cliente eliminado Satisfactoriamente");
            }else{
                System.out.println("Error Cliente no se elimino correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

}
 