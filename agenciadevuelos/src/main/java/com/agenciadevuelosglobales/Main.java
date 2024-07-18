package com.agenciadevuelosglobales;

import com.agenciadevuelosglobales.Menu.MenuManager;

import trip.application.GetAllTrip;
import trip.domain.ServiceTrip;
import trip.infrastructure.in.TripController;


public class Main {
    private ServiceTrip serviceTrip;
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
         menuManager.startMainMenu();

  

    }
    
}