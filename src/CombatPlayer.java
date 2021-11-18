import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombatPlayer implements CombatBehavior {

    @Override
    public ActionCombat action(List<Character> targets, Character character) {
        ActionCombat action = new ActionCombat();

        while (true) {
            ActionCombatType attackTypeSelect = Input.getInputWithMenuBack(
                    Arrays.asList(ActionCombatType.ATTACK, ActionCombatType.SPELL, ActionCombatType.USE), false);
            if (attackTypeSelect == null) {
                attackTypeSelect = ActionCombatType.NONE;
            }
            switch (attackTypeSelect) {
                case ATTACK: // attack
                    action.setType(ActionCombatType.ATTACK);
                    action.setDamage(character.getDamage());
                    break;
                case SPELL: // spell
                    ActionCombat spellAction = castSpell(targets, character);
                    if (spellAction == null) {
                        // go back to attack selection
                        continue;
                    }
                    return spellAction;
                case USE: // potion
                    action.setType(ActionCombatType.USE);
                    ItemPotion potion = potionSelector(character);
                    if (potion == null) {
                        continue;
                    }
                    potion.consume(character);
                    return action;
                default:
                    // invalid input
                    System.out.println("Invalid attack option");
                    continue;
            }

            // determine target
            if (action.getTargetName() == null) {
                Character target = targetSelector(targets);
                if (target == null) {
                    // go back to attack selection
                    continue;
                }
                action.setTargetName(target.getName());
            }
            break;
        }
        return action;
    }

    public ActionCombat castSpell(List<Character> targets, Character character) {
        ActionCombat action = new ActionCombat();
        Spell spell = spellSelector(character.getSpells(), character.getStats().getMana());
        if (spell == null) {
            // go back to attack selection
            return null;
        }

        // select target
        Character target = targetSelector(targets);
        if (target == null) {
            // go back to attack selection
            return null;
        }

        // populate action
        action.setType(ActionCombatType.SPELL);
        action.setDamage(spell.getDamage(character.getStats().getDexterity()));
        action.setManaCost(spell.getManaCost());
        action.setTargetName(target.getName());
        action.setTarget(target);

        // cast spell on target
        spell.cast(character, target);
        return action;
    }

    // select spell if available and sufficient mana, or input 0 to choose different action
    private Spell spellSelector(List<Spell> spells, int mana) {
        if (spells.isEmpty()) {
            return null;
        }

        Spell spell;
        while (true) {
            spell = Input.getInputWithMenuBack(spells, true);
            if (spell == null) {
                return null;
            }

            if (spell.getManaCost() > mana) {
                System.out.println("Insufficient mana");
                continue;
            }
            break;
        }
        return spell;
    }

    // select potion to use from inventory
    private ItemPotion potionSelector(Character character) {
        return Input.getInputWithMenuBack(
                new ArrayList<>(character.getInventory().getPotions().values()), true);
    }

    // select target (monster) to attack, or input 0 to choose different action
    public static Character targetSelector(List<Character> targets) {
        List<Character> validTarget = targets.stream().filter(character ->
                !character.isFainted()).collect(Collectors.toList());
        return Input.getInputWithMenuBack(validTarget, true);
    }
}
