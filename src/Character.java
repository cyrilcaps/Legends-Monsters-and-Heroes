public abstract class Character {
    String name;
    CharacterLevel level;
    int health;
    int mana;
    CharacterCurrency currency;
    CharacterEquipment equipment;
    CharacterStats stats;

    public abstract Action action();

    public boolean isFainted() {
        return health <= 0;
    }
}
