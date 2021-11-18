import java.util.List;

public class EventTeleport extends Event {
    private int row;
    private int col;
    private final MapToken tokenIn; //Token of the hero that is teleporting
    private final Party party;
    private final List<Party> partyList;

    public EventTeleport(Party party, MapToken currentToken, List<Party> partyList) {
        //Default -1: unchanged if teleport is not valid
        this.row = -1;
        this.col = -1;
        this.tokenIn = currentToken;
        this.party = party; //The hero attempting to teleport
        this.partyList = partyList; //All heroes (to get their positions)
        enter(party);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public MapToken getTokenIn() { return tokenIn; }

    public Party getParty() { return party; }

    public List<Party> getPartyList() { return partyList; }

    public void enter(Party party) {
        //Character character = party.getHeroes().get(party.getHeroes().keySet().toArray()[0]);
        //Instructions
        System.out.println("To teleport, please enter a row and a column.\nRows are listed from top to bottom:" +
                "\n\t-Row 1 is at the top of the map\n\t-Row 8 is at the bottom\nColumns are listed from left to right:" +
                "\n\t-Column 1 is at the left of the map\n\t-Column 8 is at the right\nKeep in mind that a hero " +
                "can only teleport up to the furthest hero in their respective lane.");

        boolean rowValid = false;
        boolean colValid = false;
        String row_num = null;
        String col_num = null;
        //Make user input row and column until input is valid
        while (!rowValid) {
            row_num = Input.getString("Enter a row: ");
            rowValid = checkIfValidInt(row_num);
        }
        while (!colValid) {
            col_num = Input.getString("Enter a column: ");
            colValid = checkIfValidInt(col_num);
        }

        //Strings will convert without error because of previous error checking
        checkTP(Integer.parseInt(row_num), Integer.parseInt(col_num));
    }

    public boolean checkIfValidInt(String numStr) {
        //Check if valid int and in range
        boolean isInteger = true;
        boolean isInRange = true;
        int str_to_int = 0;

        try {
            str_to_int = Integer.parseInt(numStr);
        } catch (Exception e) {
            isInteger = false;
        }

        if (isInteger) {
            if (str_to_int < 1 || str_to_int > 8) { //Rows/cols are 1-8
                isInRange = false;
            }
        }
        else {
            isInRange = false;
        }

        return isInteger && isInRange;
    }

    public void checkTP(int row_num, int col_num) {
        //Check if move is valid, check if space is inaccessible -> already done in World.java
        boolean validTP = true;

        //Check if a hero is able to teleport there (based on position of all heroes)
        //TODO - Previous hero positions before using back
        //Store row and column of position of other 2 heroes (that are not currently trying to teleport)
        int[][] otherHeroPositions = new int[2][2];
        int idx = 0;
        //Should be at least 6 parties (3 heroes and at least 3 monsters)
        for (int i = 0; i < this.getPartyList().size(); i++) {
            if (this.getPartyList().get(i).isHero()) { //Should be 3 heroes
                if (this.getPartyList().get(i).getHeroes() != this.getParty().getHeroes()) { //2 other hero positions
                    otherHeroPositions[idx] = this.getPartyList().get(i).getToken().getCoordinates();
                    idx++;
                }
            }
        }

        if (col_num != (otherHeroPositions[0][1] + 1) && col_num != (otherHeroPositions[1][1] + 1)) {
            //If column does not match where a hero is, reject
            validTP = false;
        }

        if (col_num == (otherHeroPositions[0][1] + 1)) {
            if (row_num < (otherHeroPositions[0][0] + 1)) {
                //If row is less than where 1st hero is (ex: hero 2 is in row 3 and hero 1 teleports to row 2), reject
                validTP = false;
            }
        }
        else if (col_num == (otherHeroPositions[1][1] + 1)) {
            if (row_num < (otherHeroPositions[1][0] + 1)) {
                //If row is less than where 2nd hero is, reject
                validTP = false;
            }
        }

        //If valid, save to data members to be retrieved
        if (validTP) {
            this.setRow(row_num);
            this.setCol(col_num);
        }
        else { //Else save "-1" to data members
            this.setRow(-1);
            this.setCol(-1);
        }
    }
}
