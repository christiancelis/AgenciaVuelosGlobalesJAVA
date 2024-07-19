package com.agenciadevuelosglobales;
import java.util.Scanner;
import com.agenciadevuelosglobales.Menu.MenuManager;
import utils.DistanceCalculator;
import utils.Geocoding;

public class Main {
    private static final double COST_PER_KM = 0.1; // Costo por kilómetro

    public static void main(String[] args) {
        // MenuManager menuManager = new MenuManager();
        // menuManager.startMainMenu();
        
       try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese la ciudad de origen: ");
            String origin = scanner.nextLine().trim();

            System.out.print("Ingrese la ciudad de destino: ");
            String destination = scanner.nextLine().trim();

            if (origin.isEmpty() || destination.isEmpty()) {
                System.out.println("Las ciudades no pueden estar vacías.");
                return;
            }

            // Obtener detalles de la ubicación
            Geocoding.LocationDetails originDetails = Geocoding.getLocationDetails(origin);
            Geocoding.LocationDetails destinationDetails = Geocoding.getLocationDetails(destination);

       

            System.out.println("Ciudad de origen: " + originDetails.getCity() + ", País: " + originDetails.getCountry());
            System.out.println("Ciudad de destino: " + destinationDetails.getCity() + ", País: " + destinationDetails.getCountry());
   

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
    
