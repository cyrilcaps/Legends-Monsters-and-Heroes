import java.util.List;

public abstract class Character {
    private final String name;
    private final CharacterLevel level;
    private final CharacterCurrency currency;
    private final CharacterEquipment equipment;
    private final CharacterStats stats;
    private CombatBehavior combatBehavior = new CombatRandom();

    public Character(String name, CharacterLevel level, CharacterCurrency currency,
                     CharacterEquipment equipment, CharacterStats stats) {
        this.name = name;
        this.level = level;
        this.currency = currency;
        this.equipment = equipment;
        this.stats = stats;
    }

    /*
    Getter/Setter
     */

    public String getName() {
        return name;
    }

    public CharacterLevel getLevel() {
        return level;
    }

    public CharacterCurrency getCurrency() {
        return currency;
    }

    public CharacterEquipment getEquipment() {
        return equipment;
    }

    public CharacterStats getStats() {
        return stats;
    }

    /*
    Combat methods
     */

    public void setAttackBehavior(CombatBehavior combatBehavior) {
        this.combatBehavior = combatBehavior;
    }

    public ActionCombat action(List<Character> characters) {
        return combatBehavior.action(characters, this);
    }

    public abstract int getDamage();

    public boolean isFainted() {
        return getStats().health <= 0;
    }
}
