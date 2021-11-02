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
        turnBasedManager.addTeam(partyList);

        Party party = turnBasedManager.next();
        while (turnBasedManager.hasNext()) {
            // print map
            world.printMap();

            // get action
            ActionWorld action = party.move();

            // quit will exit play
            boolean valid = false;
            switch (action.getType()) {
                case MOVE:
                    valid = world.move(party.getToken(), action.getCoordinates()[0], action.getCoordinates()[1]);
                case NONE:
                    break;
                case QUIT:
                    return;
            }

            // resolve new square - market or common
            if (valid) {
                MapSquare currentSquare = world.getMapSquare(party.getToken());
                Event event = EventFactory.generateEvent(currentSquare.getType());
                if (event != null) {
                    event.enter(party);
                }
                party = turnBasedManager.next();
            }
        }
    }
}
