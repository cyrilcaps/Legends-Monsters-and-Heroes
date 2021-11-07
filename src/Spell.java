public class Spell extends Item implements Castable {
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
    public void cast(Character c, Character target) {
        c.getStats().useMana(getManaCost());
        target.getStats().spellDebuff(getType());
        System.out.println(c.getName() + " casts " + getName() + " on " + target.getName());
        target.applyCombat(new ActionCombat(
                ActionCombatType.SPELL, getDamage(c.getStats().getDexterity()), target.getName()));
    }

    @Override
    public String toString() {
        return super.toString() + ", " + getManaCost() + " Mana, " + getDamage() + " " + getType() + " damage";
    }
}
