package Controller.Strategy;

import java.util.List;
import java.util.stream.Collectors;

import Model.Emergencia;
import Model.MapaUrbano;

public class EstrategiaPorCercania implements EstrategiaPrioridad {

    private final MapaUrbano mapa;

    public EstrategiaPorCercania(MapaUrbano mapa) {
        this.mapa = mapa;
    }

    @Override
    public List<Emergencia> priorizar(List<Emergencia> emergencias) {
        return emergencias.stream()
                .sorted((e1, e2) -> Double.compare(
                        mapa.calcularDistancia(e1.getUbicacion()),
                        mapa.calcularDistancia(e2.getUbicacion())))
                .collect(Collectors.toList());
    }

}
