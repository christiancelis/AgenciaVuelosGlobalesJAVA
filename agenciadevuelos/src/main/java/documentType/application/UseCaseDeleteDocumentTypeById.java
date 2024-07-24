package documentType.application;

import documentType.domain.ServiceDocumentType;

public class UseCaseDeleteDocumentTypeById {
    ServiceDocumentType serviceDocumentType;

    public UseCaseDeleteDocumentTypeById(ServiceDocumentType serviceDocumentType) {
        this.serviceDocumentType = serviceDocumentType;
    }

    public void execute(int id){
        serviceDocumentType.deleteDocumentType(id);
    }
        
}
