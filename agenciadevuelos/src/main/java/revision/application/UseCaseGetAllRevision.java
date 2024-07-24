package revision.application;

import java.util.ArrayList;

import revision.domain.Revision;
import revision.domain.ServiceRevision;

public class UseCaseGetAllRevision {
    ServiceRevision serviceRevision;

    public UseCaseGetAllRevision(ServiceRevision serviceRevision) {
        this.serviceRevision = serviceRevision;
    }

    public ArrayList<Revision> execute(){
        return serviceRevision.getAllRevision();
    }

    
}
