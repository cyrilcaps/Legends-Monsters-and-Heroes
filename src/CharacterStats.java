import java.util.*;

public class CharacterStats {
    private int health = 100;
    private int maxHealth = 100;
    private int mana;
    private int maxMana;

    private int strength;
    private int agility;
    private int dexterity;

    private double strengthGrowth = 1.05;
    private double agilityGrowth = 1.05;
    private double dexterityGrowth = 1.05;

    private int damage;
    private double dodgeChance;
    private int damageReduction;

    private final Map<String, CharacterStats> buffs = new HashMap<>();

    public CharacterStats() {
    }

    public CharacterStats(int strength, int agility, int dexterity, int damage, double dodgeChance, int damageReduction) {
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

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
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

    public void applyBuff(String buffName, CharacterStats buff) {
        heal(buff.getHealth());
        addMana(buff.getMana());
        strength += buff.getStrength();
        agility += buff.getAgility();
        dexterity += buff.getDexterity();
        processSecondaryStats();
        buffs.put(buffName, buff);
    }

    public void removeBuffs() {
        for (CharacterStats buff : buffs.values()) {
            strength -= buff.getStrength();
            agility -= buff.getAgility();
            dexterity -= buff.getDexterity();
        }
        processSecondaryStats();
        buffs.clear();
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

    public void processSecondaryStats() {
        damage = (int) (strength * 0.05);
        dodgeChance = agility * 0.002;
        damageReduction = (int) (dexterity * 0.05);
    }

    public void takeDamage(int damage) {
        health = Math.max(health - damage, 0);
    }

    public void heal(int heal) {
        health = Math.min(health + heal, maxHealth);
    }

    public void addMana(int recover) {
        mana = Math.min(mana + recover, maxMana);
    }

    public boolean useMana(int manaCost) {
        if (manaCost > mana) {
            return false;
        }
        mana -= manaCost;
        return true;
    }

    public String getHealthString() {
        return getHealth() + "/" + getMaxHealth() + " HP";
    }

    @Override
    public String toString() {
        return "CharacterStats:{" +
                health + "/" + maxHealth + " HP, " +
                mana + "/" + maxMana + " MP, " +
                strength + " STR, " +
                agility + " AGI, " +
                dexterity + " DEX, " +
                damage + " DMG, " +
                dodgeChance + "% DGE, " +
                damageReduction + " RED" +
                '}';
    }
}
