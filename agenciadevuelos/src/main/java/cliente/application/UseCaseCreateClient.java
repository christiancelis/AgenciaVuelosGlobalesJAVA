package cliente.application;

import cliente.domain.Client;
import cliente.domain.ServiceClient;

public class UseCaseCreateClient {
    ServiceClient serviceClient;

    public UseCaseCreateClient(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public void execute(Client cliente){
        serviceClient.createClient(cliente);
    }
    
}
