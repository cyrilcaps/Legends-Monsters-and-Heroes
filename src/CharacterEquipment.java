public class CharacterEquipment {
    Equipable mainHand;
    Equipable offHand;
    Equipable armor;

    public CharacterEquipment() {

    }

    public Equipable getMainHand() {
        return mainHand;
    }

    public Equipable getOffHand() {
        return offHand;
    }

    public Equipable getArmor() {
        return armor;
    }

    public void setMainHand(Character character, Equipable mainHand) {
        this.mainHand.unequip(character);
        this.mainHand = mainHand;
        mainHand.equip(character, this.mainHand);
    }

    public void setOffHand(Character character, Equipable offHand) {
        this.offHand.unequip(character);
        this.offHand = offHand;
        mainHand.equip(character, this.offHand);
    }

    public void setArmor(Character character, Equipable armor) {
        this.armor.unequip(character);
        this.armor = armor;
        mainHand.equip(character, this.armor);
    }
}
