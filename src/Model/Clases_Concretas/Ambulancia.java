package Model.Clases_Concretas;


import Model.ServicioEmergenciaBase;
import Model.Enums.TipoEmergencia;

public class Ambulancia extends ServicioEmergenciaBase {

    @Override
    public String getNombreServicio() {
        return "Ambulancia";
    }

    @Override
    public boolean puedeAtender(TipoEmergencia tipoEmergencia) {
        return tipoEmergencia == TipoEmergencia.ACCIDENTE_VEHICULAR;
    }

    
}
