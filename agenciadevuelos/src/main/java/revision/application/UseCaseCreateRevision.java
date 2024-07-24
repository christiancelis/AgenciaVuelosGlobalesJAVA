package revision.application;

import revision.domain.Revision;
import revision.domain.ServiceRevision;

public class UseCaseCreateRevision {
    ServiceRevision serviceRevision;

    public UseCaseCreateRevision(ServiceRevision serviceRevision) {
        this.serviceRevision = serviceRevision;
    }

    public void execute(Revision revision){
        serviceRevision.createRevision(revision);
    }
    
}   
