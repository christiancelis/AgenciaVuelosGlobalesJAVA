package utils;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Validation {
    
 public static String leerdato(String msg, Scanner es) {
        String dato;
        while (true) {
            dato = "";
            try {
                System.out.print(msg);
                dato = es.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!dato.isEmpty()) {
                return dato;
            } else {
                System.out.println("El campo no puede ser vacío\n");
                continue;
            }
        }
    }

    public static int leerNumero(String msg, Scanner es) {
        String dato;
        while (true) {
            dato = "";
            try {
                System.out.print(msg);
                dato = es.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }

            if (!IsInteger(dato)) {
                System.out.println("El campo no puede ser texto");
                continue;
            }
            if (!dato.isEmpty()) {
                return Integer.parseInt(dato);
            } else {
                System.out.println("El campo no puede ser vacío o ser menor o igual a cero\n");
                continue;
            }
        }
    }

    public static boolean IsInteger(String text) {
        try {
            int v = Integer.parseInt(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    public static Date LeerFecha(String msg, Scanner sc){
        Date fecha = null;
        while (true) {
            String fechaStr = Validation.leerdato(msg, sc);
            String[] fechaC = fechaStr.split("-");
            if(fechaC.length!=3){
                System.out.println("Formato no valido");
                continue;
            }
            if(fechaC[0].length()==4 && fechaC[1].length()==2 && fechaC[2].length()==2 && IsInteger(String.valueOf(fechaC[0]))==true && IsInteger(String.valueOf(fechaC[1]))==true && IsInteger(String.valueOf(fechaC[2]))==true ){
                try {
                    fecha =java.sql.Date.valueOf(fechaStr);
                    return fecha;
                } catch (Exception e) {
                    System.out.println("Formato de fecha no válido. Intente de nuevo.");
                }
            }else{
                System.out.println("Formato no valido");
                continue;
            }
            fecha = null; 
        }
    }

    public static Time leerHora(String msg, Scanner sc){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        Time hora = null;
        while (true) {
            String horaStr = Validation.leerdato(msg, sc);
            try {
                hora = new Time(timeFormatter.parse(horaStr).getTime());
                return hora;
            } catch (ParseException e) {
                System.out.println("Formato de hora no válido. Intente de nuevo.");
            }
            hora = null;
        }
    }

}
