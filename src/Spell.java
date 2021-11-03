public class Spell extends Item {
    private final int damage;
    private final int manaCost;
    private final SpellType type;

    public Spell(String name, int price, int levelRequirement, int damage, int manaCost, SpellType type) {
        super(name, levelRequirement, price);
        this.damage = damage;
        this.manaCost = manaCost;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public int getDamage(int dexterity) {
        return damage + (dexterity/10000) * damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public SpellType getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + getManaCost() + " Mana, " + getDamage() + " " + getType() + " damage";
    }
}
