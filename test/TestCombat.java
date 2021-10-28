import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TestCombat {
    @BeforeAll
    static void setUp() {
        DataLoader.loadAll();
    }

    @Test
    public void testAttackRandom() {
        CharacterMonster monster = GlobalData.getRandom(GlobalData.getMonsters());
        CharacterHero hero = GlobalData.getRandom(GlobalData.getHeroes());
        ActionCombat combat = monster.action(Collections.singletonList(hero));
        Assertions.assertEquals(monster.getStats().getDamage(), combat.getDamage());
        Assertions.assertEquals(hero.getName(), combat.getTargetName());
    }

    @Test
    public void testAttackRandomMulti() {
        CharacterMonster monster = GlobalData.getRandom(GlobalData.getMonsters());
        List<Character> heroes = new ArrayList<>();
        IntStream.range(0, 3).forEach(x -> {heroes.add(GlobalData.getRandom(GlobalData.getHeroes()));});
        ActionCombat combat = monster.action(heroes);
        Assertions.assertEquals(monster.getStats().getDamage(), combat.getDamage());
        System.out.println(combat);
    }
}
