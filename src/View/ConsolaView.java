package View;

import Controller.Factory.EmergenciaFactory;
import Controller.Observer.GestorEmergencia;
import Controller.Singleton.ControladorRecursoSingleton;
import Model.Emergencia;
import Model.MapaUrbano;
import Model.ServicioEmergenciaBase;
import Model.Enums.NivelDeGravedad;
import Model.Enums.TipoEmergencia;

import java.util.List;
import java.util.Scanner;

public class ConsolaView {
    private final Scanner scanner = new Scanner(System.in);
    private final GestorEmergencia gestor;
    private final ControladorRecursoSingleton recursos;
    private final MapaUrbano mapa;
    private int ultimaOpcionSeleccionada = 0;

    public ConsolaView(GestorEmergencia gestor, ControladorRecursoSingleton recursos, MapaUrbano mapa) {
        this.gestor = gestor;
        this.recursos = recursos;
        this.mapa = mapa;
    }

    public void iniciar() {
        System.out.println("\n" + """
                ===============================================
                | Bienvenido al Sistema de Emergencias Urbanas |
                ===============================================
                """);
        boolean activo = true;

        while (activo) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");

            try {
                validarOpcion(opcion);

                switch (opcion) {
                    case 1 -> {
                        registrarEmergencia();
                        ultimaOpcionSeleccionada = 1;
                    }
                    case 2 -> {
                        if (ultimaOpcionSeleccionada != 1) {
                            throw new IllegalStateException("Debe registrar una emergencia antes de atenderla.");
                        }
                        atenderEmergencias();
                        ultimaOpcionSeleccionada = 2;
                    }
                    case 3 -> {
                        mostrarRecursosDisponibles();
                        ultimaOpcionSeleccionada = 3;
                    }
                    case 4 -> {
                        mostrarEstadisticas();
                        ultimaOpcionSeleccionada = 4;
                    }
                    case 5 -> {
                        System.out.println("Gracias por usar el sistema.");
                        activo = false;
                    }
                    default -> throw new IllegalArgumentException("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n1. Registrar nueva emergencia");
        System.out.println("2. Atender emergencia");
        System.out.println("3. Ver recursos disponibles");
        System.out.println("4. Mostrar estadísticas del día");
        System.out.println("5. Salir");
    }

    private void registrarEmergencia() {
        TipoEmergencia tipo = seleccionarTipoEmergencia();
        String zona = seleccionarZona();
        NivelDeGravedad gravedad = seleccionarGravedad();

        Emergencia emergencia = EmergenciaFactory.crearEmergencia(tipo, zona, gravedad);
        gestor.registrarEmergencia(emergencia);
        System.out.println("Emergencia registrada: " + emergencia);
    }

    private void atenderEmergencias() {
        List<String> resultados = gestor.atenderEmergencias(recursos);

        if (resultados.isEmpty()) {
            System.out.println("No hay emergencias registradas para atender.");
            return;
        }

        for (String resultado : resultados) {
            System.out.println(resultado);
        }
    }

    private String seleccionarZona() {
        System.out.println("\n\t" + """
                <------------ Zonas ------------>
                """);
        System.out.println("1. Norte");
        System.out.println("2. Sur");
        System.out.println("3. Este");
        System.out.println("4. Oeste");
        System.out.println("5. Centro");

        int opcion = leerEntero("Seleccione una zona: ");
        String zona = switch (opcion) {
            case 1 -> "Norte";
            case 2 -> "Sur";
            case 3 -> "Este";
            case 4 -> "Oeste";
            case 5 -> "Centro";
            default -> throw new IllegalArgumentException("Opción inválida.");
        };

        // Registrar la zona en el mapa si no está registrada
        if (!mapa.ubicacionRegistrada(zona)) {
            switch (zona) {
                case "Norte" -> mapa.registrarUbicacion("Norte", new MapaUrbano.Coordenadas(10, 0));
                case "Sur" -> mapa.registrarUbicacion("Sur", new MapaUrbano.Coordenadas(-10, 0));
                case "Este" -> mapa.registrarUbicacion("Este", new MapaUrbano.Coordenadas(0, 10));
                case "Oeste" -> mapa.registrarUbicacion("Oeste", new MapaUrbano.Coordenadas(0, -10));
                case "Centro" -> mapa.registrarUbicacion("Centro", new MapaUrbano.Coordenadas(0, 0));
            }
        }

        return zona;
    }

    private TipoEmergencia seleccionarTipoEmergencia() {
        System.out.println("\n\t" + """
                <------------ Emergencias ------------>
                """);
        System.out.println("\n1. Incendio");
        System.out.println("2. Robo");
        System.out.println("3. Accidente Vehicular");
        int opcion = leerEntero("Seleccione el tipo de emergencia: ");
        return switch (opcion) {
            case 1 -> TipoEmergencia.INCENDIO;
            case 2 -> TipoEmergencia.ROBO;
            case 3 -> TipoEmergencia.ACCIDENTE_VEHICULAR;
            default -> throw new IllegalArgumentException("Opción inválida.");
        };

    }

    private NivelDeGravedad seleccionarGravedad() {
        System.out.println("\n\t" + """
                <------------ Gravedades ------------>
                """);
        System.out.println("1. Baja");
        System.out.println("2. Media");
        System.out.println("3. Alta");
        int opcion = leerEntero("Seleccione el nivel de gravedad: ");
        return switch (opcion) {
            case 1 -> NivelDeGravedad.BAJA;
            case 2 -> NivelDeGravedad.MEDIA;
            case 3 -> NivelDeGravedad.ALTA;
            default -> throw new IllegalArgumentException("Opción inválida.");
        };
    }

    private void mostrarRecursosDisponibles() {
        List<ServicioEmergenciaBase> recursosDisponibles = recursos.filtrarRecursosDisponibles();
        System.out.println("\n\t" + """
                <------------ Recursos ------------>
                """);

        if (recursosDisponibles.isEmpty()) {
            System.out.println("Recursos disponibles: AGOTADO");
        } else {
            System.out.println("Recursos disponibles: " + recursosDisponibles.size());
            for (ServicioEmergenciaBase recurso : recursosDisponibles) {
                System.out.println("- " + recurso.getNombreServicio());
            }
        }

        esperarEnter(); // Esperar a que el usuario presione ENTER
    }

    private void mostrarEstadisticas() {
        System.out.println("\n\t" + """
                <------------ Estadísticas ------------>
                """);
        System.out.println("Emergencias atendidas: " + gestor.getTotalEmergenciasAtendidas());
        System.out.println("Tiempo promedio de respuesta: " + gestor.calcularTiempoPromedioRespuesta() + " minutos");
        System.out.println("Recursos disponibles: " + recursos.filtrarRecursosDisponibles().size());

        esperarEnter(); // Esperar a que el usuario presione ENTER
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }

    private void esperarEnter() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    private void validarOpcion(int opcion) {
        if (opcion < 1 || opcion > 5) {
            throw new IllegalArgumentException("Debe seleccionar una opción válida entre 1 y 5.");
        }
    }
}