//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> interesesTotales;
    private static List<Lugar> lugares;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String ruta = "dataExterna.txt";
    List<Lugar> lista = GestorArchivos.leerLugares(ruta);
    lugares = lista; // guardar globalmente para el menú
    interesesTotales = GestorArchivos.listaInteresesTotales(lista);

        //System.out.println("Intereses únicos encontrados:");
        //for (String inte : interesesTotales) {
        //    System.out.println(" - " + inte);
        //}

        //-------------------------------------
        System.out.println("");
        System.out.println("=====================================================================");
        System.out.println("Hola! Bienvenido al sistema planificador de rutas de viaje turístico.");
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
                diaInput = "lunes";
                break;
            case "martes":
                diaInput = "martes";
                break;
            case "miercoles":
                diaInput = "miercoles";
                break;
            case "jueves":
                diaInput = "jueves";
                break;
            case "viernes":
                diaInput = "viernes";
                break;
            case "sabado":
                diaInput = "sabado";
                break;
            case "domingo":
                diaInput = "domingo";
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
        System.out.println("Panel de funciones:");
        System.out.println("-------------------");
        System.out.println("Ingresa el numeral correspondiente a la funcion que deseas ejecutar (1, 2, 3):");
        System.out.println("A continuacion, veras el listado de funciones disponibles para procesar la base de datos: ");
        System.out.println("1. Evaluar afinidad de lugares de acuerdo a tus intereses.");
        System.out.println("2. Encontrar rutas generales y rutas de solo lugares abiertos entre dos puntos.");
        System.out.println("3. Terminar ejecucion.");
        System.out.println("");
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
                System.out.println("Porfavor, ingresa tus intereses turisticos separados por comas (ejemplo: museos, parques, noche):");
                scanner.nextLine();
                String interesesInput = scanner.nextLine();

                // Procesar intereses:
                //Los intereses del usuario se guaradan en interesInput





                menuFunciones();
                break;
            case 2:
                System.out.println("Has seleccionado la funcion 2: Encontrar rutas generales y rutas de solo lugares abiertos entre dos puntos.");
                System.out.println("Ingresa el nombre del lugar origen:");
                scanner.nextLine();
                String origenInput = scanner.nextLine().trim();
                System.out.println("Ingresa el nombre del lugar destino:");
                String destinoInput = scanner.nextLine().trim();

                Lugar origen = null, destino = null;
                if (lugares != null) {
                    for (Lugar l : lugares) {
                        if (l.getNombre().equalsIgnoreCase(origenInput)) origen = l;
                        if (l.getNombre().equalsIgnoreCase(destinoInput)) destino = l;
                    }
                }

                if (origen == null || destino == null) {
                    System.out.println("No se encontraron origen o destino con esos nombres.");
                    break;
                }

                PlanificadorRutas plan = new PlanificadorRutas(lugares);
                System.out.println("Deseas respetar horario de apertura? (s/n)");
                String resp = scanner.nextLine().trim().toLowerCase();
                if (resp.equals("s") || resp.equals("si") || resp.equals("y")) {
                    //Con restricción de horario -> mostrar solo rutas abiertas
                    String dia = checkDia();
                    List<List<Lugar>> rutas = plan.encontrarRutasConHorario(origen, destino, dia);
                    plan.mostrarRutas(rutas);
                    // Guardar las rutas en un archivo
                    GestorArchivos.guardarRutas(rutas, origen, destino, dia, "rutas_con_horario.txt");
                    //System.out.println("Las rutas se han guardado en 'rutas_con_horario.txt'");
                } else {
                    //Sin restricción de horario -> mostrar todas las rutas
                    List<List<Lugar>> rutas = plan.encontrarTodasLasRutas(origen, destino);
                    plan.mostrarRutas(rutas);
                    // Guardar las rutas en un archivo
                    GestorArchivos.guardarRutas(rutas, origen, destino, "cualquier día", "rutas_generales.txt");
                    //System.out.println("Las rutas se han guardado en 'rutas_generales.txt'");
                }
                menuFunciones();
                break;
            case 3:
                System.out.println("Terminando ejecucion. ¡Gracias por usar el planificador de rutas turísticas!");
                break;
            default:
                System.out.println("Opcion no valida. Porfavor, intentalo de nuevo.");
                menuFunciones();
                break;
        }
    }
}