public class CharacterHero extends Character {
    CharacterHeroType type;

    // Name/mana/strength/agility/dexterity/starting money/starting experience
    public CharacterHero(String name, int mana, int strength, int agility, int dexterity, int money, int experience, CharacterHeroType type) {
        super(name, new CharacterLevel(1, experience), new CharacterCurrency(money), new CharacterEquipment(),
                new CharacterStats(strength, agility, dexterity, 0, 0, 0));
        this.type = type;
        // set stat preference for leveling
        switch (type) {
            case WARRIOR:
                getStats().setPreferences(true, true, false);
                break;
            case SORCERER:
                getStats().setPreferences(false, true, true);
                break;
            case PALADIN:
                getStats().setPreferences(true, false, true);
                break;
        }
        getStats().setMana(mana);
        getStats().setMaxMana(mana);
        getStats().processSecondaryStats();
        setAttackBehavior(new CombatPlayer());
    }

    @Override
    public int getDamage() {
        int strength = getStats().getStrength();
        int weaponDamage = getEquipment().getMainHand().getDamage();
        return (int) ((strength + weaponDamage) * 0.05);
    }

    @Override
    public void applyCombat(ActionCombat action) {
        // determine combat action handling
        if (action.getType() != ActionCombatType.USE) {
            // check dodge attack
            if (Math.random() < getStats().getDodgeChance() / 100) {
                System.out.println(getName() + " dodged the attack!");
                return;
            }
            // calculate damage
            int damage = Math.max(action.getDamage() - getStats().getDamageReduction(), 0);
            getStats().takeDamage(damage);
            System.out.println(getName() + " received " + damage + " damage");
            if (isFainted()) {
                System.out.println(getName() + " fainted!");
            }
        }
        // TODO: determine potion handling
    }

    // health and mana recovery per round
    public void recover() {
        getStats().addMana((int) (getStats().getMaxMana() * 0.1));
        getStats().heal((int) (getStats().getMaxHealth() * 0.1));
    }

    @Override
    public String toString() {
        return getName() + " " + type + " " + getLevel() + " " + getCurrency() + " " + getStats() +
                "\n\t" + getEquipment() +
                "\n\t" + getInventory();
    }
}
