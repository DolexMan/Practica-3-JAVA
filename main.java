//import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String ruta = "dataExterna.txt";
        List<Lugar> lista = GestorArchivos.leerLugares(ruta);

        for (Lugar c : lista) {
            c.mostrarInfo();
        }
    }
}