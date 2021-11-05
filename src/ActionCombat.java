public class ActionCombat {
    private ActionCombatType type;
    private int damage;
    private int manaCost;
    private String targetName;
    // effect object
    // target - list<players>


    public ActionCombat() {
    }

    public ActionCombat(ActionCombatType type, int damage, String targetName) {
        this.type = type;
        this.damage = damage;
        this.targetName = targetName;
    }

    public ActionCombatType getType() {
        return type;
    }

    public void setType(ActionCombatType type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
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