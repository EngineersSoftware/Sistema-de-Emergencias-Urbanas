package Model;

import java.util.HashMap;
import java.util.Map;

public class MapaUrbano {

    private final Map<String, Coordenadas> ubicaciones = new HashMap<>();
    private final Coordenadas baseOperaciones;

    public MapaUrbano(Coordenadas baseOperaciones) {
        this.baseOperaciones = baseOperaciones;
    }

    public void registrarUbicacion(String nombre, Coordenadas coordenadas) {
        ubicaciones.put(nombre, coordenadas);
    }

    public double calcularDistancia(String ubicacion) {
        Coordenadas destino = ubicaciones.get(ubicacion);
        return baseOperaciones.distanciaA(destino);
    }

    public static class Coordenadas {
        private final double latitud;
        private final double longitud;

        public Coordenadas(double latitud, double longitud) {
            this.latitud = latitud;
            this.longitud = longitud;
        }

        public double distanciaA(Coordenadas otra) {
            return Math.sqrt(Math.pow(latitud - otra.latitud, 2) + Math.pow(longitud - otra.longitud, 2));
        }
    }

}
