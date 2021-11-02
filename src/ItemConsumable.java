public class ItemConsumable extends Item {
    private final CharacterStats stats;
    private int count = 1;

    public ItemConsumable(String name, int levelRequirement, int price, CharacterStats stats) {
        super(name, levelRequirement, price);
        this.stats = stats;
    }

    public CharacterStats getStats() {
        return stats;
    }

    public int getCount() {
        return count;
    }

    public void addCount(int add) {
        count += add;
    }

    @Override
    public String toString() {
        return "ItemConsumable{" +
                "stats=" + stats +
                '}';
    }
}
