package Controller.Strategy;

import java.util.List;

import Model.Emergencia;

public interface EstrategiaPrioridad {

    List<Emergencia> priorizar (List<Emergencia> emergencias);

}
