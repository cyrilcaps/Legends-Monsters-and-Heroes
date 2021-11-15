import java.util.Arrays;
import java.util.Set;

public class EventPotion extends Event {

    public EventPotion(Party party) {
        enter(party);
    }

    public void enter(Party party) {
        //Only 1 hero per party so get the first hero
        //Line below IntelliJ warning: might be a suspicious call to 'Map.get'
        Character character = party.getHeroes().get(party.getHeroes().keySet().toArray()[0]);
        if (character.getInventory().getPotions().size() != 0) {
            Set<String> potionNames = character.getInventory().getPotions().keySet();
            String selectedPotionName = (String) Input.getInputWithMenuBack(Arrays.asList(potionNames.toArray()), true);
            if (selectedPotionName != null) {
                ItemPotion selectedPotion = character.getInventory().getPotions().get(selectedPotionName);
                selectedPotion.consume(character);
                System.out.println("Potion was successfully consumed!");
            }
        }
        else {
            System.out.println("Hero has no potions to consume!");
        }
    }
}
