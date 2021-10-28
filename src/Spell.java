public class Spell {
    private final String name;
    private final int price;
    private final int levelRequirement;
    private final int damage;
    private final int manaCost;
    private final SpellType type;

    public Spell(String name, int price, int levelRequirement, int damage, int manaCost, SpellType type) {
        this.name = name;
        this.price = price;
        this.levelRequirement = levelRequirement;
        this.damage = damage;
        this.manaCost = manaCost;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public SpellType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", levelRequirement=" + levelRequirement +
                ", price=" + price +
                ", damage=" + damage +
                ", manaCost=" + manaCost +
                ", type=" + type +
                '}';
    }
}
