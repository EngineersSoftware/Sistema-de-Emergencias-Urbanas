package Controller.Strategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Model.Emergencia;

public class EstrategiaPorGravedad implements EstrategiaPrioridad{

    @Override    
    public List<Emergencia> priorizar(List<Emergencia> emergencias){
        return emergencias.stream()
                .sorted(Comparator.comparing(Emergencia::getGravedad).reversed())
                .collect(Collectors.toList());
    }

}
