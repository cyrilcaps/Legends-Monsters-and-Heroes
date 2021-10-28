import java.util.List;

public interface CombatBehavior {
    public abstract ActionCombat action(List<Character> targets, Character character);
}
