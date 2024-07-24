package revision.application;

import revision.domain.ServiceRevision;

public class UseCaseDeleteRevision {
    ServiceRevision serviceRevision;

    public UseCaseDeleteRevision(ServiceRevision serviceRevision) {
        this.serviceRevision = serviceRevision;
    }

    public void execute(int id){
        serviceRevision.deleteRevision(id);
    }
}
