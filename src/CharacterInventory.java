import java.util.HashMap;
import java.util.Map;

public class CharacterInventory {
    private final Map<String, ItemConsumable> potions = new HashMap<>();

    public CharacterInventory() {
    }

    public Map<String, ItemConsumable> getPotions() {
        return potions;
    }

    // add potion to inventory, or increment count if exists
    public void addPotion(ItemConsumable potion) {
        ItemConsumable existingPotion = potions.putIfAbsent(potion.getName(), potion);
        if (existingPotion != null) {
            existingPotion.addCount(1);
        }
    }

    // get potion, decrement count, remove if 0
    public ItemConsumable getPotion(String potionName) {
        ItemConsumable potion = potions.get(potionName);
        potion.addCount(-1);
        if (potion.getCount() == 0) {
            potions.remove(potionName);
        }
        return potion;
    }
}
