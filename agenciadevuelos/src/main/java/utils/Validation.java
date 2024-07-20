package utils;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {
    
    public static String leerdato(String msg, Scanner sc) {
        while (true) {
            System.out.print(msg);
            try {
                String dato = sc.nextLine();
                if (!dato.isEmpty()) {
                    return dato;
                } else {
                    System.out.println("El campo no puede ser vacío.");
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada: " + e.getMessage());
                sc.next(); // Limpia el buffer del scanner en caso de error
            }
        }
    }

    public static int leerNumero(String msg, Scanner sc) {
        while (true) {
            System.out.print(msg);
            try {
                String dato = sc.nextLine();
                if (IsInteger(dato)) {
                    return Integer.parseInt(dato);
                } else {
                    System.out.println("El campo debe ser un número entero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato de número no válido. Intente de nuevo.");
            }
        }
    }

    public static boolean IsInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static Date LeerFecha(String msg, Scanner sc) {
        while (true) {
            String fechaStr = leerdato(msg, sc);
            try {
                Date fecha = Date.valueOf(fechaStr);
                return fecha;
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de fecha no válido. Intente de nuevo.");
            }
        }
    }

    public static Time leerHora(String msg, Scanner sc) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        timeFormatter.setLenient(false); // Para evitar fechas y horas incorrectas
        while (true) {
            String horaStr = leerdato(msg, sc);
            try {
                java.util.Date parsedDate = timeFormatter.parse(horaStr);
                return new Time(parsedDate.getTime());
            } catch (ParseException e) {
                System.out.println("Formato de hora no válido. Intente de nuevo.");
            }
        }
    }
}
