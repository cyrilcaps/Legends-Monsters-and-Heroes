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

        heroes.forEach(character -> characterMap.put(character.getName(), character));
        monsters.forEach(character -> characterMap.put(character.getName(), character));

        this.maxRound = heroes.size() + monsters.size();
    }

    public boolean isHeroWin() {
        return heroWin;
    }

    private List<Character> getHeroes() {
        return turnBasedManager.getTeam(0);
    }

    private List<Character> getMonsters() {
        return turnBasedManager.getTeam(1);
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

            System.out.println(Util.colorString(UtilPrintColors.RED_BOLD_BRIGHT,"*****************************"));
            System.out.println("Current player: " + character);

            // get action
            ActionCombat action = character.action(turnBasedManager.getNextTeam());
            if (action.getType().equals(ActionCombatType.ATTACK)) {
                // get character, apply combat action
                System.out.println(character.getName() + " attacks " + action.getTargetName());
                characterMap.get(action.getTargetName()).applyCombat(action);
            }
        } while (turnBasedManager.hasNext() && !determineWinner());

        // resolve end of combat
        int bounty = getMonsterBounty();
        for (Character hero : getHeroes()) {
            // remove any debuffs
            hero.getStats().removeDebuffs();

            // heal fainted hero to half of max
            if (hero.isFainted()) {
                hero.getStats().addHealth(hero.getStats().getMaxHealth() / 2);
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
        for (Character hero : getHeroes()) {
            if (!hero.isFainted()) {
                allFainted = false;
                break;
            }
        }
        if (allFainted) {
            System.out.println(Util.colorString(UtilPrintColors.RED_BOLD, "Monsters are victorious"));
            return true;
        }

        // determine monsters all fainted
        allFainted = true;
        for (Character monster : getMonsters()) {
            if (!monster.isFainted()) {
                allFainted = false;
                break;
            }
        }
        if (allFainted) {
            System.out.println(Util.colorString(UtilPrintColors.YELLOW_BOLD, "Heroes are victorious"));
            heroWin = true;
            return true;
        }
        return false;
    }

    // monster bounty = level * 100
    private int getMonsterBounty() {
        int bounty = 0;
        for (Character monster : getMonsters()) {
            bounty += monster.getLevel().getLevel() * 100;
        }
        return bounty;
    }
}
