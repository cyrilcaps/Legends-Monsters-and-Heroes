import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private final String name;
    private final CharacterLevel level;
    private final CharacterCurrency currency;
    private final CharacterEquipment equipment;
    private final CharacterInventory inventory = new CharacterInventory();
    private final List<Spell> spells = new ArrayList<>();
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

    public CharacterInventory getInventory() {
        return inventory;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public CharacterStats getStats() {
        return stats;
    }

    public void setAttackBehavior(CombatBehavior combatBehavior) {
        this.combatBehavior = combatBehavior;
    }

    /*
    abstract methods
     */

    public abstract int getDamage();
    public abstract void applyCombat(ActionCombat action);

    /*
    combat action based on combat behavior
     */

    public ActionCombat action(List<Character> characters) {
        if (isFainted()) {
            return new ActionCombat(ActionCombatType.NONE, 0, null);
        }
        return combatBehavior.action(characters, this);
    }

    /*
    helper methods
     */

    public boolean isFainted() {
        return getStats().getHealth() <= 0;
    }

    public String getCombatString() {
        return getName() + " " + getStats().getHealthString();
    }

    // add gold and process experience if alive, else heal to half
    public void endCombat(int gold, int experience) {
        if (!isFainted()) {
            getCurrency().addGold(gold);

            // add experience, if leveled up - update stats
            int levels = getLevel().addExperience(experience);
            getStats().levelUp(levels);
        }
    }

    public void endCombat() {
        getCurrency().removeGold(getCurrency().getGold()/2);
    }
}
