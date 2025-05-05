package Model.Emergencias;

import Model.Emergencia;
import Model.Enums.NivelDeGravedad;
import Model.Enums.TipoEmergencia;

public class Incendio extends Emergencia {

    public Incendio(String ubicacion, NivelDeGravedad gravedad) {
        super(ubicacion, gravedad, TipoEmergencia.INCENDIO);
    }
    
}
