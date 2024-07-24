package revision.domain;

import java.util.ArrayList;

public interface ServiceRevision {
    void createRevision(Revision revision);
    void updateRevision(Revision revision);
    void deleteRevision(int id);
    ArrayList<Revision> getAllRevision();
    Revision getRevisionById(int id);
}
