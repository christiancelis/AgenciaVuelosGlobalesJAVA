package com.agenciadevuelosglobales;

import java.io.IOException;
import java.sql.SQLException;

import com.agenciadevuelosglobales.Menu.MenuManager;

import utils.AirportDatabase;
import viaje.infrastructure.in.ViajeController;
import viaje.infrastructure.out.FlightRepositoryImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        
            
      
           MenuManager menuManager = new MenuManager();
        menuManager.startMainMenu();
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
