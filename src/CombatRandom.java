import java.util.List;

public class CombatRandom implements CombatBehavior {

    @Override
    public ActionCombat action(List<Character> targets, Character character) {
        int damage = character.getStats().getDamage();
        return new ActionCombat(ActionCombatType.ATTACK, damage, targets.get(0).getName());
    }
}
