package org.example;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private final PizzaSize size;
    private final CrustType crustType;
    private final SauceType sauce;
    private final CheeseType cheese;
    private final List<Ingredient> ingredients;

    // Constructor privado que solo el Builder puede usar
    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.crustType = builder.crustType;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.ingredients = builder.ingredients;
    }

    // Método estático para obtener un nuevo builder
    public static PizzaBuilder builder() {
        return new PizzaBuilder();
    }

    // Getters
    public PizzaSize getSize() {
        return size;
    }

    public CrustType getCrustType() {
        return crustType;
    }

    public SauceType getSauce() {
        return sauce;
    }

    public CheeseType getCheese() {
        return cheese;
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size=" + size +
                ", crustType=" + crustType +
                ", sauce=" + sauce +
                ", cheese=" + cheese +
                ", ingredients=" + ingredients +
                '}';
    }

    // Clase Builder interna estática
    public static class PizzaBuilder {
        private PizzaSize size;
        private CrustType crustType;
        private SauceType sauce;
        private CheeseType cheese;
        private List<Ingredient> ingredients;

        // Constructor público
        public PizzaBuilder() {
            this.reset();
        }

        /**
         * Método reset() - Concepto clave del patrón Builder.
         * 
         * Propósito: Reinicia el builder a su estado inicial para poder
         * reutilizarlo y construir un nuevo objeto desde cero.
         * 
         * Ventajas:
         * - Permite reutilizar el mismo builder para múltiples objetos
         * - Evita crear nuevas instancias del builder repetidamente
         * - El Director puede mantener una referencia al builder y reutilizarla
         * - Limpia cualquier estado residual de construcciones anteriores
         * 
         * @return this - para permitir method chaining (encadenamiento fluido)
         */
        public PizzaBuilder reset() {
            this.size = null;
            this.crustType = null;
            this.sauce = null;
            this.cheese = null;
            this.ingredients = new ArrayList<>();
            return this;
        }

        // Métodos fluent para configurar la pizza
        public PizzaBuilder size(PizzaSize size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder crustType(CrustType crustType) {
            this.crustType = crustType;
            return this;
        }

        public PizzaBuilder sauce(SauceType sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder cheese(CheeseType cheese) {
            this.cheese = cheese;
            return this;
        }

        // Método para agregar un ingrediente
        public PizzaBuilder addIngredient(Ingredient ingredient) {
            this.ingredients.add(ingredient);
            return this;
        }

        // Método para agregar múltiples ingredientes
        public PizzaBuilder addIngredients(Ingredient... ingredients) {
            for (Ingredient ingredient : ingredients) {
                this.ingredients.add(ingredient);
            }
            return this;
        }

        // Método build que construye la Pizza
        public Pizza build() {
            // Validaciones opcionales
            if (size == null) {
                throw new IllegalStateException("El tamaño de la pizza es obligatorio");
            }
            if (crustType == null) {
                throw new IllegalStateException("El tipo de masa es obligatorio");
            }
            if (sauce == null) {
                throw new IllegalStateException("El tipo de salsa es obligatorio");
            }
            if (cheese == null) {
                throw new IllegalStateException("El tipo de queso es obligatorio");
            }

            return new Pizza(this);
        }
    }
}
