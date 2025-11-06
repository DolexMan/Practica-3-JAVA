public class PlanificadorRutas {
    
    public void planificarRuta(Turista turista, Conexion lugarOrigen, Conexion lugarDestino, Conexion tiempo_viaje) {
        // Lógica para planificar la ruta desde el lugar de origen al destino
        //...
        // For instance methods
        String Origen = lugarOrigen.getLugarOrigen();
        String Destino = lugarDestino.getLugarDestino();
        int Tiempo = tiempo_viaje.getTiempoViaje();


    }
    public static boolean conectactadas(Lugar lugar1, Lugar lugar2){
        if (conexionExistente(lugar1, lugar2)){
            return true;
        } else {
            return false;
        }
    }
    public static boolean estaAbierta(Lugar lugar, String dia){
        if (lugar.getDiasAbierto().contains(dia.toLowerCase())){
            return true;
        } else {
            return false;
        }
    }
    public static Lugar[] listaConectadas(Conexion[] conexiones, Lugar ciudadOrigen){
        int contador = 0;
        for (Conexion conexion : conexiones) {
            if (conexion.getLugarOrigen().equals(ciudadOrigen.getNombreLugar())) {
                contador++;
            }
        }
        Lugar[] lugaresConectados = new Lugar[contador];
        int indice = 0;
        for (Conexion conexion : conexiones) {
            if (conexion.getLugarOrigen().equals(ciudadOrigen.getNombreLugar())) {
                lugaresConectados[indice] = new Lugar(conexion.getLugarDestino());
                indice++;
            }
        }
        return lugaresConectados;
    }
    public static Lugar[] listaAbiertos(Lugar[] lugares, String dia){
        int contador = 0;
        for (Lugar lugar : lugares) {
            if (lugar.getDiasAbierto().contains(dia.toLowerCase())) {
                contador++;
            }
        }
        Lugar[] lugaresAbiertos = new Lugar[contador];
        int indice = 0;
        for (Lugar lugar : lugares) {
            if (lugar.getDiasAbierto().contains(dia.toLowerCase())) {
                lugaresAbiertos[indice] = lugar;
                indice++;
            }
        }
        return lugaresAbiertos;
    }
    public static Lugar[] ruta(Lugar inicio, Lugar fin, Lugar[] lugaresConectados){
        List<Lugar> ruta = new ArrayList<>();
        ruta.add(inicio);

        Lugar actual = inicio;
        while (!actual.getNombreLugar().equals(fin.getNombreLugar())) {
            boolean encontrado = false;
            for (Lugar lugar : lugaresConectados) {
                if (conectactadas(actual, lugar)) {
                    ruta.add(lugar);
                    actual = lugar;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontró una ruta desde " + inicio.getNombreLugar() + " hasta " + fin.getNombreLugar());
                return new Lugar[0];
            }
        }

        return ruta.toArray(new Lugar[0]);
    })

}
