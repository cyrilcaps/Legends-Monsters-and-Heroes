import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EventFactory {
    public static Event generateEvent(MapSquareType type, List<Character> heroes) {
        switch (type) {
            case PLAIN:
                return commonGenerator(heroes);
            case HERONEXUS:
                return marketGenerator();
            case INACCESSIBLE:
            default:
                break;
        }
        return null;
    }

    // 50-50 chance of combat or nothing
    private static Event commonGenerator(List<Character> heroes) {
        int averageLevel = heroes.stream().mapToInt(c -> c.getLevel().getLevel()).sum() / heroes.size();
        if (Math.random() <= 0.5) {
            // return new combat
            List<Character> monsters = new ArrayList<>();
            IntStream.range(0, heroes.size()).forEach(i -> {
                while (true) {
                    CharacterMonster monster = GlobalData.getRandom(GlobalData.getMonsters());
                    if (monster.getLevel().getLevel() <= averageLevel) {
                        monsters.add(monster);
                        break;
                    }
                }
            });
            return new EventCombat(monsters);
        }
        return null;
    }

    // create shop - modified to include all available items
    private static Event marketGenerator() {
        List<ItemWeapon> weapons = new ArrayList<>();
        List<ItemArmor> armors = new ArrayList<>();
        List<Spell> spells = new ArrayList<>();
        List<ItemPotion> potions = new ArrayList<>();
//        armors.add(GlobalData.getRandom(GlobalData.getArmors()));
//        spells.add(GlobalData.getRandom(GlobalData.getSpells()));
//        potions.add(GlobalData.getRandom(GlobalData.getPotions()));
        List<String> weaponNames = new ArrayList<>(GlobalData.getWeapons().keySet());
        List<String> armorNames = new ArrayList<>(GlobalData.getArmors().keySet());
        List<String> spellNames = new ArrayList<>(GlobalData.getSpells().keySet());
        List<String> potionNames = new ArrayList<>(GlobalData.getPotions().keySet());

        IntStream.range(0, GlobalData.getWeapons().size()).forEach(i -> weapons.add(GlobalData.getWeapons().get(weaponNames.get(i))));
        IntStream.range(0, GlobalData.getArmors().size()).forEach(i -> armors.add(GlobalData.getArmors().get(armorNames.get(i))));
        IntStream.range(0, GlobalData.getSpells().size()).forEach(i -> spells.add(GlobalData.getSpells().get(spellNames.get(i))));
        IntStream.range(0, GlobalData.getPotions().size()).forEach(i -> potions.add(GlobalData.getPotions().get(potionNames.get(i))));

        return new EventShop(weapons, armors, spells, potions);
    }
}
