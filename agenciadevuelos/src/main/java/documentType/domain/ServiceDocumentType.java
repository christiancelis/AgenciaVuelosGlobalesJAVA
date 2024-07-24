package documentType.domain;

import java.util.ArrayList;

public interface ServiceDocumentType {
    ArrayList<Documento> getAllDocumentTypes();
    Documento getDocumentTypeById(int id);
    void createDocumentType(Documento documento);
    void UpdateDocumentType(Documento documento);
    void deleteDocumentType(int id);

}
