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

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Item) {
                return ((Item) o).getName().equals(getName());
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name  + " - Lvl Req.: " + levelRequirement +
                ", price: " + price + "G";
    }
}
