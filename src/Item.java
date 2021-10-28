public class Item {
    private String name;
    private int levelRequirement;
    private int price;

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
