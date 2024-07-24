package documentType.infrastructure.in;
import java.util.Scanner;
import documentType.application.UseCaseCreateDocumentType;
import documentType.application.UseCaseDeleteDocumentTypeById;
import documentType.application.UseCaseGetAllDocumentTypes;
import documentType.application.UseCaseGetDocumentTypeById;
import documentType.application.UseCaseUpdateDocumentType;
import documentType.domain.ServiceDocumentType;
import documentType.infrastructure.out.DocumentTypeRepository;

public class MenuDocument {
    public void start(){
       
        ServiceDocumentType serviceDocumentType = new DocumentTypeRepository();
        Scanner scanner = new Scanner(System.in);

        UseCaseGetAllDocumentTypes getAllDocumentTypes = new UseCaseGetAllDocumentTypes(serviceDocumentType);
        UseCaseGetDocumentTypeById getDocumentTypeById = new UseCaseGetDocumentTypeById(serviceDocumentType);
        UseCaseCreateDocumentType createDocumentType = new UseCaseCreateDocumentType(serviceDocumentType);
        UseCaseUpdateDocumentType updateDocumentType = new UseCaseUpdateDocumentType(serviceDocumentType);
        UseCaseDeleteDocumentTypeById deleteDocumentTypeById = new UseCaseDeleteDocumentTypeById(serviceDocumentType);



        DocumentController documentController = new DocumentController(getAllDocumentTypes,getDocumentTypeById,createDocumentType,updateDocumentType, deleteDocumentTypeById);


        

        while(true){
            
            int opcion = utils.Validation.leerNumero("Digite la opcion a seleccionar: ", scanner);
            switch (opcion) {
                case 1:
                    documentController.createDocumentType();
                    break;
                case 2:
                    documentController.getInfoDocumentType();
                    break;
                case 3:
                    documentController.UpdateDocumentType();
                    break;
                case 4:
                    documentController.deleteDocumentType();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            imprimirMenuDocument();
        }

    }
    private void imprimirMenuDocument(){
        System.out.println("Menu Documento");
        System.out.println("1. Registrar Tipo documento.");
        System.out.println("2. Consultar tipo de documento.");
        System.out.println("3. Actualizar tipo documento.");
        System.out.println("4. Eliminar tipo documento.");
        System.out.println("5. Salir.");
    }

}
