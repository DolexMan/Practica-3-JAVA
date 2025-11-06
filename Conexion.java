public class Conexion {

    protected String lugarOrigen;
    protected String lugarDestino;
    protected int tiempo_viaje;

    public Conexion(String lugarOrigen, String lugarDestino, int tiempo_viaje) {
        this.lugarOrigen = lugarOrigen;
        this.lugarDestino = lugarDestino;
        this.tiempo_viaje = tiempo_viaje;
    }

    //Setters and Getters
    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public int getTiempoViaje() {
        return tiempo_viaje;
    }
    
}
