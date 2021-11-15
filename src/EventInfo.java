import java.util.*;

public class EventInfo extends Event {

    public EventInfo(Party party) {
        enter(party);
    }

    public void enter(Party party) {
        //Only 1 hero per party so get the first hero
        //Line below IntelliJ warning: might be a suspicious call to 'Map.get'
        CharacterHero character = party.getHeroes().get(party.getHeroes().keySet().toArray()[0]);
        while (true) {
            System.out.println("Would you like to equip a weapon or an armor piece? ([0] to decline)");
            String equipSelect = Input.getInputWithMenuBack(Arrays.asList("Weapon", "Armor"), true);
            if (equipSelect == null) {
                break;
            }

            if ("Weapon".equals(equipSelect)) {
                equipItem(character, true);
                break;
            }
            else if ("Armor".equals(equipSelect)) {
                equipItem(character, false);
                break;
            }
        }
    }

    private void equipItem(Character character, boolean pickedWeapon) {
        if (pickedWeapon) {
            //Player chose to equip weapon
            if (character.getInventory().getWeapons().size() != 0) {
                Set<String> weaponNames = character.getInventory().getWeapons().keySet();
                String selectedWeaponName = (String) Input.getInputWithMenuBack(Arrays.asList(weaponNames.toArray()), true);
                if (selectedWeaponName != null) {
                    ItemWeapon selectedWeapon = character.getInventory().getWeapons().get(selectedWeaponName);
                    selectedWeapon.equip(character);
                    System.out.println("Weapon was successfully equipped!");
                }
            }
            else {
                System.out.println("Hero has no extra weapons in their inventory to equip!");
            }
        }
        else {
            //Player chose to equip armor
            if (character.getInventory().getWeapons().size() != 0) {
                Set<String> armorNames = character.getInventory().getArmor().keySet();
                String selectedArmorName = (String) Input.getInputWithMenuBack(Arrays.asList(armorNames.toArray()), true);
                if (selectedArmorName != null) {
                    ItemArmor selectedArmor = character.getInventory().getArmor().get(selectedArmorName);
                    selectedArmor.equip(character);
                    System.out.println("Armor was successfully equipped!");
                }
            }
            else {
                System.out.println("Hero has no extra armor in their inventory to equip!");
            }
        }
    }
}
