public class CharacterCurrency {
    int gold;

    public CharacterCurrency() {
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
}
