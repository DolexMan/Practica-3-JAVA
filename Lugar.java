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
        System.out.println("-----------------------------");
    }
    public static void filtrarAbiertos(List<Lugar> lugares, String diaInput) {
        if (lugares == null || diaInput == null || diaInput.trim().isEmpty()) {
            System.out.println("No hay datos o día inválido.");
            return;
        }
        String diaBuscado = diaInput.trim();
        boolean encontrado = false;
        System.out.println("Ciudades abiertas el día " + diaInput + ":");
        for (Lugar lugar : lugares) {
            List<String> dias = lugar.getDiasAbierto();
            if (dias == null) continue;
            for (String d : dias) {
                if (d != null && d.toLowerCase().trim().equals(diaBuscado)) {
                    System.out.println("- " + lugar.getNombre());
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron ciudades abiertas para este dia.");
        }
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
