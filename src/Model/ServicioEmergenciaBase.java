package Model;

import Model.Interface.IServicioEmergencia;

public abstract class ServicioEmergenciaBase implements IServicioEmergencia {

    protected boolean disponible = true;
    protected String ubicacionActual = "Base";
    protected Emergencia emergenciaAsignada;

    public ServicioEmergenciaBase() {
        this.emergenciaAsignada = null;
    }

    @Override
    public void asignarEmergencia(Emergencia emergencia) {
        this.emergenciaAsignada = emergencia;
        this.disponible = false;
    }

    @Override
    public void atenderEmergencia() {
        if (emergenciaAsignada != null) {
            emergenciaAsignada = null;
            disponible = true;
        } else {
            System.out.println(getNombreServicio() + " no tiene emergencias asignadas.");
        }
    }

    @Override
    public boolean estaDisponible() {
        return disponible;
    }

    @Override
    public String getNombreServicio() {
        return this.getClass().getSimpleName();
    }
}