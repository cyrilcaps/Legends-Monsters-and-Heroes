public class CharacterMonster extends Character {
    private final CharacterMonsterType type;

    public CharacterMonster(String name, int level, int damage, int defense, int dodgeChance, CharacterMonsterType type) {
        super(name, new CharacterLevel(level, 0), null, null,
                new CharacterStats(1, 1, 1, damage, defense, dodgeChance));
        this.type = type;
    }

    @Override
    public int getDamage() {
        return getStats().getDamage();
    }

    @Override
    public String toString() {
        return getName() + " " + type + " Lvl " + getLevel().getLevel() + " " + getStats();
    }
}
