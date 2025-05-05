package Model.Interface;

import Model.Emergencia;
import Model.Enums.TipoEmergencia;

public interface IServicioEmergencia {

    boolean estaDisponible();

    void asignarEmergencia(Emergencia emergencia);

    void atenderEmergencia();

    String getNombreServicio();

    boolean puedeAtender(TipoEmergencia tipoEmergencia); 

}
