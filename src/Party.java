import java.util.HashMap;
import java.util.Map;

public class Party {
    private final Map<String, CharacterHero> heroes = new HashMap<>();
    private final MapToken token;

    // behavior variables
    private MapBehavior behavior;
    boolean hasMoved = false;
    boolean hasAttacked = false;

    // combat stats
    private int combats = 0;
    private int wins = 0;

    public Party(String symbol, UtilPrintColors color) {
        token = new MapToken(symbol, color);
    }

    public Map<String, CharacterHero> getHeroes() {
        return heroes;
    }

    public MapToken getToken() {
        return token;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isHasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public void addCombat() {
        combats += 1;
    }

    public int getCombats() {
        return combats;
    }

    public void addWin() {
        wins += 1;
    }

    public int getWins() {
        return wins;
    }

    public void addHero(CharacterHero hero) {
        heroes.put(hero.getName(), hero);
    }

    public ActionWorld move() {
        while (true) {
            /*
                W/w: move up
                A/a: move left
                S/s: move down
                D/d: move right
                Q/q: quit game
                I/i: show information
             */
            String input = Input.getString("W/w: move up\n" +
                    "A/a: move left\n" +
                    "S/s: move down\n" +
                    "D/d: move right\n" +
                    "Q/q: quit game\n" +
                    "I/i: show information\n");
            switch (input.toUpperCase()) {
                case ("W"):
                    int[] coordinates = new int[]{token.getCoordinates()[0] - 1, token.getCoordinates()[1]};
                    return new ActionWorld(ActionMapType.MOVE, coordinates);
                case ("A"):
                    coordinates = new int[]{token.getCoordinates()[0], token.getCoordinates()[1] - 1};
                    return new ActionWorld(ActionMapType.MOVE, coordinates);
                case ("S"):
                    coordinates = new int[]{token.getCoordinates()[0] + 1, token.getCoordinates()[1]};
                    return new ActionWorld(ActionMapType.MOVE, coordinates);
                case ("D"):
                    coordinates = new int[]{token.getCoordinates()[0], token.getCoordinates()[1] + 1};
                    return new ActionWorld(ActionMapType.MOVE, coordinates);
                case ("Q"):
                    coordinates = new int[2];
                    return new ActionWorld(ActionMapType.QUIT, coordinates);
                case ("I"):
                    printHereos();
                    coordinates = new int[2];
                    Input.getString("Any key to continue...");
                    return new ActionWorld(ActionMapType.NONE, coordinates);
            }
        }
    }

    private void printHereos() {
        for (CharacterHero hero : heroes.values()) {
            System.out.println(hero);
        }
    }
}
