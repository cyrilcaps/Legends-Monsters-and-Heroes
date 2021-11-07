public class ItemPotion extends Item implements Consumable {
    private final CharacterStats stats;
    private int count = 1;

    public ItemPotion(String name, int levelRequirement, int price, CharacterStats stats) {
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
    public void consume(Character character) {
        // remove potion from inventory and apply stats to character
        character.getInventory().getPotion(getName());
        character.getStats().applyStat(stats);
        System.out.println(character.getName() + " drinks " + getName() + " and gains " + stats);
    }

    @Override
    public String toString() {
        return super.toString() + ", Count: " + count + ", Stats{" + stats + "}";
    }
}
