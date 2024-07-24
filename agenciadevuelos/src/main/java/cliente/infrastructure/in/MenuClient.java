package cliente.infrastructure.in;

import java.util.Scanner;

import cliente.application.UseCaseCreateClient;
import cliente.application.UseCaseDeleteClientByDocument;
import cliente.application.UseCaseGetAllClients;
import cliente.application.UseCaseGetClientByDocument;
import cliente.application.UseCaseUpdateClient;
import cliente.domain.ServiceClient;
import cliente.infrastructure.out.ClientRepository;
import documentType.application.UseCaseGetAllDocumentTypes;
import documentType.domain.ServiceDocumentType;
import documentType.infrastructure.out.DocumentTypeRepository;

public class MenuClient {

    public void start(){

        Scanner scanner = new Scanner(System.in);
        ServiceClient serviceClient = new ClientRepository();
        ServiceDocumentType serviceDocumentType = new DocumentTypeRepository();
    
        UseCaseGetClientByDocument getClientByDocument = new UseCaseGetClientByDocument(serviceClient);
        UseCaseGetAllClients getAllClients = new UseCaseGetAllClients(serviceClient);
        UseCaseCreateClient CreateClient = new UseCaseCreateClient(serviceClient);
        UseCaseGetAllDocumentTypes getAllDocumentTypes = new UseCaseGetAllDocumentTypes(serviceDocumentType);
        UseCaseUpdateClient updateClientByDocument = new UseCaseUpdateClient(serviceClient);
        UseCaseDeleteClientByDocument deleteClientByDocument = new UseCaseDeleteClientByDocument(serviceClient);

        ClientController clientController = new ClientController(getClientByDocument,getAllClients,CreateClient,getAllDocumentTypes, updateClientByDocument,deleteClientByDocument);

        while(true){
            imprimirMenuClient();

            int opçion = utils.Validation.leerNumero("Digite una opcion: ", scanner);
    
            switch (opçion) {
                case 1:
                    clientController.RegisterClient();
                    break;
                case 2:
                    clientController.getClientByDocument();
                    break;
                case 3:
                    clientController.updateClient();
                    break;
                case 4:
                    clientController.deleteClientByDocument();
                    break;
                case 5:
                    return;            
                default:
                System.out.println("Opcion no valida");
                    break;
            }
       
        }
    }

    private void imprimirMenuClient(){
        System.out.println("Menu Cliente");
        System.out.println("1. Registrar cliente.");
        System.out.println("2. Consultar informacion del cliente.");
        System.out.println("3. Actualizar informacion.");
        System.out.println("4. Eliminar cliente.");
        System.out.println("5. Salir.");
    }

}
