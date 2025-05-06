package Controller.Observer;

import java.util.ArrayList;
import java.util.List;

import Controller.Observer.Interface.ObservadorEmergencia;
import Controller.Singleton.ControladorRecursoSingleton;
import Controller.Strategy.EstrategiaPrioridad;
import Model.Emergencia;
import Model.ServicioEmergenciaBase;

public class GestorEmergencia {

    private final List<ObservadorEmergencia> observadores = new ArrayList<>();
    private final List<Emergencia> emergenciasRegistradas = new ArrayList<>();
    private EstrategiaPrioridad estrategia;
    private int totalEmergenciasAtendidas = 0;
    private double tiempoTotalRespuesta = 0;

    public void registrarTiempoRespuesta(double tiempo) {
        tiempoTotalRespuesta += tiempo;
        totalEmergenciasAtendidas++;
    }

    public double calcularTiempoPromedioRespuesta() {
        return totalEmergenciasAtendidas == 0 ? 0 : tiempoTotalRespuesta / totalEmergenciasAtendidas;
    }

    public int getTotalEmergenciasAtendidas() {
        return totalEmergenciasAtendidas;
    }

    public void setEstrategia(EstrategiaPrioridad estrategia) {
        this.estrategia = estrategia;
    }

    public List<Emergencia> priorizarEmergencias() {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha configurado una estrategia de prioridad.");
        }
        return estrategia.priorizar(emergenciasRegistradas);
    }

    public void agregarObservador(ObservadorEmergencia observador) {
        observadores.add(observador);
    }

    public void registrarEmergencia(Emergencia emergencia) {
        emergenciasRegistradas.add(emergencia);
        notificarObservadores(emergencia);
    }

    private void notificarObservadores(Emergencia emergencia) {
        for (ObservadorEmergencia observador : observadores) {
            observador.notificar(emergencia);
        }
    }

    public List<String> atenderEmergenciaIndividual(Emergencia emergencia, ControladorRecursoSingleton recursos) {
        List<String> resultados = new ArrayList<>();

        System.out.println("\nProcesando emergencia: " + emergencia);

        var servicioAsignado = recursos.getServicios(emergencia.getTipo()).stream()
                .filter(ServicioEmergenciaBase::estaDisponible)
                .findFirst();

        if (servicioAsignado.isPresent()) {
            var servicio = servicioAsignado.get();
            servicio.asignarEmergencia(emergencia);
            servicio.atenderEmergencia();

            // Simular tiempo de respuesta aleatorio
            double tiempoRespuesta = Math.random() * 10 + 5; // Entre 5 y 15 minutos
            registrarTiempoRespuesta(tiempoRespuesta);

            resultados.add("\n\t" + """
                    <------------ Atendiendo emergencia ------------>
                    """ + "\n" + "Asignando servicio: " + servicio.getNombreServicio() + "\n" +
                    servicio.getNombreServicio() + " asignado a emergencia en " + emergencia.getUbicacion() +
                    "\n" + servicio.getNombreServicio() + " atendiendo emergencia..." +
                    "\nResolviendo " + emergencia.getTipo().name().toLowerCase() + " en "
                    + emergencia.getUbicacion() +
                    "\nEmergencia atendida con éxito: " + emergencia.getTipo().name().toLowerCase() + " resuelto en "
                    + emergencia.getUbicacion() + " en " + tiempoRespuesta + " minutos." + "\n\n\t" +
                    "<--------------------------------------------->");
        } else {
            resultados.add("No hay servicios disponibles para atender la emergencia: " + emergencia);
        }

        return resultados;
    }

    public List<Emergencia> getEmergenciasRegistradas() {
        return emergenciasRegistradas;
    }

    public void limpiarEmergencias() {
        emergenciasRegistradas.clear();
    }
}