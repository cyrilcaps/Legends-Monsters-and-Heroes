import java.util.HashMap;
import java.util.Map;

public class CharacterInventory {
    private final Map<String, ItemPotion> potions = new HashMap<>();

    public CharacterInventory() {
    }

    public Map<String, ItemPotion> getPotions() {
        return potions;
    }

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

    @Override
    public String toString() {
        return "Inventory{" +
                "potions=" + potions +
                '}';
    }
}
