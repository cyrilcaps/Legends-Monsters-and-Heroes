public class CharacterCurrency {
    int gold;

    public CharacterCurrency(int gold) {
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public boolean removeGold(int gold) {
        if (gold > this.gold) {
            return false;
        }
        this.gold -= gold;
        return true;
    }

    @Override
    public String toString() {
        return gold + "G";
    }
}
