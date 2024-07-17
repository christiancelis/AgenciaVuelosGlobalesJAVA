package revision.application;

import revision.domain.Revision;
import revision.domain.ServiceRevision;

public class CreateRevision {
private ServiceRevision servicerRevision;

public CreateRevision(ServiceRevision servicerRevision) {
    this.servicerRevision = servicerRevision;
}

public void execute(Revision revision) {
    servicerRevision.CreateRevision(revision);
}


}
