import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestConsole {
    public static void main(String[] args) {
        DataLoader.loadAll();

        marketTest();
    }

    public static void heroCombat() {
        CharacterMonster monster = GlobalData.getRandom(GlobalData.getMonsters());
        CharacterHero hero = GlobalData.getRandom(GlobalData.getHeroes(CharacterHeroType.WARRIOR));
        hero.getSpells().add(GlobalData.getRandom(GlobalData.getSpells()));
        System.out.println(hero);
        System.out.println(monster);
        ActionCombat combat = hero.action(Collections.singletonList(monster));
        System.out.println(combat);
        monster.applyCombat(combat);
        System.out.println(monster);
    }

    public static void heroCombatInvalid() {
        List<Character> monsterList = new ArrayList<>();
        CharacterMonster monster = GlobalData.getRandom(GlobalData.getMonsters());
        monster.getStats().setHealth(0);
        monsterList.add(monster);
        monsterList.add(GlobalData.getRandom(GlobalData.getMonsters()));
        CharacterHero hero = GlobalData.getRandom(GlobalData.getHeroes(CharacterHeroType.WARRIOR));
        hero.getSpells().add(GlobalData.getRandom(GlobalData.getSpells()));
        System.out.println(monsterList);
        ActionCombat combat = hero.action(monsterList);
        monster.applyCombat(combat);
    }

    public static void marketTest() {
        Event market = EventFactory.generateEvent(MapSquareType.MARKET);
        Party party = new Party("S", UtilPrintColors.RED);
        party.addHero(GlobalData.getRandom(GlobalData.getHeroes(CharacterHeroType.WARRIOR)));
        market.enter(party);
    }
}
