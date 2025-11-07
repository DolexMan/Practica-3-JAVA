import java.util.List;

public class Conexion {

    // Representa una conexión desde un lugar hacia un destino con tiempo de viaje
    private String origen;      // opcional: nombre del origen
    private String destino;
    private int tiempoViaje;    // tiempo de viaje en horas

    // Constructor usado por GestorArchivos: new Conexion(destino, tiempoViaje)
    public Conexion(String destino, int tiempoViaje) {
        this.destino = destino;
        this.tiempoViaje = tiempoViaje;
    }

    // Getters
    public String getDestino() {
        return destino;
    }

    public int getTiempoViaje() {
        return tiempoViaje;
    }

    // Métodos antiguos por compatibilidad
    public String getLugarOrigen() { return origen; }
    public String getLugarDestino() { return destino; }

    @Override
    public String toString() {
        return destino;
    }
    
    public static int calcularTiempoTotal(List<Lugar> ruta) {
        if (ruta == null || ruta.size() < 2) return 0;
        
        int tiempoTotal = 0;
        
        // Empieza desde el índice 1 para excluir la ciudad de salida
        for (int i = 1; i < ruta.size(); i++) {
            Lugar actual = ruta.get(i);
            Lugar anterior = ruta.get(i-1);
            
            // Buscar la conexión entre el lugar anterior y el actual
            List<Conexion> conexiones = anterior.getConexiones();
            boolean encontrado = false;
            
            for (Conexion c : conexiones) {
                if (c.getDestino().equalsIgnoreCase(actual.getNombre())) {
                    tiempoTotal += c.getTiempoViaje();
                    encontrado = true;
                    break;
                }
            }
            
            if (!encontrado) return -1; // Error: no hay conexión directa
        }
        
        return tiempoTotal;
    }
}
