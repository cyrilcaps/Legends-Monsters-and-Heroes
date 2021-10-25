import org.junit.jupiter.api.Test;

public class TestDataLoader {

    @Test
    public void monsterReader() {
        DataLoader.monsterReader();
        System.out.println(GlobalData.getMonsters().toString());
    }

    @Test
    public void heroReader() {
        DataLoader.heroReader();
        System.out.println(GlobalData.getHeroes().toString());
    }
}
