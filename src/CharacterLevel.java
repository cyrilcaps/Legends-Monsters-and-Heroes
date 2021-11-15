public class CharacterLevel {
    private int level;
    private int experience;
    private int nextLevel;

    public CharacterLevel(int level, int experience) {
        this.level = level;
        this.experience = experience;
        //Check the next level before processing current experience and after
        //Prevents heroes from starting at level 2
        this.nextLevel = calculateNextLevel();
        processExperience();
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

    public int addExperience(int experience) {
        this.experience += experience;
        return processExperience();
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

    public int processExperience() {
        int levels = 0;
        while (canLevelUp()) {
            levelUp();
            levels += 1;
        }
        return levels;
    }

    @Override
    public String toString() {
        return "Lvl " + level + " (" + experience + "/" + nextLevel + " EXP)";
    }
}
