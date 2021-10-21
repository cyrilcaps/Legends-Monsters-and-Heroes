public class CharacterStats {
    private int strength;
    private int agility;
    private int dexterity;

    private double strengthGrowth = 1.05;
    private double agilityGrowth = 1.05;
    private double dexterityGrowth = 1.05;

    private int damage;
    private int dodgeChange;
    private int damageReduction;

    public CharacterStats() {
    }

    public CharacterStats(int strength, int agility, int dexterity, double strengthGrowth, double agilityGrowth, double dexterityGrowth) {
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.strengthGrowth = strengthGrowth;
        this.agilityGrowth = agilityGrowth;
        this.dexterityGrowth = dexterityGrowth;
    }

    public CharacterStats(boolean preferStrength, boolean preferAgility, boolean preferDexterity) {
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
        strength *= strengthGrowth;
        agility *= agilityGrowth;
        dexterity *= dexterityGrowth;
    }
}
