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
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public double getStrengthGrowth() {
        return strengthGrowth;
    }

    public double getAgilityGrowth() {
        return agilityGrowth;
    }

    public double getDexterityGrowth() {
        return dexterityGrowth;
    }

    public int getDamage() {
        return damage;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public int getDamageReduction() {
        return damageReduction;
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

            processSecondaryStats();
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
