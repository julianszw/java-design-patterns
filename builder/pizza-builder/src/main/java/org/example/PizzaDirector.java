package org.example;

/**
 * Clase Director del patrón Builder.
 * Conoce cómo construir diferentes tipos de pizzas predefinidas
 * usando el PizzaBuilder.
 * 
 * El Director encapsula las "recetas" de pizzas populares,
 * simplificando el código cliente y centralizando la lógica
 * de construcción de productos complejos.
 * 
 * NOTA SOBRE reset():
 * El Director puede trabajar de dos formas:
 * 1. Creando un nuevo builder para cada pizza (enfoque simple)
 * 2. Manteniendo un builder y usando reset() para reutilizarlo (enfoque óptimo)
 * 
 * Esta implementación demuestra el segundo enfoque para ilustrar
 * el uso correcto del método reset() en el patrón Builder.
 */
public class PizzaDirector {

    // El Director mantiene una referencia al builder para reutilizarlo
    private Pizza.PizzaBuilder builder;

    /**
     * Constructor que inicializa el Director con su propio builder.
     * El Director reutilizará este builder para todas las construcciones,
     * llamando a reset() antes de cada nueva pizza.
     */
    public PizzaDirector() {
        this.builder = new Pizza.PizzaBuilder();
    }

    /**
     * Constructor alternativo que permite inyectar un builder externo.
     * Útil para casos donde el cliente quiere controlar el builder.
     * 
     * @param builder El builder que el Director usará
     */
    public PizzaDirector(Pizza.PizzaBuilder builder) {
        this.builder = builder;
    }

    /**
     * Permite cambiar el builder en tiempo de ejecución.
     * Útil si se necesita usar diferentes configuraciones de builder.
     * 
     * @param builder El nuevo builder a usar
     */
    public void setBuilder(Pizza.PizzaBuilder builder) {
        this.builder = builder;
    }

    /**
     * Obtiene el builder actual (para uso avanzado).
     * 
     * @return El builder que el Director está usando
     */
    public Pizza.PizzaBuilder getBuilder() {
        return this.builder;
    }

    /**
     * Construye una Pizza Margarita clásica.
     * Masa fina, salsa de tomate, mozzarella y tomates frescos.
     * 
     * Nota: Usa reset() para limpiar el builder antes de configurarlo.
     */
    public Pizza buildMargarita(PizzaSize size) {
        // reset() limpia cualquier configuración previa del builder
        return builder.reset()
                .size(size)
                .crustType(CrustType.THIN)
                .sauce(SauceType.TOMATO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredient(Ingredient.TOMATOES)
                .build();
    }

    /**
     * Construye una Pizza Pepperoni clásica.
     * Masa fina, salsa de tomate, mozzarella y pepperoni.
     */
    public Pizza buildPepperoni(PizzaSize size) {
        return builder.reset()
                .size(size)
                .crustType(CrustType.THIN)
                .sauce(SauceType.TOMATO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredient(Ingredient.PEPPERONI)
                .addIngredient(Ingredient.EXTRA_CHEESE)
                .build();
    }

    /**
     * Construye una Pizza Vegetariana completa.
     * Masa sin gluten, salsa pesto, mozzarella y vegetales variados.
     */
    public Pizza buildVegetarian(PizzaSize size) {
        return builder.reset()
                .size(size)
                .crustType(CrustType.GLUTEN_FREE)
                .sauce(SauceType.PESTO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredients(
                        Ingredient.MUSHROOMS,
                        Ingredient.GREEN_PEPPERS,
                        Ingredient.ONIONS,
                        Ingredient.SPINACH,
                        Ingredient.TOMATOES,
                        Ingredient.BLACK_OLIVES
                )
                .build();
    }

    /**
     * Construye una Pizza BBQ especial.
     * Masa gruesa, salsa BBQ, cheddar, bacon y salchicha.
     */
    public Pizza buildBBQ(PizzaSize size) {
        return builder.reset()
                .size(size)
                .crustType(CrustType.THICK)
                .sauce(SauceType.BBQ)
                .cheese(CheeseType.CHEDDAR)
                .addIngredients(
                        Ingredient.BACON,
                        Ingredient.SAUSAGE,
                        Ingredient.ONIONS
                )
                .build();
    }

    /**
     * Construye una Pizza Hawaiana.
     * Masa rellena, salsa de tomate, mozzarella, jamón y piña.
     */
    public Pizza buildHawaiian(PizzaSize size) {
        return builder.reset()
                .size(size)
                .crustType(CrustType.STUFFED)
                .sauce(SauceType.TOMATO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredients(
                        Ingredient.HAM,
                        Ingredient.PINEAPPLE
                )
                .build();
    }

    /**
     * Construye una Pizza Cuatro Quesos.
     * Masa gruesa, salsa blanca, mezcla de quesos.
     */
    public Pizza buildFourCheese(PizzaSize size) {
        return builder.reset()
                .size(size)
                .crustType(CrustType.THICK)
                .sauce(SauceType.WHITE_SAUCE)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredient(Ingredient.EXTRA_CHEESE)
                .build();
    }

    /**
     * Construye una Pizza Meat Lovers (Carnívora).
     * Masa gruesa, salsa de tomate, mozzarella y todas las carnes.
     */
    public Pizza buildMeatLovers(PizzaSize size) {
        return builder.reset()
                .size(size)
                .crustType(CrustType.THICK)
                .sauce(SauceType.TOMATO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredients(
                        Ingredient.PEPPERONI,
                        Ingredient.BACON,
                        Ingredient.SAUSAGE,
                        Ingredient.HAM
                )
                .build();
    }

    /**
     * Construye una Pizza Supreme (Suprema).
     * La pizza más completa con carnes y vegetales.
     */
    public Pizza buildSupreme(PizzaSize size) {
        return builder.reset()
                .size(size)
                .crustType(CrustType.STUFFED)
                .sauce(SauceType.TOMATO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredients(
                        Ingredient.PEPPERONI,
                        Ingredient.SAUSAGE,
                        Ingredient.MUSHROOMS,
                        Ingredient.ONIONS,
                        Ingredient.GREEN_PEPPERS,
                        Ingredient.BLACK_OLIVES
                )
                .build();
    }
}

