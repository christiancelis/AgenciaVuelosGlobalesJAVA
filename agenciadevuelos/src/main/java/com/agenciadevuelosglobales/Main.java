package com.agenciadevuelosglobales;
import java.util.Scanner;
import trayecto.domain.ServiceTrayecto;
import trayecto.infrastucture.in.TrayectoController;
import trayecto.infrastucture.out.TrayectoRepository;
import viaje.application.ListVuelos;
import viaje.domain.ServiceFlight;
import viaje.infrastructure.out.FlightRepositoryImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ServiceTrayecto serviceTrayecto = new TrayectoRepository();
        ServiceFlight serviceFlight = new FlightRepositoryImpl();
        ListVuelos listVuelos = new ListVuelos(serviceFlight);
      TrayectoController trayectoController = new TrayectoController(serviceTrayecto, sc, listVuelos)  ;    
      trayectoController.CreateEscala();
      
        //    MenuManager menuManager = new MenuManager();
        // menuManager.startMainMenu();
            // Inicializa AirportDatabase

            // // Obtén una conexión a la base de datos usando DataBaseConfig
            // Connection connection = DataBaseConfig.getConnection();

            // // // Inicializa FlightRepositoryImpl con la conexión
        //     FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();

        //     // // // Inicializa ViajeController con AirportDatabase y FlightRepositoryImpl
        //      ViajeController viajeController = new ViajeController(flightRepository);

        //     // // Inicia el controlador
        //    viajeController.start();
        }
    

}
