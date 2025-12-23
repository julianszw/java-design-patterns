public class Main {
    public static void main(String[] args) {
        CharacterDirector director = new CharacterDirector();

        System.out.println("--- FORGING AETHER-BOUND HEROES ---");

        // --- HERO 1: Void Walker (Using Director + Generic Template) ---
        // El Void Walker de nuestro universo encaja con el perfil 'Ranged Agile'
        Character.CharacterBuilder builder1 = new Character.CharacterBuilder();
        builder1.name("Kaelen");
        director.constructRangedAgile(builder1);

        // Personalizamos la habilidad según nuestro universo
        // Nota: "Void Blink" no es válida para Archer, usamos una habilidad válida
        builder1.specialAbility("Eagle Eye");
        Character voidWalker = builder1.build();

        System.out.println("\nHero 1 Status:");
        System.out.println(voidWalker);


        // --- HERO 2: Sun Forger (Using Director + Customization) ---
        // El Sun Forger encaja con el perfil 'Melee Tank'
        Character.CharacterBuilder builder2 = new Character.CharacterBuilder();
        builder2.name("Valeria");
        director.constructMeleeTank(builder2);

        // El jugador encuentra un item único en la creación
        builder2.primaryWeapon("Plasma Morningstar")
                .specialAbility("Battle Cry");

        Character sunForger = builder2.build();

        System.out.println("\nHero 2 Status:");
        System.out.println(sunForger);


        // --- HERO 3: Validation Test ---
        System.out.println("\nTesting Forge Safety Protocol...");
        try {
            // Intentamos crear un personaje inválido (stats fuera de rango o incompletos)
            Character broken = new Character.CharacterBuilder()
                    .name("Invalid")
                    .characterClass("Warrior")
                    .strength(150) // ¡Fuera del rango 1-100!
                    .agility(10)
                    .intelligence(5)
                    .constitution(15)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Forge Error: " + e.getMessage());
        }

        // --- HERO 4: Otro test de validación - stats insuficientes ---
        System.out.println("\nTesting Class Requirements...");
        try {
            Character weakWarrior = new Character.CharacterBuilder()
                    .name("WeakOne")
                    .characterClass("Warrior")
                    .strength(5)  // Warrior requiere al menos 15
                    .agility(10)
                    .intelligence(5)
                    .constitution(10)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Forge Error: " + e.getMessage());
        }
    }
}
