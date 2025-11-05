import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GestorArchivos {
    // Método que lee y convierte la plantilla en un mapa clave-valor
    public static List<Lugar> leerLugares(String ruta) {
        List<Lugar> Lugares = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(ruta));
            Map<String, String> datosTemp = new HashMap<>();

            for (String linea : lineas) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) continue;

                if (linea.equalsIgnoreCase("[Lugar]")) {
                    // Si ya había datos cargados, crear la Lugar anterior
                    if (!datosTemp.isEmpty()) {
                        Lugares.add(crearLugarDesdeMapa(datosTemp));
                        datosTemp.clear();
                    }
                } else {
                    String[] partes = linea.split(":", 2);
                    if (partes.length == 2) {
                        datosTemp.put(partes[0].trim(), partes[1].trim());
                    }
                }
            }

            // Última Lugar (si el archivo no termina con un nuevo [Lugar])
            if (!datosTemp.isEmpty()) {
                Lugares.add(crearLugarDesdeMapa(datosTemp));
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return Lugares;
    }

    private static Lugar crearLugarDesdeMapa(Map<String, String> datos) {
        String nombre = datos.get("nombre");

        List<String> intereses = Arrays.asList(datos.getOrDefault("intereses", "").split(","));
        List<String> diasA = Arrays.asList(datos.getOrDefault("dias_abierto", "").split(","));
        List<String> conexiones = Arrays.asList(datos.getOrDefault("conexiones", "").split(","));

        // Quitar espacios extra
        intereses.replaceAll(String::trim);
        diasA.replaceAll(String::trim);
        conexiones.replaceAll(String::trim);

        return new Lugar(nombre, intereses, diasA, conexiones);
    }
}



