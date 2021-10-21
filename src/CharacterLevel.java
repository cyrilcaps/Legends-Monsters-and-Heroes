public class CharacterLevel {
    private int level;
    private int experience;
    private int nextLevel;

    public CharacterLevel(int level, int experience) {
        this.level = level;
        this.experience = experience;
        this.nextLevel = calculateNextLevel();
    }

    public CharacterLevel() {
        this(1, 0);
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getNextLevel() {
        return nextLevel;
    }

    public void addExperience(int experience) {
        this.experience += experience;
        levelUp();
    }

    private int calculateNextLevel() {
        return level * 10;
    }

    public boolean canLevelUp() {
        return experience >= nextLevel;
    }

    public void levelUp() {
        level += 1;
        experience -= nextLevel;
        nextLevel = nextLevel + calculateNextLevel();
    }
}
