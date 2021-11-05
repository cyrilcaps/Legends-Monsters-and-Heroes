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

    @Override
    public void equip(Character c) {
        c.getEquipment().setMainHand(this);
    }

    @Override
    public String toString() {
        return super.toString() + " DMG: " + damage + ", HANDS: " + requiredHands;
    }
}
