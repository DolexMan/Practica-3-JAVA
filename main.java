//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        String ruta = "dataExterna.txt";
        List<Lugar> lista = GestorArchivos.leerLugares(ruta);

        //-------------------------------------

        System.out.println("------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Hola! Bienvenido al sistema planificador de rutas de viaje turístico.");
        System.out.println("");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Porfavor ingresa tu nombre de usuario:");
        System.out.println("");
        
        String turista = scanner.nextLine();
        Turista usuario = new Turista(turista);

        System.out.println("A continucion, veras la lista de lugares disponibles con sus atractivos turisticos:");
        for (Lugar c : lista) {
            c.mostrarInfo();
        }

        System.out.println("");
        System.out.println("Porfavor, ingresa el día de la semana en que deseas hacer tu viaje (Lunes, Martes, ...): ");
        String diaSemana = scanner.nextLine().toLowerCase();
        System.out.println("");
        System.out.println("Estos son los lugares abiertos el dia " + diaSemana + ":");
        //Lugar[] lugaresAbiertos = PlanificadorRutas.listaAbiertos(lista.toArray(new Lugar[0]), diaSemana);
        





    }
}