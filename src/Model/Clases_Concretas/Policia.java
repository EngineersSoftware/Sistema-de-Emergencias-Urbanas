package Model.Clases_Concretas;

import Model.ServicioEmergenciaBase;
import Model.Enums.TipoEmergencia;

public class Policia extends ServicioEmergenciaBase {

    @Override
    public String getNombreServicio() {
        return "Policía";
    }

    @Override
    public boolean puedeAtender(TipoEmergencia tipoEmergencia) {
        return tipoEmergencia == TipoEmergencia.ROBO;
    }

}
