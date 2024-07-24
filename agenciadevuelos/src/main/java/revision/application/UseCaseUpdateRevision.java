package revision.application;

import revision.domain.Revision;
import revision.domain.ServiceRevision;

public class UseCaseUpdateRevision {
    ServiceRevision serviceRevision;

    public UseCaseUpdateRevision(ServiceRevision serviceRevision) {
        this.serviceRevision = serviceRevision;
    }

    public void execute(Revision revision){
        serviceRevision.updateRevision(revision);
    }
    
}
