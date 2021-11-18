import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Party {
    private final Map<String, Character> heroes = new HashMap<>();
    private final MapToken token;

    // behavior variables
    private boolean monster = false;
    private MapBehavior behavior;
    private boolean hasMoved = false;
    private boolean hasAttacked = false;

    // combat stats
    private int combats = 0;
    private int wins = 0;

    public Party(String symbol, UtilPrintColors color) {
        token = new MapToken(symbol, color);
    }

    public Map<String, Character> getHeroes() {
        return heroes;
    }

    public MapToken getToken() {
        return token;
    }

    public boolean isHero() {
        return !monster;
    }

    public void setMonster(boolean monster) {
        this.monster = monster;
    }

    public void setBehavior(MapBehavior behavior) {
        this.behavior = behavior;
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

    public void addHero(Character character) {
        heroes.put(character.getName(), character);
    }

    public Character getCharacter() {
        return (new ArrayList<>(heroes.values())).get(0);
    }

    public ActionWorld move() {
        if (behavior != null) {
            return behavior.action(this);
        }

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
                    "I/i: show information/equip weapons and armor\n" +
                    "M/m: show map\n" +
                    "X/x: attack\n" +
                    "C/c: cast spell\n" +
                    "P/p: use potion\n" +
                    "T/t: teleport\n" +
                    "B/b: back to nexus\n" +
                    "E/e: end turn\n" +
                    "Q/q: quit game\n" +
                    "Enter action: ");
            int[] coordinates = new int[2];
            switch (input.toUpperCase()) {
                case ("W"):
                    coordinates = new int[]{token.getCoordinates()[0] - 1, token.getCoordinates()[1]};
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
                    printHeroes();
                    new EventInfo(this); //Info menu for user to equip weapons/armor
                    coordinates = new int[2];
                    Input.getString("Any key to continue...");
                    return new ActionWorld(ActionMapType.NONE, coordinates);
                case("E"):
                    //End turn for current hero, switch to other hero
                    coordinates = new int[2];
                    //Added type "END" to end turn, distinguish itself from NONE
                    return new ActionWorld(ActionMapType.END, coordinates);
                case("M"):
                    //Show the map
                    coordinates = new int[2];
                    return new ActionWorld(ActionMapType.MAP, coordinates);
                case("X"):
                    // attack
                    return new ActionWorld(ActionMapType.ATTACK, coordinates);
                case("C"):
                    // spell
                    return new ActionWorld(ActionMapType.SPELL, coordinates);
                case("P"):
                    //Potion
                    coordinates = new int[2];
                    new EventPotion(this); //Info menu for user to use a potion
                    return new ActionWorld(ActionMapType.NONE, coordinates);
                case("T"):
                    //Teleport
                    EventTeleport teleport = new EventTeleport(this, token); //Menu for a user to teleport
                    int row = teleport.getRow();
                    int col = teleport.getCol();
                    if (row != -1 && col != -1) {
                        System.out.println("Teleport was successful!");
                        coordinates = new int[]{row - 1, col - 1};
                        return new ActionWorld(ActionMapType.MOVE, coordinates);
                    }
                    else {
                        System.out.println("Teleport was unsuccessful.");
                        coordinates = new int[2];
                        return new ActionWorld(ActionMapType.NONE, coordinates);
                    }
                case("B"):
                    //Back to nexus (for now, to the bottom of the map)
                    if (token.getCoordinates()[0] != 7) {
                        coordinates = new int[]{7, token.getCoordinates()[1]};
                        return new ActionWorld(ActionMapType.MOVE, coordinates);
                    }
                    else {
                        System.out.println("Hero is already at their nexus.");
                        coordinates = new int[2];
                        return new ActionWorld(ActionMapType.NONE, coordinates);
                    }
            }
        }
    }

    private void printHeroes() {
        for (Character hero : heroes.values()) {
            System.out.println(hero);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return Objects.equals(token.getSymbol(), party.token.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "Party{" +
                "heroes=" + heroes +
                ", token=" + token +
                ", monster=" + monster +
                ", behavior=" + behavior +
                ", hasMoved=" + hasMoved +
                ", hasAttacked=" + hasAttacked +
                ", combats=" + combats +
                ", wins=" + wins +
                '}';
    }
}
