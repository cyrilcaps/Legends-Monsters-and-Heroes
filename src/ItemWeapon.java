public class ItemWeapon extends Item implements Equipable {
    private int requiredHands;
    private int damage;

    @Override
    public CharacterStats equip(Character c, Equipable e) {
        return null;
    }

    @Override
    public CharacterStats unequip(Character c) {
        return null;
    }
}
