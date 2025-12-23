package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Pizza Builder Pattern Demo ===\n");

        // =====================================================
        // USANDO EL BUILDER DIRECTAMENTE (forma tradicional)
        // =====================================================
        System.out.println("--- Usando Builder Directamente ---\n");

        // Ejemplo 1: Pizza Pepperoni personalizada con Builder
        Pizza customPizza = Pizza.builder()
                .size(PizzaSize.BIG)
                .crustType(CrustType.THIN)
                .sauce(SauceType.TOMATO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredient(Ingredient.PEPPERONI)
                .addIngredient(Ingredient.EXTRA_CHEESE)
                .addIngredient(Ingredient.MUSHROOMS)
                .build();

        System.out.println("Pizza Personalizada (Builder):");
        System.out.println(customPizza);
        System.out.println();

        // =====================================================
        // DEMOSTRACIÓN DEL MÉTODO reset()
        // =====================================================
        System.out.println("--- Demostración de reset() ---\n");

        // Creamos UN SOLO builder y lo reutilizamos para múltiples pizzas
        Pizza.PizzaBuilder reuseableBuilder = Pizza.builder();

        // Primera pizza con el builder
        Pizza pizza1 = reuseableBuilder
                .size(PizzaSize.SMALL)
                .crustType(CrustType.THIN)
                .sauce(SauceType.TOMATO)
                .cheese(CheeseType.MOZZARELLA)
                .addIngredient(Ingredient.PEPPERONI)
                .build();

        System.out.println("Pizza 1 (antes de reset):");
        System.out.println(pizza1);
        System.out.println();

        // ¡IMPORTANTE! Sin reset(), los ingredientes se acumularían
        // porque la lista de ingredients no se limpia automáticamente.
        // Por eso reset() es fundamental para reutilizar el builder.

        // Segunda pizza REUTILIZANDO el mismo builder con reset()
        Pizza pizza2 = reuseableBuilder.reset()  // <-- ¡Limpiamos el builder!
                .size(PizzaSize.BIG)
                .crustType(CrustType.STUFFED)
                .sauce(SauceType.BBQ)
                .cheese(CheeseType.CHEDDAR)
                .addIngredient(Ingredient.BACON)
                .addIngredient(Ingredient.ONIONS)
                .build();

        System.out.println("Pizza 2 (después de reset, mismo builder):");
        System.out.println(pizza2);
        System.out.println();

        // Tercera pizza para demostrar que podemos seguir reutilizando
        Pizza pizza3 = reuseableBuilder.reset()
                .size(PizzaSize.MEDIUM)
                .crustType(CrustType.GLUTEN_FREE)
                .sauce(SauceType.PESTO)
                .cheese(CheeseType.PARMESAN)
                .addIngredients(Ingredient.MUSHROOMS, Ingredient.SPINACH)
                .build();

        System.out.println("Pizza 3 (mismo builder reutilizado otra vez):");
        System.out.println(pizza3);
        System.out.println();

        // =====================================================
        // USANDO EL DIRECTOR (con reset() interno)
        // =====================================================
        System.out.println("--- Usando el Director (usa reset() internamente) ---\n");

        // El Director ahora mantiene un builder interno y usa reset()
        // antes de cada construcción
        PizzaDirector director = new PizzaDirector();

        // Todas estas pizzas usan el MISMO builder interno,
        // pero reset() garantiza que cada una empiece limpia
        Pizza margarita = director.buildMargarita(PizzaSize.MEDIUM);
        System.out.println("Pizza Margarita (Director):");
        System.out.println(margarita);
        System.out.println();

        Pizza pepperoni = director.buildPepperoni(PizzaSize.BIG);
        System.out.println("Pizza Pepperoni (Director):");
        System.out.println(pepperoni);
        System.out.println();

        Pizza vegetarian = director.buildVegetarian(PizzaSize.BIG);
        System.out.println("Pizza Vegetariana (Director):");
        System.out.println(vegetarian);
        System.out.println();

        Pizza bbq = director.buildBBQ(PizzaSize.SMALL);
        System.out.println("Pizza BBQ (Director):");
        System.out.println(bbq);
        System.out.println();

        Pizza hawaiian = director.buildHawaiian(PizzaSize.MEDIUM);
        System.out.println("Pizza Hawaiana (Director):");
        System.out.println(hawaiian);
        System.out.println();

        Pizza fourCheese = director.buildFourCheese(PizzaSize.SMALL);
        System.out.println("Pizza Cuatro Quesos (Director):");
        System.out.println(fourCheese);
        System.out.println();

        Pizza meatLovers = director.buildMeatLovers(PizzaSize.BIG);
        System.out.println("Pizza Meat Lovers (Director):");
        System.out.println(meatLovers);
        System.out.println();

        Pizza supreme = director.buildSupreme(PizzaSize.BIG);
        System.out.println("Pizza Supreme (Director):");
        System.out.println(supreme);
        System.out.println();

        // =====================================================
        // INYECCIÓN DE BUILDER AL DIRECTOR
        // =====================================================
        System.out.println("--- Inyectando Builder al Director ---\n");

        // Podemos inyectar nuestro propio builder al Director
        Pizza.PizzaBuilder myBuilder = Pizza.builder();
        PizzaDirector directorConBuilder = new PizzaDirector(myBuilder);

        Pizza customFromDirector = directorConBuilder.buildSupreme(PizzaSize.BIG);
        System.out.println("Pizza usando builder inyectado:");
        System.out.println(customFromDirector);
        System.out.println();

        // También podemos cambiar el builder en tiempo de ejecución
        Pizza.PizzaBuilder anotherBuilder = Pizza.builder();
        directorConBuilder.setBuilder(anotherBuilder);

        Pizza anotherPizza = directorConBuilder.buildMargarita(PizzaSize.SMALL);
        System.out.println("Pizza con nuevo builder cambiado en runtime:");
        System.out.println(anotherPizza);
        System.out.println();

        // =====================================================
        // RESUMEN
        // =====================================================
        System.out.println("=== Resumen del método reset() ===");
        System.out.println("El método reset() es fundamental en el patrón Builder porque:");
        System.out.println("1. Permite REUTILIZAR el mismo builder para múltiples objetos");
        System.out.println("2. Limpia el estado interno (ingredientes, configuraciones)");
        System.out.println("3. Evita crear nuevas instancias del builder repetidamente");
        System.out.println("4. El Director puede mantener UN builder y reutilizarlo");
        System.out.println("5. Garantiza que cada construcción empiece desde cero");
        System.out.println("\nSin reset(), los ingredientes se acumularían entre construcciones!");
    }
}

