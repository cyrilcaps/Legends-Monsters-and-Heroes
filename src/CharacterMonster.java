public class CharacterMonster extends Character {
    private final CharacterMonsterType type;

    public CharacterMonster(String name, int level, int damage, int defense, int dodgeChance, CharacterMonsterType type) {
        super(name, new CharacterLevel(level, 0), null, null,
                new CharacterStats(1, 1, 1, damage, dodgeChance * 0.01, defense));
        this.type = type;
    }

    @Override
    public int getDamage() {
        return getStats().getDamage();
    }

    @Override
    public void applyCombat(ActionCombat action) {
        if (Math.random() < (getStats().getDodgeChance())) {
            System.out.println(getName() + " dodged the attack!");
            return;
        }
        getStats().takeDamage(action.getDamage());
    }

    @Override
    public String toString() {
        return getName() + " " + type + " Lvl " + getLevel().getLevel() + " " + getStats();
    }
}
