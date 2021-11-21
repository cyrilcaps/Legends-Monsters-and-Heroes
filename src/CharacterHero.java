public class CharacterHero extends Character {
    CharacterHeroType type;

    // Name/mana/strength/agility/dexterity/starting money/starting experience
    protected CharacterHero(String name, int mana, int strength, int agility, int dexterity, int money, int experience, CharacterHeroType type) {
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

        // starting weapon
        (new ItemWeapon("Sword", 0, 1, 500, 1)).equip(this);
    }

    public CharacterHeroType getType() {
        return type;
    }

    @Override
    public int getDamage() {
        // damage = (strength + weapon damage) * 0.05
        int strength = getStats().getStrength();
        int weaponDamage = 0;
        if (getEquipment().getMainHand() != null) {
            weaponDamage = getEquipment().getMainHand().getDamage();
        }
        int totalDamage = (int) ((strength + weaponDamage) * 0.05);
        if (getStats().isFreeze()) {
            totalDamage = (int) (totalDamage * 0.9);
        }
        return totalDamage;
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

            // calculate damage taken = damage - damage reduction - armor damage reduction
            int armor = getEquipment().getArmor() != null ? getEquipment().getArmor().getDamageReduction() : 0;
            int damage = Math.max(action.getDamage() - getStats().getDamageReduction() - armor, 0);
            getStats().takeDamage(damage);
            System.out.println(getName() + " received " + damage + " damage");
            if (isFainted()) {
                System.out.println(getName() + " fainted!");
                Input.getString("Press any key to continue...");
            }
        }
    }

    // health and mana recovery per round
    public void recover() {
        getStats().addMana((int) (getStats().getMaxMana() * 0.1));
        getStats().addHealth((int) (getStats().getMaxHealth() * 0.1));
    }

    @Override
    public String toString() {
        return Util.colorString(type.getStringColor(), getName() + " " + type.name() + " " + getLevel()) + " " + getCurrency() + " " + getStats() +
                "\n\t" + getEquipment() +
                "\n\tSpells:" + getSpells() +
                "\n\t" + getInventory();
    }
}
