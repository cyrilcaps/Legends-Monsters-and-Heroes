import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestDataLoader {
    @BeforeAll
    static void setUp() {
        DataLoader.loadAll();
    }

    @Test
    public void monsterReader() {
        System.out.println(GlobalData.getMonsters().toString());
    }

    @Test
    public void heroReader() {
        System.out.println(GlobalData.getHeroes(CharacterHeroType.WARRIOR).toString());
    }

    @Test
    public void potionReader() {
        System.out.println(GlobalData.getPotions().toString());
    }

    @Test
    public void spellReader() {
        System.out.println(GlobalData.getSpells().toString());
    }
}
