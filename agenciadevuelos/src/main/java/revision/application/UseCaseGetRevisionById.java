package revision.application;

import revision.domain.Revision;
import revision.domain.ServiceRevision;

public class UseCaseGetRevisionById {
    ServiceRevision serviceRevision;

    public UseCaseGetRevisionById(ServiceRevision serviceRevision) {
        this.serviceRevision = serviceRevision;
    }

    public Revision execute(int id){
        return serviceRevision.getRevisionById(id);
    }
}


