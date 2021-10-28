import java.util.Arrays;

public class CharacterHero extends Character {
    CharacterHeroType type;

    // Name/mana/strength/agility/dexterity/starting money/starting experience
    public CharacterHero(String name, int mana, int strength, int agility, int dexterity, int money, int experience, CharacterHeroType type) {
        super(name, new CharacterLevel(1, experience), new CharacterCurrency(money), null,
                new CharacterStats(strength, agility, dexterity, 0, 0, 0));
        this.type = type;
    }

    @Override
    public int getDamage() {
        int strength = getStats().getStrength();
        int weaponDamage = getEquipment().getMainHand().getDamage();
        return (int) ((strength + weaponDamage) * 0.05);
    }

    public void endCombat(int gold, int experience) {
        getCurrency().addGold(gold);

        int levels = getLevel().addExperience(experience);
        getStats().levelUp(levels);
    }

    @Override
    public String toString() {
        return getName() + " " + type + " " + getLevel() + " " + getCurrency() + " " + getStats();
    }
}
