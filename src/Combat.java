import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combat extends Game {
    private final TurnBasedManager<Character> turnBasedManager = new TurnBasedManager<>();
    private final Map<String, Character> characterMap = new HashMap<>();

    private int round = 0;
    private final int maxRound;
    private boolean heroWin = false;

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

            System.out.println("Current player: " + character);

            // get action
            ActionCombat action = character.action(turnBasedManager.getNextTeam());
            if (!action.getType().equals(ActionCombatType.NONE)) {
                // get character, apply combat action
                System.out.println("Action! " + action);
                characterMap.get(action.getTargetName()).applyCombat(action);
            }
        } while (turnBasedManager.hasNext() && !determineWinner());

        int bounty = getMonsterBounty();
        for (Character hero : turnBasedManager.getTeam(0)) {
            // heal fainted hero to half of max
            if (hero.isFainted()) {
                hero.getStats().heal(hero.getStats().getMaxHealth() / 2);
            }

            // win = gold + exp, lose = lose half gold
            if (heroWin) {
                hero.endCombat(bounty, 2 * turnBasedManager.getTeam(1).size());
            } else {
                hero.endCombat();
            }
        }
    }

    // return true if a team is all fainted, set heroWin true if monsters fainted
    private boolean determineWinner() {
        // determine heroes all fainted
        boolean allFainted = true;
        for (Character hero : turnBasedManager.getTeam(0)) {
            if (!hero.isFainted()) {
                allFainted = false;
                break;
            }
        }
        if (allFainted) {

            return true;
        }

        // determine monsters all fainted
        allFainted = true;
        for (Character monster : turnBasedManager.getTeam(1)) {
            if (!monster.isFainted()) {
                allFainted = false;
                break;
            }
        }
        if (allFainted) {
            heroWin = true;
            return true;
        }
        return false;
    }

    // monster bounty = level * 100
    private int getMonsterBounty() {
        int bounty = 0;
        for (Character monster : turnBasedManager.getTeam(1)) {
            bounty += monster.getLevel().getLevel() * 100;
        }
        return bounty;
    }
}
