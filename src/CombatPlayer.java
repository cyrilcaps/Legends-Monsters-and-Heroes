import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombatPlayer implements CombatBehavior {

    @Override
    public ActionCombat action(List<Character> targets, Character character) {
        ActionCombat action = new ActionCombat();

        List<ActionCombatType> attackTypes = Arrays.asList(ActionCombatType.values());
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
                    Spell spell = spellSelector(character.getSpells(), character.getStats().getMana());
                    if (spell == null) {
                        // go back to attack selection
                        continue;
                    }
                    action.setType(ActionCombatType.SPELL);
                    action.setDamage(spell.getDamage(character.getStats().getDexterity()));
                    action.setManaCost(spell.getManaCost());
                    break;
                case USE: // potion
                    action.setType(ActionCombatType.USE);
                    action.setTargetName(character.getName());
                    break;
                default:
                    // invalid input
                    System.out.println("Invalid attack option");
                    continue;
            }

            if (action.getTargetName() == null) {
                String target = targetSelector(targets);
                if (target == null) {
                    // go back to attack selection
                    continue;
                }
                action.setTargetName(target);
            }
            break;
        }
        character.getStats().useMana(action.getManaCost());
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

    // select target (monster) to attack, or input 0 to choose different action
    private String targetSelector(List<Character> targets) {
        List<Character> validTarget = targets.stream().filter(character ->
                !character.isFainted()).collect(Collectors.toList());
        Character target = Input.getInputWithMenuBack(validTarget, true);
        if (target == null) {
            return null;
        }
        return target.getName();
    }
}
