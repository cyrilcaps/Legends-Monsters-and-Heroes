import java.util.ArrayList;
import java.util.List;

public class WorldGame extends Game {
    private final World world = new World(8);
    private final List<Party> partyList = new ArrayList<>();
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

        Party party = turnBasedManager.next();
        while (turnBasedManager.hasNext()) {
            // get action
            ActionWorld action = party.move();

            // quit will exit play
            boolean valid = false;
            boolean turnEnded = false;
            switch (action.getType()) {
                case END:
                    turnEnded = true;
                case MOVE:
                    valid = world.move(party.getToken(), action.getCoordinates()[0], action.getCoordinates()[1]);
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

                // Next turn if:
                // Hero ends turn
                // Forced to end turn if: attacked or moved
                if (turnEnded) {
                    party = turnBasedManager.next();
                }
            }
        }
    }
}
