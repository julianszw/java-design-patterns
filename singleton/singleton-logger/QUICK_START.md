# 🚀 Guía Rápida de Inicio

## Opción 1: Usar Scripts Batch (Windows - MÁS FÁCIL)

### Compilar y Ejecutar en un solo paso:
```batch
compile-and-run.bat
```

### O por separado:

1. **Compilar:**
```batch
compile.bat
```

2. **Ejecutar:**
```batch
run.bat
```

## Opción 2: Línea de Comandos

### Compilar:
```bash
javac -d out/production/SingletonLogger -sourcepath src src/main/Main.java
```

### Ejecutar:
```bash
java -cp out/production/SingletonLogger main.Main
```

## 📖 Primeros Pasos con la GUI

### 1. Crear Autores
- Ve a la pestaña **"📝 Autores"**
- Escribe un nombre (ej: "Gabriel García Márquez")
- Click en **"Crear Autor"**
- ¡El log se actualiza automáticamente!

### 2. Crear Libros
- Ve a la pestaña **"📚 Libros"**
- Escribe un título (ej: "Cien años de soledad")
- Selecciona un autor (si quieres)
- Click en **"Crear Libro"**

### 3. Crear Usuarios
- Ve a la pestaña **"👤 Usuarios"**
- Escribe un username (ej: "juan_perez")
- Click en **"Crear Usuario"**

### 4. Ver los Logs
- Ve a la pestaña **"📋 Logs"**
- Observa todas las operaciones registradas con colores:
  - **Azul** = Información
  - **Naranja** = Advertencias
  - **Rojo** = Errores

## 🎯 Funciones Principales

| Operación | Cómo hacerlo |
|-----------|--------------|
| **Crear** | Llenar formulario → Click botón verde "Crear" |
| **Actualizar** | Seleccionar fila → Click "Actualizar Seleccionado" |
| **Eliminar** | Seleccionar fila → Click botón rojo "Eliminar" |
| **Buscar** | Escribir en campo de búsqueda → Click "Buscar" |
| **Ver todos** | Click "Mostrar Todos" o "Refrescar" |

## 💡 Consejos

- **Para libros con autor:** Crea primero los autores en su pestaña
- **Actualizar autores en libros:** Click "Refrescar" en la pestaña de Libros
- **Limpiar logs:** Usa el botón "Limpiar Logs" en la pestaña de Logs
- **Errores:** Todos los errores se registran en rojo en el log

## ❓ Solución de Problemas

### La aplicación no compila
- Verifica que tengas Java JDK instalado: `java -version`
- Asegúrate de estar en el directorio correcto del proyecto

### La GUI no aparece
- Revisa que la compilación fue exitosa (sin errores)
- Verifica la consola para ver mensajes de error

### No aparecen los autores en el dropdown de libros
- Click en el botón **"Refrescar"** en la pestaña de Libros
- O cambia de pestaña y vuelve a la de Libros

## 🎨 Características Visuales

La aplicación cuenta con:
- ✅ Interfaz moderna y colorida
- ✅ Tablas ordenadas con scroll
- ✅ Botones con colores intuitivos (verde=crear, azul=actualizar, rojo=eliminar)
- ✅ Logs en tiempo real con syntax highlighting
- ✅ Diálogos de confirmación para operaciones destructivas
- ✅ Mensajes de éxito/error para feedback al usuario

¡Disfruta explorando el patrón Singleton en acción! 🎉

