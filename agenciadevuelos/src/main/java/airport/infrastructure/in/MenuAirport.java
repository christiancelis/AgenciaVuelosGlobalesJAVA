package airport.infrastructure.in;

import airport.application.UseCaseCreateAirport;
import airport.application.UseCaseDeleteAirport;
import airport.application.UseCaseGetAirport;
import airport.application.UseCaseGetAllAirports;
import airport.application.UseCaseGetCities;
import airport.application.UseCaseUpdateAirport;
import airport.domain.ServiceAirport;
import airport.infrastructure.out.AirportRepository;

public class MenuAirport {

    //Service/Repository
    ServiceAirport RepositoryAirport = new AirportRepository();
    
    //UseCases
    UseCaseCreateAirport useCaseCreateAirport = new UseCaseCreateAirport(RepositoryAirport);
    UseCaseGetCities useCaseGetCities = new UseCaseGetCities(RepositoryAirport);
    UseCaseGetAirport useCaseGetAirport = new UseCaseGetAirport(RepositoryAirport);
    UseCaseGetAllAirports useCaseGetAllAirports = new UseCaseGetAllAirports(RepositoryAirport);
    UseCaseDeleteAirport useCaseDeleteAirport = new UseCaseDeleteAirport(RepositoryAirport);
    UseCaseUpdateAirport useCaseUpdateAirport = new UseCaseUpdateAirport(RepositoryAirport);
    
    //UIController
    AirportController airportController = new AirportController(useCaseCreateAirport, useCaseGetCities,useCaseGetAirport,useCaseGetAllAirports, useCaseDeleteAirport, useCaseUpdateAirport);


}   
