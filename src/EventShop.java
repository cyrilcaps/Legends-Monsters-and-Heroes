import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventShop extends Event {
    private List<ItemWeapon> weapons;
    private List<ItemArmor> armors;
    private List<Spell> spells;
    private List<ItemConsumable> potions;

    public EventShop(List<ItemWeapon> weapons, List<ItemArmor> armors, List<Spell> spells, List<ItemConsumable> potions) {
        this.weapons = weapons;
        this.armors = armors;
        this.spells = spells;
        this.potions = potions;
    }

    @Override
    public void enter(Party party) {

    }

    private void categorySelector(Character character) {
        while (true) {
            String category = Input.getInputWithMenuBack(
                    Arrays.asList("Weapons", "Armors", "Spells", "Potions"), true);
            if (category == null) {
                return;
            }
            buy(category, character);
        }
    }

    private void buy(String category, Character character) {
        switch (category) {
            case "Weapons":
                ItemWeapon weapon = itemSelector(weapons, character);
                ItemWeapon prevWeapon = character.getEquipment().equipMainhHand(weapon);
                if (prevWeapon != null) {
                    character.getCurrency().addGold(prevWeapon.getPrice() / 2);
                }
                break;
            case "Armors":
                ItemArmor armor = itemSelector(armors, character);
                ItemArmor prevArmor = character.getEquipment().equipArmor(armor);
                if (prevArmor != null) {
                    character.getCurrency().addGold(prevArmor.getPrice() / 2);
                }
                break;
            case "Spells":
                Spell spell = itemSelector(spells, character);
                character.getSpells().add(spell);
                break;
            case "Potions":
                ItemConsumable potion = itemSelector(potions, character);
                character.getInventory().addPotion(potion);
                break;
        }
    }

    private void sell(String category, Character character) {
        switch (category) {
            case "Weapons":
                ItemWeapon weapon = itemSelector(weapons, character);
                character.getEquipment().equipMainhHand(weapon);
                break;
            case "Armors":
                ItemArmor armor = itemSelector(armors, character);
                character.getEquipment().equipArmor(armor);
                break;
            case "Spells":
                Spell spell = itemSelector(spells, character);
                character.getSpells().add(spell);
                break;
            case "Potions":
                ItemConsumable potion = itemSelector(potions, character);
                character.getInventory().addPotion(potion);
                break;
        }
    }

    private <E extends Item> E itemSelector(List<E> items, Character character) {
        while (true) {
            E item = Input.getInputWithMenuBack(items, true);
            if (item == null) {
                return null;
            }

            // check sufficient level and gold before confirming purchase
            if (item.getLevelRequirement() > character.getLevel().getLevel()) {
                System.out.println("Insufficient level");
            } else if (item.getPrice() > character.getCurrency().getGold()) {
                System.out.println("Insufficient gold");
            } else if (Input.getConfirm("Purchase " + item + " ?", Arrays.asList("Y", "y"))) {
                return item;
            }

        }
    }
}
