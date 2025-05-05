package View;

import Controller.Observer.GestorEmergencia;
import Controller.Singleton.ControladorRecursoSingleton;
import Model.Clases_Concretas.Ambulancia;
import Model.Clases_Concretas.Bombero;
import Model.Clases_Concretas.Policia;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicializar el controlador de emergencias y recursos
            GestorEmergencia gestor = new GestorEmergencia();
            ControladorRecursoSingleton recursos = ControladorRecursoSingleton.getInstancia();

            // Registrar servicios disponibles en el sistema
            recursos.registrarServicio(new Bombero());
            recursos.registrarServicio(new Policia());
            recursos.registrarServicio(new Ambulancia());

            // Crear la vista de consola e iniciar el sistema
            ConsolaView vista = new ConsolaView(gestor, recursos);
            vista.iniciar();

        } catch (Exception e) {
            System.err.println("Error al iniciar el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

}