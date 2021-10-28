public class ItemConsumable extends Item {
    CharacterStats stats;

    public ItemConsumable(String name, int levelRequirement, int price, CharacterStats stats) {
        super(name, levelRequirement, price);
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "ItemConsumable{" +
                "stats=" + stats +
                '}';
    }
}
