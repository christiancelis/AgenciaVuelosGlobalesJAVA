package cliente.application;

import java.util.ArrayList;

import cliente.domain.Client;
import cliente.domain.ServiceClient;

public class UseCaseGetAllClients {
    ServiceClient serviceClient;

    public UseCaseGetAllClients(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public ArrayList<Client> execute(){
        return serviceClient.getAllClients();
    }
    
}
