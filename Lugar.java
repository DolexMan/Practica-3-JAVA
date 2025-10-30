import java.util.ArrayList;
public class Lugar extends EntidadTuristica{

    ArrayList<String> intereses;
    ArrayList<String> diasAbierto;

    public Lugar(String nombre, String descripcion, ArrayList<String> intereses, ArrayList<String> diasAbierto, String ubicacion, double precioEntrada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.intereses = intereses;
        this.diasAbierto = diasAbierto;
    } 

    // Setters and Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<String> getIntereses() {
        return intereses;
    }

    public ArrayList<String> getDiasAbierto() {
        return diasAbierto;
    }
    
}
