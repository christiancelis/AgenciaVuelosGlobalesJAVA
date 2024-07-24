package cliente.application;

import cliente.domain.Client;
import cliente.domain.ServiceClient;

public class UseCaseGetClientByDocument {
    ServiceClient serviceClient;

    public UseCaseGetClientByDocument(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public Client execute(String document){
        return serviceClient.getClientByDocument(document);
    }

}
