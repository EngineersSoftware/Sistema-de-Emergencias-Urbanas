package Controller.Observer;

import Controller.Observer.Interface.ObservadorEmergencia;
import Controller.Singleton.ControladorRecursoSingleton;
import Controller.Strategy.EstrategiaPrioridad;
import Model.Emergencia;
import Model.ServicioEmergenciaBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor centralizado para manejar el registro, notificación y seguimiento de
 * emergencias.
 * Implementa el patrón Observer para comunicar emergencias a los servicios
 * correspondientes.
 */
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

    public List<String> atenderEmergencias(ControladorRecursoSingleton recursos) {
        List<String> resultados = new ArrayList<>();
        List<Emergencia> emergenciasAtendidas = new ArrayList<>();
    
        for (Emergencia emergencia : emergenciasRegistradas) {
            System.out.println("Procesando emergencia: " + emergencia);
        
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
                    """ +"\n"+ "Asignando servicio: " + servicio.getNombreServicio() +"\n" + servicio.getNombreServicio() + " asignado a emergencia en " + emergencia.getUbicacion() +
                        "\n" + servicio.getNombreServicio() + " atendiendo emergencia..." +
                        "\nResolviendo " + emergencia.getTipo().name().toLowerCase() + " en "
                        + emergencia.getUbicacion() +
                        "\nEmergencia atendida con éxito: " + emergencia.getTipo().name().toLowerCase() + " resuelto en " + emergencia.getUbicacion() + " en " + tiempoRespuesta + " minutos." + "\n\n\t" +
                    "<--------------------------------------------->");
        
                // Agregar la emergencia a la lista de atendidas
                emergenciasAtendidas.add(emergencia);
            } else {
                resultados.add("No hay servicios disponibles para atender la emergencia: " + emergencia);
            }
        }
    
        // Eliminar las emergencias atendidas de la lista principal
        emergenciasRegistradas.removeAll(emergenciasAtendidas);
    
        return resultados;
    }

    public List<Emergencia> getEmergenciasRegistradas() {
        return emergenciasRegistradas;
    }

    public void limpiarEmergencias() {
        emergenciasRegistradas.clear();
    }

}
