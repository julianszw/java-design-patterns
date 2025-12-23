import java.util.ArrayList;
import java.util.List;

public class Character {
    // 1. ¿Qué implica 'final'? 
    // Implica que una vez asignado el valor en el constructor, NO puede cambiar.
    // Esto hace que tu objeto sea "Thread-safe" e inmutable.
    private final String name;
    private final String characterClass;
    private final int strength;
    private final int agility;
    private final int intelligence;
    private final int constitution;
    private final int healthPoints;
    private final int manaPoints;
    private final String primaryWeapon;
    private final String armor;
    private final String specialAbility;
    private final String relicItem;

    // Nueva lista inmutable
    private final List<String> inventory;

    private Character(CharacterBuilder builder) {
        this.name = builder.name;
        this.characterClass = builder.characterClass;
        this.strength = builder.strength;
        this.agility = builder.agility;
        this.intelligence = builder.intelligence;
        this.constitution = builder.constitution;
        this.primaryWeapon = builder.primaryWeapon;
        this.armor = builder.armor;
        this.specialAbility = builder.specialAbility;
        this.relicItem = builder.relicItem;

        // Protegemos la lista para que no sea modificable desde fuera
        this.inventory = List.copyOf(builder.inventory);

        this.healthPoints = builder.constitution * 10;
        this.manaPoints = builder.intelligence * 7;
    }

    public static class CharacterBuilder {
        private String name;
        private String characterClass;
        private int strength;
        private int agility;
        private int intelligence;
        private int constitution;
        private String primaryWeapon;
        private String armor;
        private String specialAbility;
        private String relicItem;

        // La lista se inicializa aquí
        private List<String> inventory = new ArrayList<>();

        public CharacterBuilder() {
            // El constructor vacío ya inicializa con valores por defecto
        }

        // 2. ¿Por qué reset() es público? 
        // Para que el Director pueda limpiar el builder antes de empezar una 
        // nueva "receta" si decide reutilizar la misma instancia del builder.
        public CharacterBuilder reset() {
            this.name = null;
            this.characterClass = null;
            // 3. Error del WRAP: No uses Integer.parseInt(null), eso da error.
            // Simplemente asigna 0 a los tipos primitivos 'int'.
            this.strength = 0;
            this.agility = 0;
            this.intelligence = 0;
            this.constitution = 0;
            this.primaryWeapon = null;
            this.armor = null;
            this.specialAbility = null;
            this.relicItem = null;
            this.inventory.clear(); // Limpiamos la mochila
            return this;
        }

        public CharacterBuilder name(String name) { this.name = name; return this; }
        public CharacterBuilder characterClass(String characterClass) { this.characterClass = characterClass; return this; }
        public CharacterBuilder strength(int strength) { this.strength = strength; return this; }
        public CharacterBuilder agility(int agility) { this.agility = agility; return this; }
        public CharacterBuilder intelligence(int intelligence) { this.intelligence = intelligence; return this; }
        public CharacterBuilder constitution(int constitution) { this.constitution = constitution; return this; }
        public CharacterBuilder primaryWeapon(String primaryWeapon) { this.primaryWeapon = primaryWeapon; return this; }
        public CharacterBuilder armor(String armor) { this.armor = armor; return this; }
        public CharacterBuilder specialAbility(String specialAbility) { this.specialAbility = specialAbility; return this; }
        public CharacterBuilder relicItem(String relicItem) { this.relicItem = relicItem; return this; }

        // --- MÉTODOS DE INVENTARIO ---

        public CharacterBuilder addItem(String item) {
            if (item != null) this.inventory.add(item);
            return this;
        }

        public CharacterBuilder addItems(List<String> items) {
            if (items != null) this.inventory.addAll(items);
            return this;
        }

        public Character build() {
            // ═══════════════════════════════════════════════════════════════
            // VALIDACIONES COMPLETAS DEL BUILDER
            // ═══════════════════════════════════════════════════════════════

            // --- 1. CAMPOS OBLIGATORIOS ---
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("El nombre del personaje es obligatorio");
            }
            if (characterClass == null || characterClass.trim().isEmpty()) {
                throw new IllegalStateException("La clase del personaje es obligatoria");
            }

            // --- 2. VALIDACIÓN DE LONGITUD DEL NOMBRE ---
            if (name.length() < 2 || name.length() > 30) {
                throw new IllegalArgumentException("El nombre debe tener entre 2 y 30 caracteres");
            }

            // --- 3. VALIDACIÓN DE CLASE VÁLIDA ---
            List<String> clasesValidas = List.of("Warrior", "Mage", "Rogue", "Priest", "Archer", "Paladin", "Necromancer", "Bard");
            if (!clasesValidas.contains(characterClass)) {
                throw new IllegalArgumentException("Clase inválida: " + characterClass + 
                    ". Las clases válidas son: " + clasesValidas);
            }

            // --- 4. VALIDACIÓN DE RANGOS DE STATS (1-100) ---
            validateStatRange("Fuerza", strength);
            validateStatRange("Agilidad", agility);
            validateStatRange("Inteligencia", intelligence);
            validateStatRange("Constitución", constitution);

            // --- 5. VALIDACIÓN DE PUNTOS TOTALES (máximo 200 puntos distribuidos) ---
            int totalStats = strength + agility + intelligence + constitution;
            if (totalStats > 200) {
                throw new IllegalArgumentException("Los puntos totales de stats (" + totalStats + 
                    ") no pueden superar 200");
            }
            if (totalStats < 10) {
                throw new IllegalArgumentException("Los puntos totales de stats (" + totalStats + 
                    ") deben ser al menos 10");
            }

            // --- 6. VALIDACIÓN DE COHERENCIA CLASE-STATS ---
            validateClassStats();

            // --- 7. VALIDACIÓN DE EQUIPAMIENTO ---
            if (primaryWeapon != null && primaryWeapon.trim().isEmpty()) {
                throw new IllegalArgumentException("El arma primaria no puede estar vacía si se especifica");
            }
            if (armor != null && armor.trim().isEmpty()) {
                throw new IllegalArgumentException("La armadura no puede estar vacía si se especifica");
            }

            // --- 8. VALIDACIÓN DE INVENTARIO ---
            final int MAX_INVENTORY_SIZE = 20;
            if (inventory.size() > MAX_INVENTORY_SIZE) {
                throw new IllegalStateException("El inventario no puede tener más de " + 
                    MAX_INVENTORY_SIZE + " items. Actual: " + inventory.size());
            }

            // --- 9. VALIDACIÓN DE ITEMS DUPLICADOS EN INVENTARIO ---
            long itemsUnicos = inventory.stream().distinct().count();
            if (itemsUnicos != inventory.size()) {
                throw new IllegalArgumentException("El inventario contiene items duplicados");
            }

            // --- 10. VALIDACIÓN DE HABILIDAD ESPECIAL POR CLASE ---
            validateSpecialAbility();

            // --- 11. VALIDACIÓN DE RELIQUIA (solo clases específicas pueden tener) ---
            if (relicItem != null && !relicItem.trim().isEmpty()) {
                List<String> clasesConReliquia = List.of("Paladin", "Priest", "Necromancer");
                if (!clasesConReliquia.contains(characterClass)) {
                    throw new IllegalArgumentException("Solo las clases " + clasesConReliquia + 
                        " pueden portar reliquias");
                }
            }

            return new Character(this);
        }

        // --- MÉTODOS AUXILIARES DE VALIDACIÓN ---

        private void validateStatRange(String statName, int value) {
            if (value < 1) {
                throw new IllegalArgumentException(statName + " debe ser al menos 1. Valor actual: " + value);
            }
            if (value > 100) {
                throw new IllegalArgumentException(statName + " no puede superar 100. Valor actual: " + value);
            }
        }

        private void validateClassStats() {
            switch (characterClass) {
                case "Warrior":
                case "Paladin":
                    if (strength < 15) {
                        throw new IllegalArgumentException(characterClass + " requiere al menos 15 de Fuerza");
                    }
                    if (constitution < 10) {
                        throw new IllegalArgumentException(characterClass + " requiere al menos 10 de Constitución");
                    }
                    break;
                case "Mage":
                case "Necromancer":
                    if (intelligence < 20) {
                        throw new IllegalArgumentException(characterClass + " requiere al menos 20 de Inteligencia");
                    }
                    break;
                case "Rogue":
                case "Archer":
                    if (agility < 18) {
                        throw new IllegalArgumentException(characterClass + " requiere al menos 18 de Agilidad");
                    }
                    break;
                case "Priest":
                    if (intelligence < 15) {
                        throw new IllegalArgumentException("Priest requiere al menos 15 de Inteligencia");
                    }
                    if (constitution < 8) {
                        throw new IllegalArgumentException("Priest requiere al menos 8 de Constitución");
                    }
                    break;
                case "Bard":
                    // Bard es versátil, requiere balance mínimo
                    if (intelligence < 10 || agility < 10) {
                        throw new IllegalArgumentException("Bard requiere al menos 10 de Inteligencia y 10 de Agilidad");
                    }
                    break;
            }
        }

        private void validateSpecialAbility() {
            if (specialAbility == null || specialAbility.trim().isEmpty()) {
                return; // Habilidad especial es opcional
            }

            // Validar que la habilidad corresponda a la clase
            switch (characterClass) {
                case "Warrior":
                    if (!List.of("Berserker Rage", "Shield Wall", "Battle Cry").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Warrior: " + specialAbility);
                    }
                    break;
                case "Mage":
                    if (!List.of("Fireball", "Ice Storm", "Arcane Blast", "Teleport").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Mage: " + specialAbility);
                    }
                    break;
                case "Rogue":
                    if (!List.of("Backstab", "Vanish", "Poison Blade", "Smoke Bomb").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Rogue: " + specialAbility);
                    }
                    break;
                case "Priest":
                    if (!List.of("Holy Light", "Divine Shield", "Resurrection", "Smite").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Priest: " + specialAbility);
                    }
                    break;
                case "Archer":
                    if (!List.of("Multi Shot", "Precision", "Rain of Arrows", "Eagle Eye").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Archer: " + specialAbility);
                    }
                    break;
                case "Paladin":
                    if (!List.of("Divine Strike", "Aura of Protection", "Lay on Hands", "Consecration").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Paladin: " + specialAbility);
                    }
                    break;
                case "Necromancer":
                    if (!List.of("Raise Dead", "Soul Drain", "Bone Armor", "Plague").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Necromancer: " + specialAbility);
                    }
                    break;
                case "Bard":
                    if (!List.of("Inspire", "Lullaby", "Battle Hymn", "Charm").contains(specialAbility)) {
                        throw new IllegalArgumentException("Habilidad inválida para Bard: " + specialAbility);
                    }
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Character: " + name + " [" + characterClass + "]\n" +
                "Stats: STR:" + strength + " AGI:" + agility + " INT:" + intelligence + "\n" +
                "HP:" + healthPoints + " MP:" + manaPoints + "\n" +
                "Equipment: " + primaryWeapon + ", " + armor + "\n" +
                "Inventory: " + inventory;
    }
}