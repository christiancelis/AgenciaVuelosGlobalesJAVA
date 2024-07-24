package cliente.application;

import cliente.domain.ServiceClient;

public class UseCaseDeleteClientByDocument {
    ServiceClient serviceClient;

    public UseCaseDeleteClientByDocument(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public void execute (String document){
        serviceClient.deleteClientByDocument(document);
    }

    
}
