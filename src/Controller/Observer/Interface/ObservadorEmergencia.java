package Controller.Observer.Interface;

import Model.Emergencia;

public interface ObservadorEmergencia {

    void notificar(Emergencia emergencia);
    String getUltimoMensaje();

}
