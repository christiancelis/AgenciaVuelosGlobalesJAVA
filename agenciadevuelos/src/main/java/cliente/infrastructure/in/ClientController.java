package cliente.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import cliente.application.UseCaseCreateClient;
import cliente.application.UseCaseDeleteClientByDocument;
import cliente.application.UseCaseGetAllClients;
import cliente.application.UseCaseGetClientByDocument;
import cliente.application.UseCaseUpdateClient;
import cliente.domain.Client;
import documentType.application.UseCaseGetAllDocumentTypes;
import documentType.domain.Documento;
import utils.Validation;

public class ClientController {
    private final UseCaseGetClientByDocument useCaseGetClientByDocument;
    private final UseCaseGetAllClients useCaseGetAllClients;
    private final UseCaseCreateClient useCaseCreateClient;
    private final UseCaseGetAllDocumentTypes useCaseGetAllDocumentTypes;
    private final UseCaseUpdateClient useCaseUpdateClientByDocument;
    private final UseCaseDeleteClientByDocument useCaseDeleteClientByDocument;
    private final Scanner scanner = new Scanner(System.in);


    public ClientController(UseCaseGetClientByDocument useCaseGetClientByDocument,
            UseCaseGetAllClients useCaseGetAllClients, UseCaseCreateClient useCaseCreateClient,
            UseCaseGetAllDocumentTypes useCaseGetAllDocumentTypes,
            UseCaseUpdateClient useCaseUpdateClientByDocument,
            UseCaseDeleteClientByDocument useCaseDeleteClientByDocument) {
        this.useCaseGetClientByDocument = useCaseGetClientByDocument;
        this.useCaseGetAllClients = useCaseGetAllClients;
        this.useCaseCreateClient = useCaseCreateClient;
        this.useCaseGetAllDocumentTypes = useCaseGetAllDocumentTypes;
        this.useCaseUpdateClientByDocument = useCaseUpdateClientByDocument;
        this.useCaseDeleteClientByDocument = useCaseDeleteClientByDocument;
    }

    public void RegisterClient(){
        System.out.println("Registro de Cliente");
        String Cedula = Validation.leerdato("Digite la cedula del cliente: ", scanner);
        Boolean clienteExiste = verificarExistenciaCliente(Cedula);
        if(clienteExiste){System.out.println("Cliente ya existe");return;}
        Client cliente = new Client();
        cliente.setDocument(Cedula);
        cliente.setNombre(Validation.leerdato("Digite el nombre del cliente: ", scanner));
        cliente.setEdad(Validation.leerNumero("Digite la edad del cliente: ", scanner));
        imprimirTiposDocumento();
        Integer idTipo = Validation.leerNumero("Digite el id del tipo de documento: ", scanner);
        Boolean documentoExiste = verificarExistenciaTipoDocumento(idTipo);
        if(!documentoExiste){System.out.println("Error - id no valido"); return;}
        cliente.setIdDocumento(idTipo);
        try {
            useCaseCreateClient.execute(cliente);
            System.out.println("Cliente creado satisfactoriamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getClientByDocument(){
        imprimirClientes();
        String cedula = utils.Validation.leerdato("Digite la cedula del cliente: ", scanner);
        Boolean clienteExiste = verificarExistenciaCliente(cedula);
        if(!clienteExiste){System.out.println("Error cedula no valida"); return;}
        Client cliente = useCaseGetClientByDocument.execute(cedula);
        System.out.println("\n****Cliente encontrado****");
        imprimirInfoClient(cliente);
    }

    public void updateClient(){
        imprimirClientes();
        String Cedula = Validation.leerdato("Digite la cedula del cliente: ", scanner);
        Boolean clienteExiste = verificarExistenciaCliente(Cedula);
        if(!clienteExiste){System.out.println("Cliente no encontrado");return;}
        Client cliente = useCaseGetClientByDocument.execute(Cedula);
        imprimirMenuDatosCliente();
        int opcion = Validation.leerNumero("Digite la opcion a seleccionar: ", scanner);
        switch (opcion) {
            case 1:
                System.out.println("Valor actual: " + cliente.getNombre());
                cliente.setNombre(Validation.leerdato("Digite el valor a actualizar: ", scanner));
                break;
            case 2:
                System.out.println("Valor actual: " + cliente.getEdad());
                cliente.setEdad(Validation.leerNumero("Digite el valor a actualizar: ", scanner));
                break;
            case 3:
                System.out.println("Valor actual: " + cliente.getDocument());
                String nuevoDocumento = Validation.leerdato("Digite el valor a actualizar", scanner);
                Boolean dExiste = verificarExistenciaCliente(nuevoDocumento);
                if(dExiste){System.out.println("Documento ya existe");return;}
                cliente.setDocument(nuevoDocumento);
                break;
            case 4:
                System.out.println("Valor actual: " + cliente.getTipo());
                imprimirTiposDocumento();
                Integer idTipo = Validation.leerNumero("Digite el valor a actualizar: ", scanner);
                Boolean documentoExiste = verificarExistenciaTipoDocumento(idTipo);
                if(!documentoExiste){System.out.println("Error - id no valido"); return;}
                cliente.setIdDocumento(idTipo);
                break;
            default:
                System.out.println("Opcion no valida");
                return;
        }
        
        useCaseUpdateClientByDocument.execute(cliente);
        imprimirInfoClient(cliente);
    }

    public void deleteClientByDocument(){
        imprimirClientes();
        String cedula = Validation.leerdato("Digite la cedula del cliente a eliminar: ", scanner);
        Boolean existe = verificarExistenciaCliente(cedula);
        if(!existe){System.out.println("Cedula no valida"); return;}
        useCaseDeleteClientByDocument.execute(cedula);
    }
    
    private boolean verificarExistenciaCliente(String cedula){
        ArrayList<Client> listaClientes = useCaseGetAllClients.execute();
        Boolean existe = listaClientes.stream().anyMatch((e)-> e.getDocument().equals(cedula));
        if(existe){return true;}
        return false;
    }

    private boolean verificarExistenciaTipoDocumento(int idTipo){
        ArrayList <Documento> listaTiposDocumento = useCaseGetAllDocumentTypes.execute();
        Boolean TipoExiste = listaTiposDocumento.stream().anyMatch((e)-> e.getIdDocumento()==idTipo);
        if(TipoExiste){return true;}
        return false;
    }

    private void imprimirClientes(){
        ArrayList<Client> listaClientes = useCaseGetAllClients.execute();
        Consumer <Client> imprimirInfo = e -> System.out.println("Cedula: " + e.getDocument() + " Nombre Cliente: " + e.getNombre());
        listaClientes.forEach(imprimirInfo);
    }

    private void imprimirTiposDocumento(){
        ArrayList <Documento> listaTiposDocumento = useCaseGetAllDocumentTypes.execute();
        Consumer <Documento> imprimirTipos = (e)-> System.out.println("Id: " + e.getIdDocumento() + " Tipo:" + e.getTipo());
        listaTiposDocumento.forEach(imprimirTipos);
    }

    private void imprimirInfoClient(Client cliente){
        System.out.println("id:" + cliente.getId() + " Nombre: " + cliente.getNombre() + " Edad: " + cliente.getEdad() + " Cedula: " + cliente.getDocument() + " tipo: " + cliente.getTipo() );
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void imprimirMenuDatosCliente(){
        System.out.println("Datos de cliente");
        System.out.println("1. Nombre");
        System.out.println("2. Edad");
        System.out.println("3. Cedula");
        System.out.println("4. Tipo documento");        
    }

}
