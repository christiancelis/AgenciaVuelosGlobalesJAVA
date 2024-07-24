package tarifa.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;

import tarifa.application.ListTarifa;
import tarifa.domain.ServiceTarifa;
import tarifa.domain.Tarifa;

public class TarifaController {
    private ListTarifa listTarifa;
    Scanner scanner = new Scanner(System.in);
    public TarifaController(ServiceTarifa serviceTarifa) {
        this.listTarifa = new ListTarifa(serviceTarifa);
    }

    public Tarifa listarTarifa() {
        ArrayList<Tarifa> lista = listTarifa.execute();
        
        // Mostrar las tarifas disponibles
        System.out.println("Seleccione una tarifa de la lista a continuación:");
        System.out.println("--------------------------------------------------");
        for (Tarifa tarifa : lista) {
            System.out.println("ID: " + tarifa.getId() + 
                               " | Valor: $" + String.format("%.2f", tarifa.getValor()) + 
                               " | Descripción: " + (tarifa.getDescripcion() != null ? tarifa.getDescripcion() : "No disponible"));
        }
        System.out.println("--------------------------------------------------");
    
        // Solicitar al usuario que elija una tarifa
        int choice = utils.Validation.leerNumero("Ingrese el ID de la tarifa que desea utilizar: ", scanner);
        
        // Buscar la tarifa seleccionada por el usuario
        for (Tarifa tarifa : lista) {
            if (tarifa.getId() == choice) {
                return tarifa; 
               
            }
            
        }
    
        // Mensaje en caso de ID no válido
        System.out.println("ID de tarifa no válido. Por favor, intente de nuevo.");
        return null;
    }
    
}
