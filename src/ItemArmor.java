public class ItemArmor extends Item implements Equipable {
    private final int damageReduction;

    public ItemArmor(String name, int price, int levelRequirement, int damageReduction) {
        super(name, levelRequirement, price);
        this.damageReduction = damageReduction;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    @Override
    public void equip(Character c) {
        c.getEquipment().setArmor(this);
    }

    @Override
    public String toString() {
        return super.toString() + " RED: " + damageReduction;
    }
}
