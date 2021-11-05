public class CharacterHeroSorcerer extends CharacterHero {
    public CharacterHeroSorcerer(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        super(name, mana, strength, agility, dexterity, money, experience, CharacterHeroType.SORCERER);
        getSpells().add(new Spell("Fire_Strike", 0, 1, 50, 100, SpellType.FIRE));
    }
}
