public class ItemWeapon extends Item implements Equipable {
    private final int damage;
    private final int requiredHands;

    public ItemWeapon(String name, int price, int levelRequirement, int damage, int requiredHands) {
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

    @Override
    public String toString() {
        return super.toString() + " DMG: " + damage + ", HANDS: " + requiredHands;
    }
}
