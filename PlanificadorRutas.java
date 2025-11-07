import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanificadorRutas {

    private final List<Lugar> lugares;

    public PlanificadorRutas(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    // 1) Todas las rutas posibles (sin restricción de horario)
    public List<List<Lugar>> encontrarTodasLasRutas(Lugar origen, Lugar destino) {
        List<List<Lugar>> resultado = new ArrayList<>();
        if (origen == null || destino == null) return resultado;

        Set<String> visitados = new HashSet<>();
        List<Lugar> ruta = new ArrayList<>();
        ruta.add(origen);
        visitados.add(origen.getNombre().toLowerCase());

        dfsBuscar(origen, destino, visitados, ruta, resultado, null);
        return resultado;
    }

    // 2) Rutas respetando horario (todos los lugares de la ruta deben estar abiertos en 'dia')
    public List<List<Lugar>> encontrarRutasConHorario(Lugar origen, Lugar destino, String dia) {
        List<List<Lugar>> resultado = new ArrayList<>();
        if (origen == null || destino == null || dia == null) return resultado;

        // validar origen/destino
        if (!estaAbierta(origen, dia) || !estaAbierta(destino, dia)) return resultado;

        Set<String> visitados = new HashSet<>();
        List<Lugar> ruta = new ArrayList<>();
        ruta.add(origen);
        visitados.add(origen.getNombre().toLowerCase());

        dfsBuscar(origen, destino, visitados, ruta, resultado, dia);
        return resultado;
    }

    private void dfsBuscar(Lugar actual, Lugar destino, Set<String> visitados, List<Lugar> ruta,
                           List<List<Lugar>> resultado, String dia) {
        if (actual.getNombre().equalsIgnoreCase(destino.getNombre())) {
            resultado.add(new ArrayList<>(ruta));
            return;
        }

        List<Conexion> conexiones = actual.getConexiones();
        if (conexiones == null) return;

        for (Conexion c : conexiones) {
            Lugar siguiente = encontrarLugarPorNombre(c.getDestino());
            if (siguiente == null) continue;
            String key = siguiente.getNombre().toLowerCase();
            if (visitados.contains(key)) continue; // evita ciclos
            if (dia != null && !estaAbierta(siguiente, dia)) continue; // respeta horario

            visitados.add(key);
            ruta.add(siguiente);
            dfsBuscar(siguiente, destino, visitados, ruta, resultado, dia);
            ruta.remove(ruta.size() - 1);
            visitados.remove(key);
        }
    }

    private Lugar encontrarLugarPorNombre(String nombre) {
        if (nombre == null) return null;
        for (Lugar l : lugares) {
            if (l.getNombre().equalsIgnoreCase(nombre)) return l;
        }
        return null;
    }

    // Mostrar rutas con tiempo total sumado
    public void mostrarRutas(List<List<Lugar>> rutas) {
        if (rutas == null || rutas.isEmpty()) {
            System.out.println("No se encontraron rutas.");
            return;
        }

        int idx = 1;
        for (List<Lugar> ruta : rutas) {
            System.out.println("Ruta " + (idx++) + ":");
            for (int i = 0; i < ruta.size(); i++) {
                System.out.print(ruta.get(i).getNombre());
                if (i < ruta.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            
            int tiempoTotal = Conexion.calcularTiempoTotal(ruta);
            if (tiempoTotal >= 0) {
                System.out.println("  (Tiempo total: " + tiempoTotal + " horas)");
            } else {
                System.out.println("  (Tiempo no disponible)");
            }
            System.out.println();
        }
    }

    // Comprueba si un lugar está abierto en un día específico
    public static boolean estaAbierta(Lugar lugar, String dia) {
        if (lugar == null || dia == null) return false;
        List<String> dias = lugar.getDiasAbierto();
        if (dias == null) return false;
        for (String d : dias) {
            if (d == null) continue;
            if (d.trim().equalsIgnoreCase(dia.trim())) return true;
        }
        return false;
    }
}
