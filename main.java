//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> interesesTotales;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String ruta = "dataExterna.txt";
        List<Lugar> lista = GestorArchivos.leerLugares(ruta);
        interesesTotales = GestorArchivos.listaInteresesTotales(lista);
        List<String> interesesTotales = GestorArchivos.listaInteresesTotales(lista);

        //System.out.println("Intereses únicos encontrados:");
        //for (String inte : interesesTotales) {
        //    System.out.println(" - " + inte);
        //}

        //-------------------------------------
        System.out.println("");
        System.out.println("=====================================================================");
        System.out.println("");
        System.out.println("Hola! Bienvenido al sistema planificador de rutas de viaje turístico.");
        System.out.println("");
        System.out.println("=====================================================================");
        System.out.println("");
        System.out.println("Porfavor ingresa tu nombre de usuario:");
        String turista = scanner.nextLine();
        Turista usuario = new Turista(turista);
        
        System.out.println("");
        System.out.println("A continucion, veras la lista de lugares disponibles con sus descripciones:");
        System.out.println("");
        for (Lugar c : lista) {
            c.mostrarInfo();
        }

        //System.out.println("");
        //System.out.println("Porfavor, ingresa el dia de la semana en que deseas hacer tu viaje (Lunes, Martes, ...): ");
        //String diaInput = scanner.nextLine().toLowerCase();
        //checkDia();
        
        
        System.out.println("");
        Lugar.filtrarAbiertos(lista, checkDia());
        System.out.println("");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("A continuacion, veras el listado de funciones disponibles para procesar los datos: ");
        System.out.println("1. Evaluar afinidad de lugares de acuerdo a tus intereses.");
        System.out.println("2. Encontrar rutas generales y rutas de solo lugares abiertos entre dos puntos.");
        System.out.println("3. Terminar ejecucion.");
        System.out.println("");
        menuFunciones();

        scanner.close();
    }

    public static String checkDia() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Porfavor ingresa el dia de la semana en que deseas hacer tu viaje (Lunes, Martes, ...): ");
        String diaInput = scanner.nextLine().toLowerCase();
        switch (diaInput) {
            case "lunes":
            case "martes":
            case "miercoles":
            case "jueves":
            case "viernes":
            case "sabado":
            case "domingo":
                break;
            default:
                System.out.println("");
                System.out.println("Dia ingresado no valido. Porfavor, intentalo de nuevo.");
                checkDia();
                break;
        }
        return diaInput;
    }
        
    
    public static void menuFunciones() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Ingresa el numeral correspondiente a la funcion que deseas ejecutar (1, 2, 3):");
        int opcion = scanner.nextInt();
        System.out.println("");
        switch (opcion) {
            case 1:
                System.out.println("Has seleccionado la funcion 1: Evaluar afinidad de lugares de acuerdo a tus intereses.");
                System.out.println("");
                System.out.println("Estas son las etiquetas de interes disponibles:");
                for (String inte : interesesTotales) {
                    System.out.println(" - " + inte);
                }
    
                System.out.println("");
                System.out.println("Porfavor, ingresa tus intereses turisticos separados por comas (ejemplo: museos, parques, playas):");
                scanner.nextLine();
                String interesesInput = scanner.nextLine();
                // Procesar intereses
                break;
            case 2:
                System.out.println("Has seleccionado la funcion 2: Encontrar rutas generales y rutas de solo lugares abiertos entre dos puntos.");
                // Implementacion de la funcion 2
                break;
            case 3:
                System.out.println("Terminando ejecucion. ¡Gracias por usar el planificador de rutas turísticas!");
                break;
            default:
                System.out.println("Opcion no valida. Porfavor, intentalo de nuevo.");
                menuFunciones();
                break;
        }
        scanner.close();
    }
}