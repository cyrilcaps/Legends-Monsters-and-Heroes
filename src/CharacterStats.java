public class CharacterStats {
    int health = 100;
    int maxHealth = 100;
    int mana;
    int maxMana;

    private int strength;
    private int agility;
    private int dexterity;

    private double strengthGrowth = 1.05;
    private double agilityGrowth = 1.05;
    private double dexterityGrowth = 1.05;

    private int damage;
    private double dodgeChance;
    private int damageReduction;

    public CharacterStats() {
    }

    public CharacterStats(int strength, int agility, int dexterity, int damage, int dodgeChance, int damageReduction) {
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.damage = damage;
        this.dodgeChance = dodgeChance;
        this.damageReduction = damageReduction;

        processSecondaryStats();
    }

    public void setPreferences(boolean preferStrength, boolean preferAgility, boolean preferDexterity) {
        if (preferStrength) {
            this.strengthGrowth = 1.1;
        }
        if (preferAgility) {
            this.agilityGrowth = 1.1;
        }
        if (preferDexterity) {
            this.dexterityGrowth = 1.1;
        }
    }

    public void levelUp() {
        levelUp(1);
    }

    public void levelUp(int levels) {
        if (levels > 0) {
            maxHealth += 100*levels;
            maxMana *= Math.pow(1.1, levels);
            health = maxHealth;
            mana = maxMana;

            strength *= Math.pow(strengthGrowth, levels);
            agility *= Math.pow(agilityGrowth, levels);
            dexterity *= Math.pow(dexterityGrowth, levels);
        }
    }

    private void processSecondaryStats() {
        damage = (int) (strength * 0.05);
        dodgeChance = agility * 0.002;
        damageReduction = (int) (dexterity * 0.05);
    }

    @Override
    public String toString() {
        return "CharacterStats:{" +
                "health=" + health +
                ", mana=" + mana +
                ", strength=" + strength +
                ", agility=" + agility +
                ", dexterity=" + dexterity +
                ", damage=" + damage +
                ", dodgeChange=" + dodgeChance +
                ", damageReduction=" + damageReduction +
                '}';
    }
}
