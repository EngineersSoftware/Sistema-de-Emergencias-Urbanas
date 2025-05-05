package Controller.Factory;

import Model.Emergencia;
import Model.Emergencias.AccidenteVehicular;
import Model.Emergencias.Incendio;
import Model.Emergencias.Robo;
import Model.Enums.NivelDeGravedad;
import Model.Enums.TipoEmergencia;

public class EmergenciaFactory {

    public static Emergencia crearEmergencia(TipoEmergencia tipo, String ubicacion, NivelDeGravedad gravedad) {
        return switch (tipo) {
            case INCENDIO -> new Incendio(ubicacion, gravedad);
            case ROBO -> new Robo(ubicacion, gravedad);
            case ACCIDENTE_VEHICULAR -> new AccidenteVehicular(ubicacion, gravedad);
        };
    }

}
