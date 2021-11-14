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
        if (c.getEquipment().getArmor() != null) {
            //Send old armor from main hand to inventory
            c.getInventory().addArmor(c.getEquipment().getArmor());
            //Remove armor currently in inventory to be added to armor slot
            c.getInventory().removeArmor(this);
        }
        //Equip new armor
        c.getEquipment().setArmor(this);
    }

    @Override
    public String toString() {
        return super.toString() + " RED: " + damageReduction;
    }
}
