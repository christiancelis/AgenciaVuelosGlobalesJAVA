package documentType.application;

import documentType.domain.Documento;
import documentType.domain.ServiceDocumentType;

public class UseCaseCreateDocumentType {
    ServiceDocumentType serviceDocumentType;

    public UseCaseCreateDocumentType(ServiceDocumentType serviceDocumentType) {
        this.serviceDocumentType = serviceDocumentType;
    }

    public void execute(Documento documento){
        serviceDocumentType.createDocumentType(documento);
    }

    
}
