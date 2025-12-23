# Configuración de Lombok

Este proyecto ahora utiliza **Lombok** para reducir el código boilerplate (getters, setters, toString, etc.).

## Instalación

### Opción 1: Maven (Recomendado)

Si usas Maven, las dependencias se descargarán automáticamente:

```bash
mvn clean install
```

### Opción 2: Descargar Lombok manualmente

1. Descarga el JAR de Lombok desde: https://projectlombok.org/download
2. Coloca `lombok.jar` en la carpeta `lib/` del proyecto
3. Ejecuta: `java -jar lombok.jar` para instalar el plugin en tu IDE

### Opción 3: Plugin del IDE

#### IntelliJ IDEA
1. Ve a `File` → `Settings` → `Plugins`
2. Busca "Lombok"
3. Instala el plugin y reinicia el IDE
4. Habilita el procesamiento de anotaciones:
   - `File` → `Settings` → `Build, Execution, Deployment` → `Compiler` → `Annotation Processors`
   - Marca "Enable annotation processing"

#### Eclipse
1. Descarga `lombok.jar` desde https://projectlombok.org/download
2. Ejecuta: `java -jar lombok.jar`
3. Selecciona tu instalación de Eclipse
4. Reinicia Eclipse

#### VS Code
1. Instala la extensión "Language Support for Java(TM) by Red Hat"
2. Lombok se detectará automáticamente si está en las dependencias de Maven

## Compilación con Lombok

### Con Maven:
```bash
mvn clean compile
```

### Sin Maven (compilación manual):
```bash
# Descarga lombok.jar primero
javac -cp "lombok.jar" -d out/production/SingletonLogger src/**/*.java
```

## Anotaciones usadas en el proyecto

- **@Getter**: Genera getters automáticamente
- **@Setter**: Genera setters automáticamente (usado solo cuando no hay lógica de validación)
- **@ToString**: Genera el método toString()
- **@Getter(lombok.AccessLevel.NONE)**: Excluye un campo del getter automático (para getters personalizados)

## Verificación

Si ves errores como "lombok cannot be resolved", es porque:
1. Lombok no está instalado en tu IDE
2. El procesamiento de anotaciones no está habilitado
3. Las dependencias de Maven no se han descargado

Sigue las instrucciones anteriores para resolver estos problemas.

