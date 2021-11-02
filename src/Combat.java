import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combat extends Game {
    private final TurnBasedManager<Character> turnBasedManager = new TurnBasedManager<>();
    private final Map<String, Character> characterMap = new HashMap<>();

    private int round = 0;
    private final int maxRound;

    public Combat(List<Character> heroes, List<Character> monsters) {
        turnBasedManager.addTeam(heroes);
        turnBasedManager.addTeam(monsters);

        heroes.forEach(character -> {characterMap.put(character.getName(), character);});
        monsters.forEach(character -> {characterMap.put(character.getName(), character);});

        this.maxRound = heroes.size() + monsters.size();
    }

    @Override
    public void play() {
        do {
            // heroes recover some health/mana at end of round
            if (round == maxRound) {
                round = 0;
                turnBasedManager.getTeam(0).forEach(character -> {
                    if (!character.isFainted()) {
                        ((CharacterHero) character).recover();
                    }
                });
            }

            // get next character
            Character character = turnBasedManager.next();
            round += 1;
            if (character.isFainted()) {
                continue;
            }

            // get action
            ActionCombat action = character.action(turnBasedManager.getNextTeam());
            if (!action.getType().equals(ActionCombatType.NONE)) {
                // get character, apply combat action
                characterMap.get(action.getTargetName()).applyCombat(action);
            }

            boolean allFainted = true;
            for (Character hero : turnBasedManager.getTeam(0)) {
                if (!hero.isFainted()) {
                    allFainted = false;
                    break;
                }
            }
        } while (turnBasedManager.hasNext());
    }
}
