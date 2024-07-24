package documentType.application;

import documentType.domain.Documento;
import documentType.domain.ServiceDocumentType;

public class UseCaseUpdateDocumentType {
     ServiceDocumentType serviceDocumentType;

    public UseCaseUpdateDocumentType(ServiceDocumentType serviceDocumentType) {
        this.serviceDocumentType = serviceDocumentType;
    }

    public void execute(Documento documento){
        serviceDocumentType.UpdateDocumentType(documento);
    }
    

     
}
