package Model;

import Model.Enums.NivelDeGravedad;
import Model.Enums.TipoEmergencia;

public abstract class Emergencia {
    
    private final String ubicacion;
    private final NivelDeGravedad gravedad;
    private final TipoEmergencia tipo;

    public Emergencia(String ubicacion, NivelDeGravedad gravedad, TipoEmergencia tipo) {
        this.ubicacion = ubicacion;
        this.gravedad = gravedad;
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public NivelDeGravedad getGravedad() {
        return gravedad;
    }

    public TipoEmergencia getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return   "Ubicacion '" + ubicacion + '\'' +
                " | Gravedad " + gravedad +
                " | Tipo " + tipo 
                ;
    }
}