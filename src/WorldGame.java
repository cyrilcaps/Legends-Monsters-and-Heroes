import java.util.ArrayList;
import java.util.List;

public class WorldGame extends Game {
    private final World world = new World(8);
    private final List<Party> partyList = new ArrayList<>();
    private final TurnBasedManager<Party> turnBasedManager = new TurnBasedManager<>();

    public WorldGame() {
    }

    public void addParty(Party party) {
        world.addToken(party.getToken());
        partyList.add(party);
    }

    public void play() {
        turnBasedManager.addTeam(partyList);

        Party party = turnBasedManager.next();
        while (turnBasedManager.hasNext()) {
            // print map
            world.printMap();

            // get action
            ActionWorld action = party.move();

            // action = quit
            if (action.getType().equals(ActionMapType.QUIT)) {
                return;
            }

            // action = move
            boolean valid = world.move(party.getToken(), action.getCoordinates()[0], action.getCoordinates()[1]);

            // resolve new square - empty, fight, or market
            if (valid) {
                // party(event)
                party = turnBasedManager.next();
            }
        }
    }
}
