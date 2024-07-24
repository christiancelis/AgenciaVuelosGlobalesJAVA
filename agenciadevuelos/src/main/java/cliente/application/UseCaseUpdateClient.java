package cliente.application;

import cliente.domain.Client;
import cliente.domain.ServiceClient;

public class UseCaseUpdateClient {
    ServiceClient serviceClient;

    public UseCaseUpdateClient(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public void execute(Client client){
        serviceClient.UpdateClient(client);
    }

}
