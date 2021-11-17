import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CharacterFactory {
    // when reading from file - Name/mana/strength/agility/dexterity/starting money/starting experience
    public static CharacterHero generateHero(CharacterHeroType type, String[] vals) {
        switch (type) {
            case WARRIOR:
                return new CharacterHeroWarrior(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]),
                        Integer.parseInt(vals[4]),
                        Integer.parseInt(vals[5]),
                        Integer.parseInt(vals[6]));
            case SORCERER:
                return new CharacterHeroSorcerer(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]),
                        Integer.parseInt(vals[4]),
                        Integer.parseInt(vals[5]),
                        Integer.parseInt(vals[6]));
            case PALADIN:
                return new CharacterHeroPaladin(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]),
                        Integer.parseInt(vals[4]),
                        Integer.parseInt(vals[5]),
                        Integer.parseInt(vals[6]));
        }
        return null;
    }

    // when reading from file - Name/level/damage/defense/dodge chance
    public static CharacterMonster generateMonster(CharacterMonsterType type, String[] vals) {
        switch (type) {
            case DRAGON:
                return new CharacterMonsterDragon(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]),
                        Integer.parseInt(vals[4]));
            case EXOSKELETON:
                return new CharacterMonsterExoskeleton(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]),
                        Integer.parseInt(vals[4]));
            case SPIRIT:
                return new CharacterMonsterSpirit(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]),
                        Integer.parseInt(vals[4]));
        }
        return null;
    }

    public static List<Character> generateMonster(int averageLevel, int numMonsters) {
        List<Character> monsters = new ArrayList<>();
        IntStream.range(0, numMonsters).forEach(i -> {
            while (true) {
                CharacterMonster monster = GlobalData.getRandom(GlobalData.getMonsters());
                if (monster.getLevel().getLevel() <= averageLevel) {
                    monsters.add(monster);
                    break;
                }
            }
        });
        return monsters;
    }
}
