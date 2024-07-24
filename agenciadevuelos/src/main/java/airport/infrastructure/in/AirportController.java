package airport.infrastructure.in;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import airport.application.UseCaseCreateAirport;
import airport.application.UseCaseDeleteAirport;
import airport.application.UseCaseGetAirport;
import airport.application.UseCaseGetAllAirports;
import airport.application.UseCaseGetCities;
import airport.application.UseCaseUpdateAirport;
import airport.domain.entity.Airport;
import airport.domain.entity.City;
import utils.Validation;

public class AirportController{

    private final UseCaseCreateAirport useCaseCreateAirport;
    private final UseCaseGetCities useCaseGetCities;
    private final UseCaseGetAirport useCaseGetAirport;
    private final UseCaseGetAllAirports useCaseGetAllAirports;
    private final UseCaseDeleteAirport useCaseDeleteAirport;
    private final UseCaseUpdateAirport useCaseUpdateAirport;





    private final Scanner scanner = new Scanner(System.in);


    

    public AirportController(UseCaseCreateAirport useCaseCreateAirport, UseCaseGetCities useCaseGetCities,
            UseCaseGetAirport useCaseGetAirport, UseCaseGetAllAirports useCaseGetAllAirports,
            UseCaseDeleteAirport useCaseDeleteAirport, UseCaseUpdateAirport useCaseUpdateAirport) {
        this.useCaseCreateAirport = useCaseCreateAirport;
        this.useCaseGetCities = useCaseGetCities;
        this.useCaseGetAirport = useCaseGetAirport;
        this.useCaseGetAllAirports = useCaseGetAllAirports;
        this.useCaseDeleteAirport = useCaseDeleteAirport;
        this.useCaseUpdateAirport = useCaseUpdateAirport;
    }

    public void registerAirport() {
        Airport aeropuerto = new Airport();
        aeropuerto.setNombre(Validation.leerdato("Digite el nombre del aeropuerto", scanner));
        
        ArrayList<City> listaCiudades = useCaseGetCities.execute();

        listaCiudades.forEach((e)-> System.out.println("id: " + e.getIdCiudad() + " Nombre: " + e.getNombreciudad()));

        int idCiudad = Validation.leerNumero("Digite el id de la ciudad", scanner);

        Optional <City> obj = listaCiudades.stream().filter((e)-> e.getIdCiudad()==idCiudad).findFirst();
        if(!obj.isPresent()){
            System.out.println("Error id no valido, no se puedo registrar el aeropuerto");
            return;
        }    
        aeropuerto.setIdCiudad(idCiudad);
        useCaseCreateAirport.execute(aeropuerto);
        System.out.println("Aeropuerto Registrado");
    }

    public void getInfoAirport(){
        ArrayList<Airport> listaaeropuerto = useCaseGetAllAirports.execute();
        listaaeropuerto.forEach((e)-> System.out.println("id: "+ e.getId())); 
        
        int idaeropuerto = Validation.leerNumero("Digite el id del aeropuerto a ver", scanner);

        Optional <Airport> obj = listaaeropuerto.stream().filter((e)->e.getId()==idaeropuerto ).findAny();

        if(!obj.isPresent()){
            System.out.println("error, id no valido");
            return;
        }

        Airport aeropuerto = obj.get();

        System.out.println("Datos Aeropuerto:");
        System.out.println("id: " + aeropuerto.getId() + " Nombre: " +  aeropuerto.getNombre() + " Ciudad: " + aeropuerto.getNombreciudad() + " Pais: " + aeropuerto.getNombrePais());

    }

    public void deleteAirport(){
        ArrayList<Airport> listaaeropuerto = useCaseGetAllAirports.execute();
        listaaeropuerto.forEach((e)-> System.out.println("id: "+ e.getId())); 
        
        int idaeropuerto = Validation.leerNumero("Digite el id del aeropuerto a eliminar", scanner);

        Optional <Airport> obj = listaaeropuerto.stream().filter((e)->e.getId()==idaeropuerto ).findAny();

        if(!obj.isPresent()){
            System.out.println("error, id no valido");
            return;
        }

        useCaseDeleteAirport.execute(idaeropuerto);
        System.out.println("Aeropuerto eliminado con exito");
    }

    public void updateAirport(){
        ArrayList<Airport> listaaeropuerto = useCaseGetAllAirports.execute();
        listaaeropuerto.forEach((e)-> System.out.println("id: "+ e.getId())); 
        
        int idaeropuerto = Validation.leerNumero("Digite el id del aeropuerto a actualizar", scanner);

        Optional <Airport> obj = listaaeropuerto.stream().filter((e)->e.getId()==idaeropuerto ).findAny();

        if(!obj.isPresent()){
            System.out.println("error, id no valido");
            return;
        }

        Airport aeropuerto = obj.get();

        System.out.println("Nombre actual: " + aeropuerto.getNombre());
        aeropuerto.setNombre(Validation.leerdato("Digite el nombre a actualizar", scanner));
        useCaseUpdateAirport.execute(aeropuerto);
    }

    
    




    




    
    
}
