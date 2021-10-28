public class ItemWeapon extends Item implements Equipable {
    private int damage;
    private int requiredHands;

    public ItemWeapon(String name, int levelRequirement, int price, int damage, int requiredHands) {
        super(name, levelRequirement, price);
        this.damage = damage;
        this.requiredHands = requiredHands;
    }

    public int getDamage() {
        return damage;
    }

    public int getRequiredHands() {
        return requiredHands;
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
