package airline.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import airline.application.UseCaseGetAirlinesById;
import airline.application.UseCaseGetAllAirlines;
import airline.domain.Airline;
import utils.Validation;


public class AirlineController {
    private final UseCaseGetAirlinesById useCaseGetAirlinesById;
    private final UseCaseGetAllAirlines useCaseGetAllAirlines;
    Scanner scanner = new Scanner(System.in);
    


    public AirlineController(UseCaseGetAirlinesById useCaseGetAirlinesById,
            UseCaseGetAllAirlines useCaseGetAllAirlines) {
        this.useCaseGetAirlinesById = useCaseGetAirlinesById;
        this.useCaseGetAllAirlines = useCaseGetAllAirlines;
    }

    public  void GetAirlineById(){
        imprimirTodasAerolineas();
        int idAerolinea = Validation.leerNumero("Digite el id de la aerolinea que desea consultar: ", scanner);
        Airline aerolinea = useCaseGetAirlinesById.execute(idAerolinea);
        if(aerolinea!=null){imprimirAerolinea(aerolinea);}
    }

    private void imprimirAerolinea(Airline aerolinea){
        System.out.println("Informacion Aerolinea");
        System.out.println("id:" + aerolinea.getIdAirline() + " Tipo: " + aerolinea.getNombreAirline());
        System.out.println("-----------------------------------------------------------------------------------------");
    }


     private void imprimirTodasAerolineas(){
        ArrayList <Airline> listaAerolineas = useCaseGetAllAirlines.execute();
        Consumer <Airline> imprimiraerolineas = (e)-> System.out.println("Id: " + e.getIdAirline() + " Nombre:" + e.getNombreAirline());
        listaAerolineas.forEach(imprimiraerolineas);
    }


}
