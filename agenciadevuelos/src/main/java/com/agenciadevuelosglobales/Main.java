package com.agenciadevuelosglobales;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.agenciadevuelosglobales.Menu.MenuManager;

import trayecto.domain.ServiceTrayecto;
import trayecto.infrastucture.in.TrayectoController;
import trayecto.infrastucture.out.TrayectoRepository;
import utils.AirportDatabase;
import viaje.application.ListVuelos;
import viaje.domain.FlightRecord;
import viaje.domain.ServiceFlight;
import viaje.infrastructure.in.ViajeController;
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
