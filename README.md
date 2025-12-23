# 🎨 Patrones de Diseño en Java - Práctica

<div align="center">

[![Java](https://img.shields.io/badge/Java-21-red.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Educational-blue.svg)](LICENSE)

Colección de implementaciones prácticas de patrones de diseño en Java con ejemplos del mundo real.

</div>

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Patrones Implementados](#-patrones-implementados)
  - [Builder Pattern](#-builder-pattern)
  - [Factory Method Pattern](#-factory-method-pattern)
  - [Singleton Pattern](#-singleton-pattern)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Requisitos](#-requisitos)
- [Cómo Usar Este Repositorio](#-cómo-usar-este-repositorio)
- [Tecnologías](#-tecnologías)

---

## 📖 Descripción

Este repositorio contiene implementaciones prácticas de patrones de diseño en Java. Cada patrón incluye múltiples ejemplos con casos de uso reales, documentación detallada y código ejecutable para facilitar el aprendizaje y comprensión de estos conceptos fundamentales en la ingeniería de software.

---

## 🎯 Patrones Implementados

### 🏗️ Builder Pattern

Patrón creacional que permite construir objetos complejos paso a paso, proporcionando una interfaz fluida y legible.

#### Proyectos:

| Proyecto | Descripción | Tecnología | Documentación |
|----------|-------------|------------|---------------|
| **[Build Your PC](builder/build-your-pc/)** | Constructor de PCs personalizadas con componentes modulares | Java 21 | - |
| **[Pizza Builder](builder/pizza-builder/)** | Sistema de construcción de pizzas con múltiples configuraciones | Maven + Java 21 | [📖 README](builder/pizza-builder/README.md) |
| **[RPG Character Builder](builder/rpg-character-builder/)** | Creador de personajes de RPG con atributos y habilidades | Java 21 | - |

**Características clave:**
- ✅ Constructor privado para inmutabilidad
- ✅ API fluida con encadenamiento de métodos
- ✅ Validación centralizada
- ✅ Código legible y mantenible

---

### 🏭 Factory Method Pattern

Patrón creacional que define una interfaz para crear objetos, permitiendo que las subclases decidan qué clase instanciar.

#### Proyectos:

| Proyecto | Descripción | Tecnología | Documentación |
|----------|-------------|------------|---------------|
| **[Factory Notification System](factory-method/factory-notification-system/)** | Sistema de notificaciones con múltiples canales (Email, SMS, Push) | Maven + Java 21 | [📖 Assignment](factory-method/factory-notification-system/assignment.md) |
| **[Factory Reporting System](factory-method/factory-reporting-system/)** | Generador de reportes en diferentes formatos | Maven + Java 21 | - |

**Características clave:**
- ✅ Desacoplamiento entre creación y uso
- ✅ Extensibilidad sin modificar código existente
- ✅ Cumplimiento del principio Open/Closed
- ✅ Facilita el testing y mantenimiento

**Ejemplo del Sistema de Notificaciones:**
```java
// Crear notificación por email
NotificationCreator creator = new EmailNotificationCreator();
Notification notification = creator.createNotification();
notification.send("Su pedido ha sido enviado");
```

---

### 🔒 Singleton Pattern

Patrón creacional que garantiza que una clase tenga una única instancia y proporciona un punto de acceso global a ella.

#### Proyectos:

| Proyecto | Descripción | Tecnología | Documentación |
|----------|-------------|------------|---------------|
| **[Singleton Logger](singleton/singleton-logger/)** | Sistema de registro académico con GUI Swing y logger centralizado | Maven + Java 21 + Swing | [📖 README](singleton/singleton-logger/README.md) |

**Características del proyecto:**
- 🎓 Sistema completo de gestión académica (Estudiantes, Profesores, Materias, Carreras)
- 📝 Operaciones CRUD completas
- 🎨 Interfaz gráfica profesional con Swing
- 📋 Sistema de logs en tiempo real con niveles (INFO, WARN, ERROR)
- 🎯 Implementación thread-safe del patrón Singleton
- 🔔 Sistema de notificaciones con patrón Observer

**Características clave del patrón:**
- ✅ Una única instancia en toda la aplicación
- ✅ Punto de acceso global
- ✅ Thread-safe con sincronización
- ✅ Control centralizado de recursos

**Ejemplo de uso:**
```java
Logger logger = Logger.getInstance();
logger.log(LogLevel.INFO, "Operación exitosa");
```

---

## 📁 Estructura del Proyecto

```
design-patterns-practice/
│
├── builder/                          # Patrón Builder
│   ├── build-your-pc/               # Constructor de PCs
│   ├── pizza-builder/               # Constructor de pizzas
│   └── rpg-character-builder/       # Constructor de personajes RPG
│
├── factory-method/                   # Patrón Factory Method
│   ├── factory-notification-system/ # Sistema de notificaciones
│   └── factory-reporting-system/    # Sistema de reportes
│
├── singleton/                        # Patrón Singleton
│   └── singleton-logger/            # Logger con GUI académica
│
└── README.md                         # Este archivo
```

---

## 🔧 Requisitos

### Requisitos Generales:
- **Java JDK:** 21 o superior
- **Maven:** 3.6+ (para proyectos Maven)
- **Git:** Para clonar el repositorio

### Requisitos por Proyecto:

| Proyecto | Java | Maven | Otros |
|----------|------|-------|-------|
| Build Your PC | 21+ | No | - |
| Pizza Builder | 21+ | Sí | - |
| RPG Character Builder | 21+ | No | - |
| Factory Notification System | 21+ | Sí | - |
| Factory Reporting System | 21+ | Sí | - |
| Singleton Logger | 21+ | Sí | Java Swing |

---

## 🚀 Cómo Usar Este Repositorio

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/design-patterns-practice.git
cd design-patterns-practice
```

### 2. Navegar a un Proyecto Específico

```bash
# Ejemplo: Pizza Builder
cd builder/pizza-builder
```

### 3. Compilar y Ejecutar

#### Proyectos Maven:

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn exec:java -Dexec.mainClass="org.example.Main"
```

#### Proyectos Java Puro:

```bash
# Compilar
javac -d out/production src/*.java

# Ejecutar
java -cp out/production Main
```

#### Singleton Logger (Windows):

```bash
# Compilar y ejecutar
compile-and-run.bat

# Solo ejecutar
run.bat
```

### 4. Explorar el Código

Cada proyecto incluye:
- 📝 Código fuente bien documentado
- 📖 README específico (cuando aplique)
- 🧪 Ejemplos de uso en la clase Main
- 📋 Comentarios explicativos

---

## 🛠️ Tecnologías

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white)

</div>

- **Lenguaje:** Java 21+
- **Build Tools:** Maven 3.6+
- **GUI:** Java Swing (Singleton Logger)
- **Paradigma:** Programación Orientada a Objetos
- **Principios:** SOLID, Clean Code

---

## 📚 Recursos Adicionales

### Patrones de Diseño:
- [Refactoring Guru - Design Patterns](https://refactoring.guru/design-patterns)
- [Head First Design Patterns](https://www.oreilly.com/library/view/head-first-design/9781492077992/)
- [Design Patterns: Elements of Reusable Object-Oriented Software](https://en.wikipedia.org/wiki/Design_Patterns)

### Principios SOLID:
- **S** - Single Responsibility Principle
- **O** - Open/Closed Principle
- **L** - Liskov Substitution Principle
- **I** - Interface Segregation Principle
- **D** - Dependency Inversion Principle

---

## 🎓 Patrones de Diseño

Los patrones de diseño se clasifican en tres categorías:

### Creacionales (Implementados)
- ✅ **Builder** - Construcción de objetos complejos
- ✅ **Factory Method** - Creación de objetos mediante interfaces
- ✅ **Singleton** - Única instancia de una clase

### Estructurales (Próximamente)
- 🔜 Adapter
- 🔜 Decorator
- 🔜 Facade

### Comportamentales (Próximamente)
- 🔜 Observer
- 🔜 Strategy
- 🔜 Command

---

## 💡 Consejos de Aprendizaje

1. **Comienza con el Builder Pattern** - Es el más intuitivo y tiene ejemplos visuales
2. **Practica con Factory Method** - Comprende la abstracción de creación
3. **Experimenta con Singleton** - El proyecto incluye una GUI completa
4. **Modifica el código** - La mejor forma de aprender es experimentando
5. **Lee la documentación** - Cada proyecto tiene comentarios explicativos

---

## 🤝 Contribuciones

Este es un proyecto educativo. Si encuentras errores o tienes sugerencias:

1. Haz fork del repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-mejora`)
3. Commit tus cambios (`git commit -am 'Agrega nueva mejora'`)
4. Push a la rama (`git push origin feature/nueva-mejora`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto es de uso educativo y de aprendizaje.

---

## 👨‍💻 Autor

Proyecto de práctica de Design Patterns en Java

---

<div align="center">

**⭐ Si este repositorio te fue útil, considera darle una estrella!**

[🔝 Volver arriba](#-patrones-de-diseño-en-java---práctica)

</div>