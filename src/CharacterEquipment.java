public class CharacterEquipment {
    ItemWeapon mainHand;
    Equipable offHand;
    ItemArmor armor;

    public CharacterEquipment() {

    }

    public ItemWeapon getMainHand() {
        return mainHand;
    }

    public ItemArmor getArmor() {
        return armor;
    }

    public void setMainHand(ItemWeapon mainHand) {
        this.mainHand = mainHand;
    }

    public void setArmor(ItemArmor armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "mainHand=" + mainHand +
                ", armor=" + armor +
                '}';
    }
}
