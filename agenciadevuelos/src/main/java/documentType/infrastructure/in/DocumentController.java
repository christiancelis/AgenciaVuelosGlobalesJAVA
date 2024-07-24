package documentType.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;


import documentType.application.UseCaseCreateDocumentType;
import documentType.application.UseCaseDeleteDocumentTypeById;
import documentType.application.UseCaseGetAllDocumentTypes;
import documentType.application.UseCaseGetDocumentTypeById;
import documentType.application.UseCaseUpdateDocumentType;
import documentType.domain.Documento;
import utils.Validation;

public class DocumentController {
    private final UseCaseGetAllDocumentTypes useCaseGetAllDocumentTypes;
    private final UseCaseGetDocumentTypeById useCaseGetDocumentTypeById;
    private final UseCaseCreateDocumentType useCaseCreateDocumentType;
    private final UseCaseUpdateDocumentType useCaseUpdateDocumentType;
    private final UseCaseDeleteDocumentTypeById useCaseDeleteDocumentTypeById;
    private final Scanner scanner = new Scanner(System.in);
    

    public DocumentController(UseCaseGetAllDocumentTypes useCaseGetAllDocumentTypes,
            UseCaseGetDocumentTypeById useCaseGetDocumentTypeById, UseCaseCreateDocumentType useCaseCreateDocumentType,
            UseCaseUpdateDocumentType useCaseUpdateDocumentType,
            UseCaseDeleteDocumentTypeById useCaseDeleteDocumentTypeById) {
        this.useCaseGetAllDocumentTypes = useCaseGetAllDocumentTypes;
        this.useCaseGetDocumentTypeById = useCaseGetDocumentTypeById;
        this.useCaseCreateDocumentType = useCaseCreateDocumentType;
        this.useCaseUpdateDocumentType = useCaseUpdateDocumentType;
        this.useCaseDeleteDocumentTypeById = useCaseDeleteDocumentTypeById;
    }


    public void getInfoDocumentType(){
        imprimirTiposDocumento();
        int idTipoDocumento = Validation.leerNumero("Digite el id del tipo que desea consultar: ", scanner);
        Documento tipodocumento = useCaseGetDocumentTypeById.execute(idTipoDocumento);
        if(tipodocumento!=null){imprimirInfoTipoDocumento(tipodocumento);}
    }

    public void createDocumentType(){
        Documento documento = new Documento();
        String nombre = Validation.leerdato("Digite el tipo de documento: ", scanner);
        Boolean validarnombre = ValidarNombreTipoDocumento(nombre);
        if(validarnombre==true){System.out.println("Tipo de documento ya existe"); return;}
        documento.setTipo(nombre);
        try {
            useCaseCreateDocumentType.execute(documento);
            System.out.println("Documento creado satisfactoriamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateDocumentType(){

        imprimirTiposDocumento();
        int idTipoDocumento = Validation.leerNumero("Digite id del tipo de documento a actualizar: ", scanner);
        Documento documento = useCaseGetDocumentTypeById.execute(idTipoDocumento);
        if(documento==null){return;}
        String nombre = Validation.leerdato("Digite el tipo de documento: ", scanner);
        Boolean validarnombre = ValidarNombreTipoDocumento(nombre);
        if(validarnombre==true){System.out.println("Tipo de documento ya existe"); return;}
        documento.setTipo(nombre);
        try {
            useCaseUpdateDocumentType.execute(documento);
            System.out.println("Tipo de documento actualizado satisfactoriamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDocumentType(){
        imprimirTiposDocumento();;
        int idTipoDocumento = Validation.leerNumero("Digite id del tipo de documento a eliminar: ", scanner);
        Documento documento = useCaseGetDocumentTypeById.execute(idTipoDocumento);
        if(documento==null){return;}
        useCaseDeleteDocumentTypeById.execute(documento.getIdDocumento());
    };


    private boolean ValidarNombreTipoDocumento(String nombre){
        ArrayList <Documento> listaTiposDocumento = useCaseGetAllDocumentTypes.execute();
        Boolean val = listaTiposDocumento.stream().anyMatch((e)-> e.getTipo().toLowerCase().equals(nombre.toLowerCase()));
        if(val==false){return false;}
        return true;
    }

    private void imprimirInfoTipoDocumento(Documento documento){
        System.out.println("Informacion Tipo de documento");
        System.out.println("id:" + documento.getIdDocumento() + " Tipo: " + documento.getTipo());
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void imprimirTiposDocumento(){
        ArrayList <Documento> listaTiposDocumento = useCaseGetAllDocumentTypes.execute();
        Consumer <Documento> imprimirTipos = (e)-> System.out.println("Id: " + e.getIdDocumento() + " Tipo:" + e.getTipo());
        listaTiposDocumento.forEach(imprimirTipos);
    }

    
}
