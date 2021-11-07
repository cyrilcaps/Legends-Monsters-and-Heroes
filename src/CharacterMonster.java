public class CharacterMonster extends Character {
    private final CharacterMonsterType type;

    protected CharacterMonster(String name, int level, int damage, int defense, int dodgeChance, CharacterMonsterType type) {
        super(name, new CharacterLevel(level, 0), null, null,
                new CharacterStats(1, 1, 1, damage/10, dodgeChance * 0.01, defense/10));
        this.type = type;
    }

    @Override
    public int getDamage() {
        int totalDamage = getStats().getDamage();
        if (getStats().isFreeze()) {
            totalDamage = (int) (totalDamage * 0.9);
        }
        return totalDamage;
    }

    @Override
    public void applyCombat(ActionCombat action) {
        if (Math.random() < (getStats().getDodgeChance())) {
            System.out.println(getName() + " dodged the attack!");
            return;
        }
        int damage = Math.max(action.getDamage() - getStats().getDamageReduction(), 0);
        System.out.println(getName() + " received " + damage + " damage");
        getStats().takeDamage(damage);
    }

    public String getMonsterName() {
        return Util.colorString(type.getStringColor(),
                getName() + " " + type.name() + " Lvl " + getLevel().getLevel());
    }

    @Override
    public String toString() {
         return getMonsterName() + " - " + getStats().getHealthString() + " status:" + getStats().getStatusString();
    }
}
