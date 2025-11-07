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
        
        // Leer conexiones y tiempo de viaje
        String conexionesRaw = datos.getOrDefault("conexiones", "");
        int tiempoViaje = Integer.parseInt(datos.getOrDefault("tiempo_viaje", "0"));
        List<Conexion> conexiones = new ArrayList<>();
        
        if (!conexionesRaw.trim().isEmpty()) {
            String[] parts = conexionesRaw.split(",");
            for (String p : parts) {
                String destino = p.trim();
                if (!destino.isEmpty()) {
                    conexiones.add(new Conexion(destino, tiempoViaje));
                }
            }
        }

        // Quitar espacios extra de listas simples
        intereses.replaceAll(String::trim);
        diasA.replaceAll(String::trim);

        return new Lugar(nombre, intereses, diasA, conexiones);
    }

    public static List<String> listaInteresesTotales(List<Lugar> lugares) {
        Set<String> set = new LinkedHashSet<>();
        if (lugares == null) return new ArrayList<>(set);
        for (Lugar l : lugares) {
            List<String> intereses = l.getIntereses();
            if (intereses == null) continue;
            for (String it : intereses) {
                if (it == null) continue;
                String norm = it.trim().toLowerCase();
                if (!norm.isEmpty()) set.add(norm);
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * Guarda las rutas encontradas en un archivo de texto.
     * @param rutas Lista de rutas a guardar
     * @param origen Lugar de origen
     * @param destino Lugar de destino
     * @param dia Día de la semana (null si no se aplicó restricción de horario)
     * @param rutaArchivo Ruta donde se guardará el archivo
     */
    public static void guardarRutas(List<List<Lugar>> rutas, Lugar origen, Lugar destino, 
                                  String dia, String rutaArchivo) {
        try {
            // Crear el archivo si no existe
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            // Escribir el contenido
            try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) {
                writer.println("=============");
                writer.println("Ruta guardada");
                writer.println("=============");
                writer.println("Desde: " + origen.getNombre());
                writer.println("Hasta: " + destino.getNombre());
                if (dia != null) {
                    writer.println("Dia: " + dia);
                }
                writer.println("--------------------------------------------------");
                
                if (rutas == null || rutas.isEmpty()) {
                    writer.println("No se encontraron rutas disponibles.");
                } else {
                    int idx = 1;
                    for (List<Lugar> ruta : rutas) {
                        writer.print("Ruta " + idx++ + ": ");
                        
                        // Escribir la secuencia de lugares
                        for (int i = 0; i < ruta.size(); i++) {
                            writer.print(ruta.get(i).getNombre());
                            if (i < ruta.size() - 1) {
                                writer.print(" -> ");
                            }
                        }
                        
                        // Calcular y mostrar el tiempo total
                        int tiempoTotal = Conexion.calcularTiempoTotal(ruta);
                        if (tiempoTotal >= 0) {
                            writer.println("  (Tiempo total: " + tiempoTotal + " horas)");
                        } else {
                            writer.println("  (Tiempo no disponible)");
                        }
                    }
                }
                writer.println("--------------------------------------------------");
                writer.println(); // Línea en blanco para separar múltiples búsquedas
                writer.flush();
            }
            
            System.out.println("Las rutas han sido guardadas exitosamente en: " + rutaArchivo);
            
        } catch (IOException e) {
            System.out.println("Error al guardar las rutas: " + e.getMessage());
        }
    }
}