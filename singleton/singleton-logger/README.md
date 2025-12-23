# Sistema de Registro Académico - Java Swing GUI con Singleton Logger

## Descripción

Aplicación de escritorio desarrollada en Java con interfaz gráfica Swing que implementa el patrón de diseño **Singleton** para centralizar el sistema de logging. Sistema completo de gestión académica que permite administrar estudiantes, materias, profesores y carreras universitarias con operaciones CRUD completas, registrando automáticamente todas las operaciones en un log visual en tiempo real.

## Características Principales

### 🎯 Patrón Singleton
- Implementación thread-safe del patrón Singleton en la clase `Logger`
- Una única instancia compartida en toda la aplicación
- Sistema de notificación a listeners para actualización en tiempo real de la GUI

### 📝 Gestión de Entidades
El sistema permite gestionar cuatro tipos de entidades académicas:

1. **Estudiantes** - Registro completo con nombre, apellido, email y carrera asignada
2. **Materias** - Asignaturas con código, nombre, créditos y profesor asignado
3. **Profesores** - Docentes con nombre, email y departamento
4. **Carreras** - Planes de estudio con código, nombre y duración en años

### ✨ Operaciones CRUD
Para cada entidad se pueden realizar:
- **Create** - Crear nueva entidad con validación de datos
- **Read** - Visualizar en tabla con búsqueda inteligente
- **Update** - Actualizar información (email, asignaciones, etc.)
- **Delete** - Eliminar con confirmación

### 📋 Sistema de Logs
- Visualización en tiempo real de todas las operaciones
- Logs con colores según nivel:
  - 🔵 **INFO** (azul) - Operaciones exitosas
  - 🟠 **WARN** (naranja) - Advertencias
  - 🔴 **ERROR** (rojo) - Errores
- Función de limpieza de logs

## Estructura del Proyecto

```
SingletonLogger/
├── src/
│   ├── domain/
│   │   └── entity/
│   │       ├── Student.java         # Entidad Estudiante
│   │       ├── Subject.java         # Entidad Materia
│   │       ├── Professor.java       # Entidad Profesor
│   │       └── CareerPath.java      # Entidad Carrera
│   ├── main/
│   │   └── Main.java               # Punto de entrada de la aplicación
│   ├── ui/
│   │   ├── MainFrame.java          # Ventana principal
│   │   └── panels/
│   │       ├── StudentPanel.java    # Panel gestión estudiantes
│   │       ├── SubjectPanel.java    # Panel gestión materias
│   │       ├── ProfessorPanel.java  # Panel gestión profesores
│   │       ├── CareerPathPanel.java # Panel gestión carreras
│   │       └── LogPanel.java        # Panel visualización logs
│   └── util/
│       ├── Logger.java              # Singleton Logger
│       ├── LoggerHelper.java        # Helper para logs
│       └── IdGenerator.java         # Generador IDs único
└── out/
    └── production/
        └── SingletonLogger/         # Archivos compilados
```

## Requisitos

- **Java JDK 8 o superior**
- **Java Swing** (incluido en JDK)

## Compilación

```bash
javac -d out/production/SingletonLogger -sourcepath src src/main/Main.java
```

O usa los archivos batch incluidos:
```bash
# Windows
compile.bat
```

## Ejecución

```bash
java -cp out/production/SingletonLogger main.Main
```

O usa los archivos batch incluidos:
```bash
# Windows - Compilar y ejecutar
compile-and-run.bat

# O solo ejecutar (si ya está compilado)
run.bat
```

## Uso de la Interfaz Gráfica

### Pestaña de Estudiantes 🎓
1. Ingresa el nombre y apellido del estudiante
2. Proporciona un email válido (debe contener @)
3. Opcionalmente selecciona una carrera (debe crearse primero)
4. Click en "Registrar"
5. Gestiona estudiantes con los botones:
   - **Actualizar Email** - Cambiar el email del estudiante
   - **Cambiar Carrera** - Asignar otra carrera
   - **Eliminar Seleccionado** - Dar de baja al estudiante
   - **Refrescar** - Actualizar lista de carreras disponibles
6. Usa el campo de búsqueda para filtrar por nombre, email o carrera

### Pestaña de Materias 📚
1. Ingresa el nombre de la materia
2. Asigna un código único (se convierte a mayúsculas automáticamente)
3. Define el número de créditos (1-10)
4. Opcionalmente selecciona un profesor (debe crearse primero)
5. Click en "Crear Materia"
6. Gestiona materias con los botones:
   - **Actualizar Nombre** - Cambiar el nombre de la materia
   - **Cambiar Profesor** - Asignar otro profesor
   - **Eliminar Seleccionado** - Eliminar la materia
   - **Refrescar** - Actualizar lista de profesores disponibles

### Pestaña de Profesores 👨‍🏫
1. Ingresa el nombre completo del profesor
2. Proporciona un email válido
3. Especifica el departamento académico
4. Click en "Crear Profesor"
5. Gestiona profesores con los botones:
   - **Actualizar Email** - Cambiar el email
   - **Actualizar Departamento** - Cambiar de departamento
   - **Eliminar Seleccionado** - Dar de baja al profesor
6. Busca profesores por nombre, email o departamento

### Pestaña de Carreras 🎯
1. Ingresa el nombre de la carrera
2. Asigna un código único (se convierte a mayúsculas automáticamente)
3. Define la duración en años (1-10)
4. Click en "Crear Carrera"
5. Gestiona carreras con los botones:
   - **Actualizar Seleccionado** - Cambiar el nombre de la carrera
   - **Eliminar Seleccionado** - Eliminar la carrera
   - **Refrescar** - Actualizar la tabla

### Pestaña de Logs 📋
- Visualiza en tiempo real todas las operaciones realizadas
- Los logs se colorean según su nivel (INFO, WARN, ERROR)
- Click en "Limpiar Logs" para vaciar el registro visual
- Auto-scroll al último log registrado

## Validaciones

El sistema incluye validaciones automáticas:
- Nombres, apellidos, códigos y emails no pueden estar vacíos
- Los emails deben contener el símbolo @
- Los códigos se convierten automáticamente a mayúsculas
- Créditos y duración deben ser números positivos
- Se capturan excepciones y se registran como errores
- Confirmación antes de eliminar entidades
- Mensajes informativos para el usuario

## Relaciones entre Entidades

- Un **Estudiante** puede estar inscrito en una **Carrera** (relación opcional)
- Una **Materia** puede ser impartida por un **Profesor** (relación opcional)
- Los cambios en asignaciones se registran automáticamente en el log

## Patrón de Diseño: Singleton

La clase `Logger` implementa el patrón Singleton con las siguientes características:

```java
public class Logger {
    private static Logger instance;
    
    private Logger() {
        // Constructor privado
    }
    
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
}
```

### Ventajas del Singleton en este proyecto:
- ✅ Una única instancia de Logger en toda la aplicación
- ✅ Punto de acceso global al sistema de logging
- ✅ Control centralizado de todos los registros académicos
- ✅ Thread-safe con sincronización
- ✅ Notificación a múltiples listeners (GUI)

## Tecnologías Utilizadas

- **Java** - Lenguaje de programación
- **Java Swing** - Framework para interfaz gráfica
- **AWT** - Abstract Window Toolkit para componentes UI
- **Patrón Singleton** - Para el sistema de logging
- **Patrón Observer** - Para notificaciones de logs a la GUI

## Características Técnicas

- **Thread-safe** - Logger sincronizado para entornos multi-hilo
- **Event Dispatch Thread** - GUI ejecutada en EDT para evitar problemas de concurrencia
- **Generación automática de IDs** - Contador incremental por tipo de entidad
- **Listeners de eventos** - Actualización reactiva de la UI
- **Validación de datos** - Input sanitization y manejo de excepciones
- **Relaciones entre entidades** - Sistema de referencias entre objetos del dominio

## Casos de Uso

### Registro de Nuevo Estudiante
1. Crear primero una carrera en la pestaña "Carreras"
2. Ir a "Estudiantes"
3. Ingresar datos del estudiante
4. Seleccionar la carrera creada
5. El sistema registra la operación en el log

### Asignación de Profesor a Materia
1. Crear el profesor en la pestaña "Profesores"
2. Ir a "Materias"
3. Crear o seleccionar una materia existente
4. Usar "Cambiar Profesor" para asignar
5. El cambio se registra automáticamente

## Autor

Proyecto de práctica de Design Patterns - Sistema de Registro Académico con Singleton Logger

## Licencia

Uso educativo y de aprendizaje
