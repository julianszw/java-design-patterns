# Pizza Builder Pattern

Proyecto de ejemplo en Java que implementa el patrón de diseño **Builder** para la construcción de objetos Pizza con múltiples configuraciones.

## 📋 Descripción

Este proyecto demuestra el uso del patrón Builder para crear objetos complejos (pizzas) de manera fluida y legible. El patrón Builder es especialmente útil cuando se tiene un objeto con muchos parámetros opcionales.

## 🏗️ Estructura del Proyecto

```
pizza-builder/
├── src/main/java/org/example/
│   ├── Main.java              # Programa principal con ejemplos
│   ├── Pizza.java             # Clase principal con Builder interno
│   ├── PizzaSize.java         # Enum: tamaños de pizza
│   ├── CrustType.java         # Enum: tipos de masa
│   ├── SauceType.java         # Enum: tipos de salsa
│   ├── CheeseType.java        # Enum: tipos de queso
│   └── Ingredient.java        # Enum: ingredientes disponibles
└── pom.xml                    # Configuración Maven
```

## 🎯 Patrón Builder

El patrón Builder se implementa mediante una clase interna estática `PizzaBuilder` dentro de la clase `Pizza`. Esto proporciona:

- **Constructor privado**: Solo el Builder puede crear instancias de Pizza
- **Inmutabilidad**: Los atributos de Pizza son `final`
- **API fluida**: Los métodos del builder retornan `this` para encadenamiento
- **Validación**: El método `build()` valida los campos obligatorios
- **Legibilidad**: Código más legible y mantenible

## 💻 Uso

### Ejemplo básico

```java
Pizza pepperoniPizza = Pizza.builder()
    .size(PizzaSize.BIG)
    .crustType(CrustType.THIN)
    .sauce(SauceType.TOMATO)
    .cheese(CheeseType.MOZZARELLA)
    .addIngredient(Ingredient.PEPPERONI)
    .addIngredient(Ingredient.EXTRA_CHEESE)
    .build();
```

### Agregar múltiples ingredientes

```java
Pizza vegetarianPizza = Pizza.builder()
    .size(PizzaSize.BIG)
    .crustType(CrustType.GLUTEN_FREE)
    .sauce(SauceType.PESTO)
    .cheese(CheeseType.MOZZARELLA)
    .addIngredients(
        Ingredient.MUSHROOMS,
        Ingredient.GREEN_PEPPERS,
        Ingredient.ONIONS,
        Ingredient.SPINACH
    )
    .build();
```

## 🚀 Compilar y Ejecutar

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar el programa

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

O directamente con Java:

```bash
java -cp target/classes org.example.Main
```

## 📦 Componentes

### Enums disponibles

#### PizzaSize
- `SMALL` - Pizza pequeña
- `BIG` - Pizza grande

#### CrustType
- `THIN` - Masa delgada
- `THICK` - Masa gruesa
- `STUFFED` - Masa rellena
- `GLUTEN_FREE` - Sin gluten

#### SauceType
- `TOMATO` - Salsa de tomate
- `BBQ` - Salsa BBQ
- `PESTO` - Salsa pesto
- `WHITE_SAUCE` - Salsa blanca
- `NO_SAUCE` - Sin salsa

#### CheeseType
- `MOZZARELLA`
- `CHEDDAR`
- `PARMESAN`
- `GOUDA`
- `NO_CHEESE` - Sin queso

#### Ingredient
- `PEPPERONI`
- `MUSHROOMS` - Champiñones
- `ONIONS` - Cebollas
- `SAUSAGE` - Salchicha
- `BACON`
- `EXTRA_CHEESE` - Queso extra
- `BLACK_OLIVES` - Aceitunas negras
- `GREEN_PEPPERS` - Pimientos verdes
- `PINEAPPLE` - Piña
- `SPINACH` - Espinaca
- `TOMATOES` - Tomates
- `HAM` - Jamón

## ✅ Ventajas del Patrón Builder

1. **Legibilidad**: El código es más fácil de leer y entender
2. **Inmutabilidad**: Los objetos creados son inmutables y thread-safe
3. **Validación**: Validación centralizada antes de crear el objeto
4. **Flexibilidad**: Fácil agregar nuevos parámetros sin romper el código existente
5. **Sin constructores telescópicos**: Evita múltiples constructores sobrecargados

## 📝 Requisitos

- Java 21 o superior
- Maven 3.6+

## 👨‍💻 Autor

Proyecto de ejemplo para demostración del patrón Builder en Java.

