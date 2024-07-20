package com.agenciadevuelosglobales;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import utils.AirportDatabase;
import utils.DistanceCalculator;
import utils.Geocoding;

public class Main {
    private static final double COST_PER_KM = 0.1; // Costo por kilómetro

    public static void main(String[] args) {
        // MenuManager menuManager = new MenuManager();
        // menuManager.startMainMenu();
        
       Scanner scanner = new Scanner(System.in);
        AirportDatabase airportDatabase;

        try {
            airportDatabase = new AirportDatabase();
        } catch (IOException e) {
            System.out.println("Error al cargar la base de datos de aeropuertos: " + e.getMessage());
            return;
        }

        System.out.print("Ingrese la ciudad de origen: ");
        String origin = scanner.nextLine();

        System.out.print("Ingrese la ciudad de destino: ");
        String destination = scanner.nextLine();

        try {
            // Obtener detalles de la ubicación
            Geocoding.LocationDetails originDetails = Geocoding.getLocationDetails(origin);
            Geocoding.LocationDetails destinationDetails = Geocoding.getLocationDetails(destination);

            // Validar ciudad y país
            System.out.println("Ciudad de origen: " + originDetails.getCity() + ", País: " + originDetails.getCountry());
            System.out.println("Ciudad de destino: " + destinationDetails.getCity() + ", País: " + destinationDetails.getCountry());

            // Buscar aeropuertos
            List<AirportDatabase.Airport> originAirports = airportDatabase.getAirports(originDetails.getCity());
            List<AirportDatabase.Airport> destinationAirports = airportDatabase.getAirports(destinationDetails.getCity());

            if (originAirports.isEmpty()) {
                System.out.println("No se encontraron aeropuertos en la ciudad de origen.");
            } else {
                System.out.println("Aeropuertos en la ciudad de origen:");
                for (int i = 0; i < originAirports.size(); i++) {
                    System.out.println((i + 1) + ". " + originAirports.get(i).getName() + " (" + originAirports.get(i).getIataCode() + ")");
                }
            }

            if (destinationAirports.isEmpty()) {
                System.out.println("No se encontraron aeropuertos en la ciudad de destino.");
            } else {
                System.out.println("Aeropuertos en la ciudad de destino:");
                for (int i = 0; i < destinationAirports.size(); i++) {
                    System.out.println((i + 1) + ". " + destinationAirports.get(i).getName() + " (" + destinationAirports.get(i).getIataCode() + ")");
                }
            }

            // Calcular distancia
            double distance = DistanceCalculator.calculateDistance(
                    originDetails.getLat(), originDetails.getLon(),
                    destinationDetails.getLat(), destinationDetails.getLon()
            );

            // Calcular precio
            double price = distance * COST_PER_KM;

            System.out.println("La distancia entre " + originDetails.getCity() + " y " + destinationDetails.getCity() + " es de " + distance + " km.");
            System.out.println("El precio calculado es: $" + price);
        } catch (Exception e) {
            System.out.println("Error al calcular la distancia: " + e.getMessage());
        }
    }

    }
    
