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

    private boolean freeze;
    private boolean burn;
    private boolean electrocute;

    private boolean inBush;
    private boolean inCave;
    private boolean inKoulou;

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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
        return inKoulou ? (int)(strength*1.1) : strength;
    }

    public int getAgility() { 
        return inCave ? (int)(agility * 1.1) : agility;
    }

    public int getDexterity() { 
        return inBush ? (int)(dexterity * 1.1 ) : dexterity;
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
        return electrocute ? dodgeChance * 0.9 : dodgeChance;
    }

    public int getDamageReduction() {
        return burn ? (int) (damageReduction * 0.9) : damageReduction;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public boolean isBurn() {
        return burn;
    }

    public boolean isElectrocute() {
        return electrocute;
    }

    /*
    status effects
     */

    public void spellDebuff(SpellType type) {
        switch (type) {
            case ICE:
                freeze = true;
                break;
            case FIRE:
                burn = true;
                break;
            case LIGHTNING:
                electrocute = true;
                break;
        }
    }

    public void removeDebuffs() {
        freeze = false;
        burn = false;
        electrocute = false;
    }

    public void cellBuff(MapSquareType cell){
        switch(cell){
            case BUSH:
                inBush = true;
                break;
            case CAVE:
                inCave = true;
                break;
            case KOULOU:
                inKoulou = true;
                break;
            default: 
                break;

        }
    }

    //remove buff that's gained from different cell type
    public void removeBuffs(){
        inBush = false;
        inCave = false;
        inKoulou = false;
    }

    // preferences for stat growth
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

    // determine stats on level up
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

    // process stats based on strength, agility, dexterity
    public void processSecondaryStats() {
        damage = (int) (strength * 0.05);
        dodgeChance = agility * 0.002;
        damageReduction = (int) (dexterity * 0.05);
    }

    public void takeDamage(int damage) {
        health = Math.max(health - damage, 0);
    }

    public void addHealth(int heal) {
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

    // add other CharacterStats object to this object
    public void applyStat(CharacterStats otherStats) {
        addHealth(otherStats.getHealth());
        addMana(otherStats.getMana());
        strength += otherStats.getStrength();
        dexterity += otherStats.getDexterity();
        agility += otherStats.getAgility();

        // determine new values for secondary stats
        processSecondaryStats();
    }

    public String getHealthString() {
        return getHealth() + "/" + getMaxHealth() + " HP";
    }

    public String getStatusString() {
        StringBuilder statuses = new StringBuilder("[");
        if (burn) {
            statuses.append(Util.colorString(UtilPrintColors.RED, "BURN")).append(",");
        }
        if (freeze) {
            statuses.append(Util.colorString(UtilPrintColors.CYAN, "FREEZE")).append(",");
        }
        if (electrocute) {
            statuses.append(Util.colorString(UtilPrintColors.YELLOW, "ELECTROCUTE")).append(",");
        }
        if (statuses.length() > 1) {
            statuses.replace(statuses.length() - 1, statuses.length(), "");
        }
        statuses.append("]");
        return statuses.toString();
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
                damageReduction + " RED, " +
                "statuses:" + getStatusString() +
                '}';
    }
}
