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
        // USANDO EL DIRECTOR (forma simplificada)
        // =====================================================
        System.out.println("--- Usando el Director ---\n");

        PizzaDirector director = new PizzaDirector();

        // Ejemplo 2: Pizza Margarita usando Director
        Pizza margarita = director.buildMargarita(PizzaSize.MEDIUM);
        System.out.println("Pizza Margarita (Director):");
        System.out.println(margarita);
        System.out.println();

        // Ejemplo 3: Pizza Pepperoni usando Director
        Pizza pepperoni = director.buildPepperoni(PizzaSize.BIG);
        System.out.println("Pizza Pepperoni (Director):");
        System.out.println(pepperoni);
        System.out.println();

        // Ejemplo 4: Pizza Vegetariana usando Director
        Pizza vegetarian = director.buildVegetarian(PizzaSize.BIG);
        System.out.println("Pizza Vegetariana (Director):");
        System.out.println(vegetarian);
        System.out.println();

        // Ejemplo 5: Pizza BBQ usando Director
        Pizza bbq = director.buildBBQ(PizzaSize.SMALL);
        System.out.println("Pizza BBQ (Director):");
        System.out.println(bbq);
        System.out.println();

        // Ejemplo 6: Pizza Hawaiana usando Director
        Pizza hawaiian = director.buildHawaiian(PizzaSize.MEDIUM);
        System.out.println("Pizza Hawaiana (Director):");
        System.out.println(hawaiian);
        System.out.println();

        // Ejemplo 7: Pizza Cuatro Quesos usando Director
        Pizza fourCheese = director.buildFourCheese(PizzaSize.SMALL);
        System.out.println("Pizza Cuatro Quesos (Director):");
        System.out.println(fourCheese);
        System.out.println();

        // Ejemplo 8: Pizza Meat Lovers usando Director
        Pizza meatLovers = director.buildMeatLovers(PizzaSize.BIG);
        System.out.println("Pizza Meat Lovers (Director):");
        System.out.println(meatLovers);
        System.out.println();

        // Ejemplo 9: Pizza Supreme usando Director
        Pizza supreme = director.buildSupreme(PizzaSize.BIG);
        System.out.println("Pizza Supreme (Director):");
        System.out.println(supreme);
        System.out.println();

        // =====================================================
        // COMPARACIÓN
        // =====================================================
        System.out.println("=== Resumen ===");
        System.out.println("El Director simplifica la creación de pizzas predefinidas,");
        System.out.println("mientras que el Builder permite total personalización.");
        System.out.println("\nVentajas del Director:");
        System.out.println("- Encapsula recetas comunes");
        System.out.println("- Código cliente más limpio");
        System.out.println("- Centraliza la lógica de construcción");
        System.out.println("- Fácil de mantener y modificar recetas");
    }
}
