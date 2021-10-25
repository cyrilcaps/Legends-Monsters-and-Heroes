import java.util.Arrays;

public class CharacterHero extends Character {
    CharacterHeroType type;

    // Name/mana/strength/agility/dexterity/starting money/starting experience
    public CharacterHero(String name, int mana, int strength, int agility, int dexterity, int money, int experience, CharacterHeroType type) {
        super(name, new CharacterLevel(1, experience), new CharacterCurrency(money), null,
                new CharacterStats(strength, agility, dexterity, 0, 0, 0));
        this.type = type;
    }

    public ActionCombat action() {
        while (true) {
            /*
                1 - attack with weapon (enemy)
                2 - open spell selector, attack with spell (enemy)
                3 - open consumable selector, use consumable (self)
             */
            int input = Input.getIntWithMenu(Arrays.asList("ATTACK", "SKILL", "USE"), 1);
            switch (input) {
                case (1):
                    return new ActionCombat(ActionCombatType.ATTACK);
                case (2):
                    // skill selector
                    return new ActionCombat(ActionCombatType.SPELL);
                case (3):
                    // consumable selector
                    return new ActionCombat(ActionCombatType.USE);
            }
        }
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
