public class CharacterHero extends Character {
    @Override
    public Action action() {
        return new Action();
    }

    public void endCombat(int gold, int experience) {
        currency.addGold(gold);

        level.addExperience(experience);
        while (level.canLevelUp()) {
            level.levelUp();
            stats.levelUp();
        }
    }
}
