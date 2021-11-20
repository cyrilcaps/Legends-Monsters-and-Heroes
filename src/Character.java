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

    //Needed for a deep copy?
    public Character(Character character) {
        this.name = character.getName();
        this.level = character.getLevel();
        this.currency = character.getCurrency();
        this.equipment = character.getEquipment();
        this.stats = character.getStats();
        this.combatBehavior = character.combatBehavior;
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
            return new ActionCombat(ActionCombatType.NONE, 0, (String) null);
        }
        return combatBehavior.action(characters, this);
    }

    /*
    helper methods
     */

    public boolean isFainted() {
        return getStats().getHealth() <= 0;
    }

    // add gold and process experience if alive, else heal to half
    public void endCombat(int gold, int experience) {
        if (!isFainted()) {
            System.out.println(getName() + " gained " + gold + "G and " + experience + " EXP!");
            getCurrency().addGold(gold);

            // add experience, if leveled up - update stats
            int levels = getLevel().addExperience(experience);
            if (levels > 0) {
                System.out.println("\tGained " + levels + " levels!");
            }
            getStats().levelUp(levels);
        }
    }

    // remove gold on faint
    public void endCombat() {
        System.out.println(getName() + " lost " + getCurrency().getGold()/2  + "G");
        getCurrency().removeGold(getCurrency().getGold()/2);
    }
}
