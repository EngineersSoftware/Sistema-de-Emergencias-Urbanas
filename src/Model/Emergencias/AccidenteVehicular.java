package Model.Emergencias;

import Model.Emergencia;
import Model.Enums.NivelDeGravedad;
import Model.Enums.TipoEmergencia;

public class AccidenteVehicular  extends Emergencia{
    
    public AccidenteVehicular(String ubicacion, NivelDeGravedad gravedad) {
        super(ubicacion, gravedad, TipoEmergencia.ACCIDENTE_VEHICULAR);
    }

    
}