public class CharacterDirector {

    /**
     * Construye un personaje ágil de rango (arquero/rogue)
     * Template base para clases como Archer o Rogue
     */
    public void constructRangedAgile(Character.CharacterBuilder builder) {
        builder.characterClass("Archer")
               .strength(10)
               .agility(25)
               .intelligence(15)
               .constitution(10)
               .primaryWeapon("Longbow")
               .armor("Leather Armor")
               .addItem("Quiver of Arrows")
               .addItem("Rope")
               .addItem("Healing Potion");
    }

    /**
     * Construye un personaje tanque cuerpo a cuerpo
     * Template base para clases como Warrior o Paladin
     */
    public void constructMeleeTank(Character.CharacterBuilder builder) {
        builder.characterClass("Warrior")
               .strength(25)
               .agility(10)
               .intelligence(5)
               .constitution(20)
               .primaryWeapon("Greatsword")
               .armor("Plate Armor")
               .addItem("Shield")
               .addItem("Health Potion")
               .addItem("Bandages");
    }

    /**
     * Construye un mago ofensivo
     * Template base para Mage o Necromancer
     */
    public void constructOffensiveCaster(Character.CharacterBuilder builder) {
        builder.characterClass("Mage")
               .strength(5)
               .agility(10)
               .intelligence(30)
               .constitution(10)
               .primaryWeapon("Staff of Fire")
               .armor("Mystic Robes")
               .specialAbility("Fireball")
               .addItem("Mana Potion")
               .addItem("Spell Tome")
               .addItem("Crystal Focus");
    }

    /**
     * Construye un sanador/soporte
     * Template base para Priest o Bard
     */
    public void constructHealer(Character.CharacterBuilder builder) {
        builder.characterClass("Priest")
               .strength(5)
               .agility(8)
               .intelligence(25)
               .constitution(15)
               .primaryWeapon("Holy Mace")
               .armor("Cloth Vestments")
               .specialAbility("Holy Light")
               .addItem("Holy Water")
               .addItem("Prayer Beads")
               .addItem("Bandages");
    }
}
