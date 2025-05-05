# Sistema de Emergencias Urbanas 🚨

Este sistema es una aplicación de consola diseñada para gestionar emergencias urbanas, asignar recursos disponibles y calcular métricas de desempeño. Utiliza patrones de diseño avanzados como **Singleton**, **Strategy**, **Factory**, y **Observer**, además de lambdas y clases internas para mejorar la funcionalidad y la experiencia del usuario.

---

## **Características Principales**

### 1. **Gestión de Emergencias**
- Permite registrar emergencias de diferentes tipos:
  - **Incendio**
  - **Robo**
  - **Accidente Vehicular**
- Cada emergencia incluye:
  - **Ubicación**: Lugar donde ocurre la emergencia.
  - **Gravedad**: Nivel de gravedad (BAJA, MEDIA, ALTA).
  - **Tipo**: Tipo de emergencia.

### 2. **Asignación de Recursos**
- Asigna recursos disponibles (como bomberos, policías y ambulancias) a las emergencias según su tipo.
- Los recursos se gestionan mediante un patrón **Singleton** para garantizar que solo haya una instancia centralizada.
- Los recursos se filtran dinámicamente usando lambdas para verificar su disponibilidad.

### 3. **Prioridad de Emergencias**
- Utiliza el patrón **Strategy** para priorizar emergencias según:
  - **Gravedad**: Emergencias más graves se atienden primero.
  - **Cercanía**: Emergencias más cercanas a la base de operaciones tienen prioridad.

### 4. **Cálculo de Métricas**
- Calcula estadísticas al final de cada jornada:
  - **Emergencias atendidas**.
  - **Tiempo promedio de respuesta**.
  - **Recursos disponibles restantes**.

### 5. **Interfaz de Usuario en Consola**
- Menú interactivo con opciones claras y colores llamativos para mejorar la experiencia del usuario.
- Mensajes de confirmación, advertencia y error con colores diferenciados.
- Pausa después de cada acción importante con la opción de presionar ENTER para continuar.

---

## **Menú Principal**

El sistema presenta las siguientes opciones en el menú principal:

1. **Registrar nueva emergencia**  
   Permite registrar una emergencia indicando su tipo, ubicación y nivel de gravedad.

2. **Atender emergencia**  
   Asigna recursos disponibles a las emergencias registradas y muestra los resultados.

3. **Ver recursos disponibles**  
   Muestra la cantidad de recursos disponibles y sus nombres. Si no hay recursos, indica que están agotados.

4. **Mostrar estadísticas del día**  
   Muestra las métricas de desempeño, como emergencias atendidas, tiempo promedio de respuesta y recursos restantes.

5. **Salir**  
   Finaliza la ejecución del sistema.

---

## **Patrones de Diseño Utilizados**

### 1. **Singleton**
- Implementado en la clase `ControladorRecursoSingleton` para gestionar los recursos de manera centralizada.

### 2. **Strategy**
- Implementado en la clase `GestorEmergencia` para priorizar emergencias según diferentes estrategias (gravedad o cercanía).

### 3. **Factory**
- Implementado en la clase `EmergenciaFactory` para crear instancias de emergencias según su tipo.

### 4. **Observer**
- Implementado para notificar a los observadores cuando se registra una nueva emergencia.

---

## **Tecnologías y Herramientas**

- **Lenguaje**: Java
- **IDE**: Visual Studio Code
- **Paradigma**: Programación Orientada a Objetos (POO)
- **Características Avanzadas**:
  - Lambdas y Streams para manipulación de listas.
  - Clases internas para modelar mapas urbanos y calcular distancias.

---

## **Cómo Ejecutar el Sistema**

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd Reto02/ChallengeTwo
   ```

3. Compila el proyecto:
   ```bash
   javac -d bin src/**/*.java
   ```

4. Ejecuta el sistema:
   ```bash
   java -cp bin View.Main
   ```

---

## **Ejemplo de Uso**

### **Registrar Emergencia**
```plaintext
Seleccione una opción: 1
Ingrese el tipo de emergencia:
1. Incendio
2. Robo
3. Accidente Vehicular
Seleccione el tipo de emergencia: 1
Ingrese la ubicación: Bogotá
Seleccione el nivel de gravedad:
1. BAJA
2. MEDIA
3. ALTA
Seleccione el nivel de gravedad: 3
Emergencia registrada: Ubicacion 'Bogotá' | Gravedad ALTA | Tipo INCENDIO
```

### **Atender Emergencia**
```plaintext
<------------ Atendiendo emergencia ------------>
Bombero asignado a emergencia en Bogotá
Bombero atendiendo emergencia...
Resolviendo incendio en Bogotá
Emergencia atendida con éxito: incendio resuelto en Bogotá en 7.5 minutos.
```

### **Mostrar Estadísticas**
```plaintext
<------------ Estadísticas ------------>
Emergencias atendidas: 1
Tiempo promedio de respuesta: 7.5 minutos
Recursos disponibles: 2
```

---


¡Gracias por usar el Sistema de Emergencias Urbanas! 🚒👮‍♂️🚑
