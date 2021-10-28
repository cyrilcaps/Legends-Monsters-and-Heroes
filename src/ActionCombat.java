public class ActionCombat {
    private ActionCombatType type;
    private int damage;
    private String targetName;
    // effect object
    // target - list<players>

    public ActionCombat(ActionCombatType type, int damage, String targetName) {
        this.type = type;
        this.damage = damage;
        this.targetName = targetName;
    }

    public int getDamage() {
        return damage;
    }

    public String getTargetName() {
        return targetName;
    }

    public ActionCombatType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ActionCombat{" +
                "type=" + type +
                ", damage=" + damage +
                ", targetName='" + targetName + '\'' +
                '}';
    }
}
