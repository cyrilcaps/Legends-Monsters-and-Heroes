public class CharacterEquipment {
    ItemWeapon mainHand = new ItemWeapon("Hands", 0, 0, 0, 2);
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

    public ItemWeapon equipMainhHand(ItemWeapon mainHand) {
        ItemWeapon previous = this.mainHand;
        this.mainHand = mainHand;
        return previous;
    }

    public ItemArmor equipArmor(ItemArmor armor) {
        ItemArmor previous = this.armor;
        this.armor = armor;
        return previous;
    }
}
