package documentType.application;

import documentType.domain.Documento;
import documentType.domain.ServiceDocumentType;

public class UseCaseGetDocumentTypeById {
    ServiceDocumentType serviceDocumentType;

    public UseCaseGetDocumentTypeById(ServiceDocumentType serviceDocumentType) {
        this.serviceDocumentType = serviceDocumentType;
    }

    public Documento execute(int id){
        return serviceDocumentType.getDocumentTypeById(id);
    }
    
}
