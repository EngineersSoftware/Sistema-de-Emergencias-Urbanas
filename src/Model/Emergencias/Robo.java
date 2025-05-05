package Model.Emergencias;

import Model.Emergencia;
import Model.Enums.NivelDeGravedad;
import Model.Enums.TipoEmergencia;

public class Robo extends Emergencia{
    
    public Robo(String ubicacion, NivelDeGravedad gravedad) {
        super(ubicacion, gravedad, TipoEmergencia.ROBO);
    }
    
}
