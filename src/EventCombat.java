import java.util.ArrayList;
import java.util.List;

public class EventCombat extends Event {
    private final List<Character> monsters;

    public EventCombat(List<Character> monsters) {
        this.monsters = monsters;
    }

    @Override
    public void enter(Party party) {
        List<Character> characters = new ArrayList<>(party.getHeroes().values());
        Combat combat = new Combat(characters, monsters);
    }
}
