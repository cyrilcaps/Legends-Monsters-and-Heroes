import java.util.HashMap;
import java.util.Map;

public class CharacterInventory {
    private final Map<String, ItemPotion> potions = new HashMap<>();

    //Added space for weapons and armor in a player's inventory
    private final Map<String, ItemWeapon> weapons = new HashMap<>();
    private final Map<String, ItemArmor> armor = new HashMap<>();

    public CharacterInventory() {
    }

    public Map<String, ItemPotion> getPotions() {
        return potions;
    }
    public Map<String, ItemWeapon> getWeapons() { return weapons; }
    public Map<String, ItemArmor> getArmor() { return armor; }

    // add potion to inventory, or increment count if exists
    public void addPotion(ItemPotion potion) {
        ItemPotion existingPotion = potions.putIfAbsent(potion.getName(), potion);
        if (existingPotion != null) {
            existingPotion.addCount(1);
        }
    }

    // get potion, decrement count, remove if 0
    public ItemPotion getPotion(String potionName) {
        ItemPotion potion = potions.get(potionName);
        potion.addCount(-1);
        if (potion.getCount() == 0) {
            potions.remove(potionName);
        }
        return potion;
    }

    //Add a weapon to inventory, no duplicates
    public void addWeapon(ItemWeapon weapon) {
        weapons.putIfAbsent(weapon.getName(), weapon);
    }

    public void removeWeapon(ItemWeapon weapon) {
        weapons.remove(weapon.getName());
    }

    //Add a piece of armor to inventory, no duplicates
    public void addArmor(ItemArmor armor) {
        this.armor.putIfAbsent(armor.getName(), armor);
    }

    public void removeArmor(ItemArmor armor) {
        this.armor.remove(armor.getName());
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "potions=" + potions +
                ", weapons=" + weapons +
                ", armor=" + armor +
                '}';
    }
}
