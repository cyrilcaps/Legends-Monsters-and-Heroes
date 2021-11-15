import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldGame extends Game {
    private final World world = new World(8);
    private final List<Party> partyList = new ArrayList<>();
    private final Map<String, Party> parties = new HashMap<>();
    private final TurnBasedManager<Party> turnBasedManager = new TurnBasedManager<>();

    public WorldGame() {
    }

    public void addParty(Party party) {
        world.spawnToken(party.getToken());
        partyList.add(party);
    }

    @Override
    public void play() {
        for (Party value : partyList) {
            List<Party> oneHeroParty = new ArrayList<>();
            oneHeroParty.add(value);
            turnBasedManager.addTeam(oneHeroParty);
        }

        // game start
        world.printMap();

        boolean start = true;
        Party party = turnBasedManager.next();
        while (turnBasedManager.hasNext()) {
            // heroes, start of round - respawn or recover
            if (start && party.isHero()) {
                if (party.getCharacter().isFainted()) {
                    // perform back action for free
                    party.getCharacter().getStats().addHealth(party.getCharacter().getStats().getMaxHealth() / 2);
                } else {
                    // recover some health/mana
                    ((CharacterHero) party.getCharacter()).recover();
                }
            }
            start = false;

            // get action
            ActionWorld action = party.move();

            // quit will exit play
            boolean valid = false;
            boolean turnEnded = false;
            switch (action.getType()) {
                case END:
                    turnEnded = true;
                    break;
                case MOVE:
                    if (!party.isHasMoved()) {
                        valid = world.move(party.getToken(), action.getCoordinates()[0], action.getCoordinates()[1]);
                        if (valid) {
                            party.setHasMoved(true);
                        }
                    } else {
                        System.out.println("You have already moved!");
                    }
                    break;
                case ATTACK:
                    attack(party);
                    break;
                case SPELL:
                    cast(party);
                    break;
                case NONE:
                    break;
                case QUIT:
                    return;
            }

            // print new location
            world.printMap();

            // resolve new square - market or common
            if (valid) {
                // generate event for new location
                MapSquare currentSquare = world.getMapSquare(party.getToken());
                Event event = EventFactory.generateEvent(currentSquare.getType(),
                        new ArrayList<>(party.getHeroes().values()));

                // enter event if exists
                if (event != null) {
                    event.enter(party);

                    // print location
                    world.printMap();
                }
            }

            // Next turn if:
            // Hero ends turn
            // Forced to end turn if: attacked or moved
            if (turnEnded) {
                party.setHasMoved(false);
                party.setHasAttacked(false);

                party = turnBasedManager.next();
                start = true;
            }
        }
    }

    // can do attack/spell if character not attacked and targets available
    private boolean canAttack(Party party, List<Character> targets) {
        if (party.isHasAttacked()) {
            System.out.println(party.getToken().getSymbol() + " has already attacked!");
            return false;
        }
        if (targets.isEmpty()) {
            System.out.println("No targets nearby");
            return false;
        }

        return true;
    }

    private void attack(Party party) {
        List<Character> targets = getCharactersInRange(party.getToken(), party.isHero());
        if (canAttack(party, targets)) {
            // monster = attack first target, hero = use target selector
            Character target = targets.get(0);
            if (party.isHero()) {
                target = CombatPlayer.targetSelector(targets);
            }

            // apply damage to target
            if (target != null) {
                party.setHasAttacked(true);
                target.applyCombat(new ActionCombat(ActionCombatType.ATTACK,
                        party.getCharacter().getDamage(), target.getName()));
            }
        } else {
            // set true to say no targets
            party.setHasAttacked(true);
        }
    }

    private void cast(Party party) {
        List<Character> targets = getCharactersInRange(party.getToken(), party.isHero());
        if (canAttack(party, targets)) {
            ActionCombat spellAction = (new CombatPlayer()).castSpell(
                    getCharactersInRange(party.getToken(), party.isHero()),
                    party.getCharacter());
            if (spellAction != null) {
                party.setHasAttacked(true);
            }
        } else {
            // set true to say no targets
            party.setHasAttacked(true);
        }
    }

    private List<Character> getCharactersInRange(MapToken token, boolean getMonsters) {
        List<Character> characters = new ArrayList<>();

        int range = 1;
        int tokenX = token.getCoordinates()[0];
        int tokenY = token.getCoordinates()[0];
        for (int i = tokenX - range ; i < tokenX + range; i++) {
            for (int j = tokenY - range; j < tokenY + range; j++) {
                MapSquare square = world.getMapSquare(i, j);
                String name = square.getOccupier();

                for (Character character : parties.get(name).getHeroes().values()) {
                    if (getMonsters) {
                        if (character instanceof CharacterMonster) {
                            characters.add(character);
                        }
                    } else {
                        if (character instanceof CharacterHero) {
                            characters.add(character);
                        }
                    }
                }
            }
        }
        return characters;
    }
}
