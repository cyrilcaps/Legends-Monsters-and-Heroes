import java.util.*;

public class GlobalData {
    /*
    Load data from files
     */

    // split by type?
    public static final Map<String, CharacterHero> heroes = new HashMap<>();
    public static final Map<String, CharacterMonster> monsters = new HashMap<>();

    public static Map<String, CharacterHero> getHeroes() {
        return heroes;
    }

    public static Map<String, CharacterMonster> getMonsters() {
        return monsters;
    }

    public static CharacterHero getRandomHero() {
        List<String> names = new ArrayList<>(heroes.keySet());
        Collections.shuffle(names);
        return heroes.get(names.get(0));
    }
}
