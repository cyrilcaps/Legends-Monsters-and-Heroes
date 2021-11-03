public class ItemArmor extends Item implements Equipable {
    private final int damageReduction;

    public ItemArmor(String name, int price, int levelRequirement, int damageReduction) {
        super(name, levelRequirement, price);
        this.damageReduction = damageReduction;
    }

    @Override
    public CharacterStats equip(Character c, Equipable e) {
        return null;
    }

    @Override
    public CharacterStats unequip(Character c) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " RED: " + damageReduction;
    }
}
