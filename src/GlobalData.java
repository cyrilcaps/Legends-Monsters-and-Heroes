import java.util.*;

public class GlobalData {
    /*
    Load data from files
     */

    // split by type?
    private static final Map<String, CharacterHero> heroes = new HashMap<>();

    private static final Map<String, CharacterMonster> monsters = new HashMap<>();

    private static final Map<String, Spell> spells = new HashMap<>();

    private static final Map<String, ItemArmor> armors = new HashMap<>();

    private static final Map<String, ItemWeapon> weapons = new HashMap<>();

    private static final Map<String, ItemConsumable> potions = new HashMap<>();

    public static Map<String, CharacterHero> getHeroes() {
        return heroes;
    }

    public static Map<String, CharacterMonster> getMonsters() {
        return monsters;
    }

    public static Map<String, Spell> getSpells() {
        return spells;
    }

    public static Map<String, ItemArmor> getArmors() {
        return armors;
    }

    public static Map<String, ItemWeapon> getWeapons() {
        return weapons;
    }

    public static Map<String, ItemConsumable> getPotions() {
        return potions;
    }

    public static <T> T getRandom (Map<String, T> map) {
        List<String> names = new ArrayList<>(map.keySet());
        Collections.shuffle(names);
        return map.get(names.get(0));
    }
}
