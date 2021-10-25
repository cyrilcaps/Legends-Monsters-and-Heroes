public class ActionCombat {
    ActionCombatType type;
    // effect object
    // target - list<players>


    public ActionCombat(ActionCombatType type) {
        this.type = type;
    }

    public ActionCombatType getType() {
        return type;
    }
}
