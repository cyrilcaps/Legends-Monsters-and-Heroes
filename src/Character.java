public abstract class Character {
    private final String name;
    private final CharacterLevel level;
    private final CharacterCurrency currency;
    private final CharacterEquipment equipment;
    private final CharacterStats stats;

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
    public boolean isFainted() {
        return getStats().health <= 0;
    }
}
