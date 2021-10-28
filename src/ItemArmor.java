public class ItemArmor extends Item implements Equipable {
    private int damageReduction;

    public ItemArmor(String name, int levelRequirement, int price, int damageReduction) {
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
}
