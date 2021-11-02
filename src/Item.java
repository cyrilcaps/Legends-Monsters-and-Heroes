public class Item {
    private final String name;
    private final int levelRequirement;
    private final int price;

    public Item(String name, int levelRequirement, int price) {
        this.name = name;
        this.levelRequirement = levelRequirement;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getPrice() {
        return price;
    }
}
