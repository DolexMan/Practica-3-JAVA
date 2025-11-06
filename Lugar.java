//import java.util.ArrayList;
import java.util.List;
public class Lugar extends EntidadTuristica{

    private List<String> intereses;
    private List<String> diasA;
    private List<String> conexionesD;
    private List<String> tiempo_viaje;

    public Lugar(String nombre, List<String> intereses, List<String> diasA, List<String> conexionesD, List<String> tiempo_viaje) {
        this.nombre = nombre;
        this.intereses = intereses;
        this.diasA = diasA;
        this.conexionesD = conexionesD;
        this.tiempo_viaje = tiempo_viaje;
    }

    public void mostrarInfo() {
        System.out.println("Ciudad: " + nombre);
        System.out.println("Puntos de interés: " + intereses);
        System.out.println("Días abiertos: " + diasA);
        System.out.println("Conexiones directas:" + conexionesD);
        System.out.println("Tiempos de viaje (horas): " + tiempo_viaje);
        System.out.println("-----------------------------");
    }

    // Setters and Getters
    public String getNombre() {
        return nombre;
    }

    public List<String> getConexiones() {
        return conexionesD;
    }

    public List<String> getIntereses() {
        return intereses;
    }

    public List<String> getDiasAbierto() {
        return diasA;
    }
    
}
