package documentType.application;

import java.util.ArrayList;

import documentType.domain.Documento;
import documentType.domain.ServiceDocumentType;

public class UseCaseGetAllDocumentTypes {
    ServiceDocumentType serviceDocumentType;

    public UseCaseGetAllDocumentTypes(ServiceDocumentType serviceDocumentType) {
        this.serviceDocumentType = serviceDocumentType;
    }



    public ArrayList <Documento> execute(){
         return serviceDocumentType.getAllDocumentTypes();
    }
    
}
