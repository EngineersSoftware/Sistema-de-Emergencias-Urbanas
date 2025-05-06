# Sistema de Emergencias Urbanas 🚨

Este sistema es una aplicación de consola diseñada para gestionar emergencias urbanas, asignar recursos disponibles y calcular métricas de desempeño. Utiliza patrones de diseño avanzados como **Singleton**, **Strategy**, **Factory**, y **Observer**, además de lambdas y clases internas para mejorar la funcionalidad y la experiencia del usuario.

---

## 🎯 Características Principales

### 1. Gestión de Emergencias
- **Tipos de Emergencias**:
  - 🔥 Incendio
  - 🚨 Robo
  - 🚗 Accidente Vehicular
- **Atributos de Emergencia**:
  - Ubicación (Norte, Sur, Este, Oeste, Centro)
  - Nivel de Gravedad (BAJA, MEDIA, ALTA)
  - Tipo de Emergencia
  - Estado de atención

### 2. Sistema de Recursos
- **Tipos de Recursos**:
  - 🚒 Bomberos
  - 🚓 Policías
  - 🚑 Ambulancias
- **Gestión de Estado**:
  - Control de disponibilidad
  - Asignación automática según tipo de emergencia
  - Monitoreo de recursos activos

### 3. Características Técnicas

#### Patrones de Diseño Implementados
1. **Singleton** 
   - `ControladorRecursoSingleton`: Gestión centralizada de recursos
   - Garantiza una única instancia del controlador de recursos

2. **Observer**
   - `GestorEmergencia`: Notifica sobre nuevas emergencias
   - `ObservadorEmergencia`: Interface para observadores
   - Notificación en tiempo real de eventos

3. **Strategy**
   - Estrategias de priorización:
     - `EstrategiaPorCercania`: Prioriza por distancia
     - `EstrategiaPorGravedad`: Prioriza por nivel de gravedad

4. **Factory**
   - `EmergenciaFactory`: Creación de diferentes tipos de emergencias
   - Facilita la creación de nuevos tipos de emergencias

#### Sistema de Coordenadas
- Mapa urbano con sistema de coordenadas cartesianas
- Base de operaciones en (0,0)
- Cálculo de distancias para priorización
- Zonas predefinidas con coordenadas específicas

#### Interfaz de Usuario
- Menú interactivo en consola
- Opciones principales:
  1. Registrar nueva emergencia
  2. Atender emergencia
  3. Ver recursos disponibles
  4. Mostrar estadísticas del día
  5. Salir
- Validación de entradas
- Feedback visual con mensajes informativos

### 4. Métricas y Estadísticas
- Total de emergencias atendidas
- Tiempo promedio de respuesta
- Recursos disponibles en tiempo real
- Seguimiento de estado de emergencias

### 5. Características Avanzadas
- Priorización automática de emergencias
- Sistema de coordenadas para ubicación
- Gestión de recursos en tiempo real
- Validación de datos de entrada
- Manejo de excepciones robusto


## 🛠 Tecnologías Utilizadas
- Java (Programación Orientada a Objetos)
- Colecciones Java (List, ArrayList)
- Streams y Lambdas
- Enums para tipos constantes
- Manejo de excepciones



## 📊 Diagrama de Componentes
```
Sistema de Emergencias
├── Controller
│   ├── Factory
│   ├── Observer
│   ├── Singleton
│   └── Strategy
├── Model
│   ├── Clases_Concretas
│   ├── Emergencias
│   └── Enums
│   └── Interface
|   |__ Emergencia
|   |__ Mapa Urbano
|   |__ ServicioEmergenciaBase
|    
└── View
    └── ConsolaView
    └── Main
```


¡Atrevete a usar el Sistema de Emergencias Urbanas! 🚒👮‍♂️🚑
