package Model.Clases_Concretas;

import Model.ServicioEmergenciaBase;
import Model.Enums.TipoEmergencia;

public class Bombero extends ServicioEmergenciaBase {

    @Override
    public String getNombreServicio() {
        return "Bomberos";
    }

    @Override
    public boolean puedeAtender(TipoEmergencia tipoEmergencia) {
        return tipoEmergencia == TipoEmergencia.INCENDIO;
    }


}
