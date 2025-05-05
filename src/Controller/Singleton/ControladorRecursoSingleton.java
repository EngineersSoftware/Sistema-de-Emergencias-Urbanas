package Controller.Singleton;

import Model.ServicioEmergenciaBase;
import Model.Enums.TipoEmergencia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorRecursoSingleton {
    private static ControladorRecursoSingleton instancia;
    private final List<ServicioEmergenciaBase> servicios;

    private ControladorRecursoSingleton() {
        servicios = new ArrayList<>();
    }

    public static synchronized ControladorRecursoSingleton getInstancia() {
        if (instancia == null) {
            instancia = new ControladorRecursoSingleton();
        }
        return instancia;
    }

    public void registrarServicio(ServicioEmergenciaBase servicio) {
        if (!servicios.contains(servicio)) {
            servicios.add(servicio);
        }
    }

    public List<ServicioEmergenciaBase> getServicios(TipoEmergencia tipo) {
        return servicios.stream()
                .filter(servicio -> servicio.estaDisponible() && servicio.puedeAtender(tipo))
                .toList();
    }

    public List<ServicioEmergenciaBase> filtrarRecursosDisponibles() {
        return servicios.stream()
                .filter(ServicioEmergenciaBase::estaDisponible) // Filtrar solo los recursos disponibles
                .collect(Collectors.toList());
    }
}