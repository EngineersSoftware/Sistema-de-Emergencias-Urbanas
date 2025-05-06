package View;

import Controller.Observer.GestorEmergencia;
import Controller.Singleton.ControladorRecursoSingleton;
import Controller.Strategy.EstrategiaPorCercania;
import Model.MapaUrbano;
import Model.Clases_Concretas.Ambulancia;
import Model.Clases_Concretas.Bombero;
import Model.Clases_Concretas.Policia;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicializar el controlador de emergencias y recursos
            GestorEmergencia gestor = new GestorEmergencia();
            ControladorRecursoSingleton recursos = ControladorRecursoSingleton.getInstancia();

            // Inicializar el mapa urbano con la base de operaciones
            MapaUrbano mapa = new MapaUrbano(new MapaUrbano.Coordenadas(0, 0)); // Base en (0, 0)

            // Configurar la estrategia de cercanía
            gestor.setEstrategia(new EstrategiaPorCercania(mapa));

            // Registrar servicios disponibles en el sistema
            recursos.registrarServicio(new Bombero());
            recursos.registrarServicio(new Policia());
            recursos.registrarServicio(new Ambulancia());

            // Crear la vista de consola e iniciar el sistema
            ConsolaView vista = new ConsolaView(gestor, recursos, mapa);
            vista.iniciar();

        } catch (Exception e) {
            System.err.println("Error al iniciar el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

}