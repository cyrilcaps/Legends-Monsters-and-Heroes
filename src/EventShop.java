import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventShop extends Event {
    private final List<ItemWeapon> weapons;
    private final List<ItemArmor> armors;
    private final List<Spell> spells;
    private final List<ItemPotion> potions;

    public EventShop(List<ItemWeapon> weapons, List<ItemArmor> armors, List<Spell> spells, List<ItemPotion> potions) {
        this.weapons = weapons;
        this.armors = armors;
        this.spells = spells;
        this.potions = potions;
    }

    @Override
    public void enter(Party party) {
        System.out.println("Welcome to the market!");
        List<Character> characters = new ArrayList<>(party.getHeroes().values());
        while (true) {
            System.out.println("Who is entering the market? ([0] to leave)");
            Character character = Input.getInputWithMenuBack(characters, true);
            if (character == null) {
                break;
            }

            String buyOrSell = Input.getInputWithMenuBack(Arrays.asList("Buy", "Sell"), true);
            if (buyOrSell == null) {
                continue;
            }
            switch (buyOrSell) {
                case "Buy":
                    categorySelector(character, true);
                    break;
                case "Sell":
                    categorySelector(character, false);
                    break;
            }
        }
        System.out.println("Leaving the market.");
    }

    // choose category - Weapons, Armors, Spells, Potions
    private void categorySelector(Character character, boolean buy) {
        while (true) {
            // select category, return if null
            String category = Input.getInputWithMenuBack(
                    Arrays.asList("Weapons", "Armors", "Spells", "Potions"), true);
            if (category == null) {
                return;
            }

            // buy or sell
            if (buy) {
                buy(category, character);
            } else {
                sell(category, character);
            }
        }
    }

    // buy method with menus
    private void buy(String category, Character character) {
        Item item = null;

        // select item based on category, relevant actions based on category
        switch (category) {
            case "Weapons":
                ItemWeapon weapon = buyItemSelector(weapons, character);
                ItemWeapon prevWeapon = character.getEquipment().getMainHand();
                if (prevWeapon != null) {
                    character.getCurrency().addGold(prevWeapon.getPrice() / 2);
                }
                if (weapon != null) {
                    weapon.equip(character);
                }
                item = weapon;
                break;
            case "Armors":
                ItemArmor armor = buyItemSelector(armors, character);
                ItemArmor prevArmor = character.getEquipment().getArmor();
                if (prevArmor != null) {
                    character.getCurrency().addGold(prevArmor.getPrice() / 2);
                }
                if (armor != null) {
                    armor.equip(character);
                }
                item = armor;
                break;
            case "Spells":
                Spell spell = buyItemSelector(spells, character);
                if (spell != null) {
                    if (character.getSpells().contains(spell)) {
                        System.out.println(character.getName() + " already knows " + spell.getName());
                        break;
                    }
                    character.getSpells().add(spell);
                }
                item = spell;
                break;
            case "Potions":
                ItemPotion potion = buyItemSelector(potions, character);
                if (potion != null) {
                    character.getInventory().addPotion(potion);
                }
                item = potion;
                break;
        }

        // sell if item was chosen
        if (item != null) {
            character.getCurrency().removeGold(item.getPrice());
        }
    }

    // generic method for choosing item to buy
    private <E extends Item> E buyItemSelector(List<E> items, Character character) {
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

    // sell method with menus
    private void sell(String category, Character character) {
        Item item = null;

        // select category, relevant actions to remove item from character
        switch (category) {
            case "Weapons":
                item = character.getEquipment().getMainHand();
                break;
            case "Armors":
                item = character.getEquipment().getArmor();
                break;
            case "Spells":
                item = Input.getInputWithMenuBack(character.getSpells(), true);
                if (item != null) {
                    character.getSpells().remove(item);
                }
                break;
            case "Potions":
                item = Input.getInputWithMenuBack(
                        new ArrayList<>(character.getInventory().getPotions().values()), true);
                if (item != null) {
                    character.getInventory().getPotion(item.getName());
                }
                break;
        }

        // sell item if valid
        if (item != null) {
            character.getCurrency().addGold(item.getPrice()/2);
        }
    }


}
