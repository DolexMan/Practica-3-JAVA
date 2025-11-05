//import java.util.ArrayList;
import java.util.List;
public class Lugar extends EntidadTuristica{

    //ArrayList<String> intereses;
    //ArrayList<String> diasAbierto;
    List<String> intereses;
    List<String> diasA;
    List<String> conexiones;

    public Lugar(String nombre, List<String> intereses, List<String> diasA, List<String> conexiones) {
        this.nombre = nombre;
        this.intereses = intereses;
        this.diasA = diasA;
        this.conexiones = conexiones;
    }

    public void mostrarInfo() {
        System.out.println("Ciudad: " + nombre);
        System.out.println("Puntos de interés: " + intereses);
        System.out.println("Días abiertos: " + diasA);
        System.out.println("Conexiones directas: " + conexiones);
        System.out.println("-----------------------------");
    }

    // Setters and Getters
    public String getNombre() {
        return nombre;
    }

    public List<String> getconexiones() {
        return conexiones;
    }

    public List<String> getIntereses() {
        return intereses;
    }

    public List<String> getDiasAbierto() {
        return diasA;
    }
    
}
