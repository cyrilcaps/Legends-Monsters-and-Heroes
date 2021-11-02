import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EventFactory {
    public static Event generateEvent(MapSquareType type) {
        switch (type) {
            case COMMON:
                return commonGenerator();
            case MARKET:
                return marketGenerator();
            case INACCESSIBLE:
            default:
                break;
        }
        return null;
    }

    // 50-50 chance of combat or nothing
    private static Event commonGenerator() {
        if (Math.random() <= 0.5) {
            // return new combat
            List<Character> monsters = new ArrayList<>();
            monsters.add(GlobalData.getRandom(GlobalData.getMonsters()));
            monsters.add(GlobalData.getRandom(GlobalData.getMonsters()));
            monsters.add(GlobalData.getRandom(GlobalData.getMonsters()));
            return new EventCombat(monsters);
        }
        return null;
    }

    // create shop with 3 random items
    private static Event marketGenerator() {
        List<ItemWeapon> weapons = new ArrayList<>();
        List<ItemArmor> armors = new ArrayList<>();
        List<Spell> spells = new ArrayList<>();
        List<ItemConsumable> potions = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> {
            weapons.add(GlobalData.getRandom(GlobalData.getWeapons()));
            armors.add(GlobalData.getRandom(GlobalData.getArmors()));
            spells.add(GlobalData.getRandom(GlobalData.getSpells()));
            potions.add(GlobalData.getRandom(GlobalData.getPotions()));

        });
        return new EventShop(weapons, armors, spells, potions);
    }
}
