package cliente.domain;

import java.util.ArrayList;

import documentType.domain.Documento;

public interface ServiceClient {
    Client getClientByDocument(String document);
    ArrayList <Client> getAllClients();
    void createClient(Client cliente);
    void UpdateClient(Client cliente);
    void deleteClientByDocument(String document);
}
