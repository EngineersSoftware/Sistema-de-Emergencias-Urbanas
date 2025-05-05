package Controller.Observer.Observadores;

import Controller.Observer.Interface.ObservadorEmergencia;
import Model.Emergencia;
import Model.Enums.TipoEmergencia;

public class ObservadorAmbulancia implements ObservadorEmergencia {

    protected String ultimoMensaje;

    @Override
    public void notificar(Emergencia emergencia) {
        if (emergencia.getTipo() == TipoEmergencia.ACCIDENTE_VEHICULAR) {
            this.ultimoMensaje = "Ambulancia notificada sobre accidente en " + emergencia.getUbicacion();
        } else {
            this.ultimoMensaje = "Ambulancia no aplica para esta emergencia.";
        }
    }

    @Override
    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

}
