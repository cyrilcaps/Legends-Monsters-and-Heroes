import java.util.List;

public class EventTeleport extends Event {
    private int row;
    private int col;
    private final Party party;
    private final Board<MapSquare> map; //Get the map to retrieve information about if that tile has been explored

    public EventTeleport(Party party, Board<MapSquare> map) {
        //Default -1: unchanged if teleport is not valid
        this.row = -1;
        this.col = -1;
        this.party = party; //The hero attempting to teleport
        this.map = map; //The map used to determine if the square that a user is attempting to teleport has been visited
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

    public Board<MapSquare> getMap() { return map; }

    public Party getParty() { return party; }

    public void enter(Party party) {
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
        boolean validTP;

        //Check based on if that square has been visited by another hero
        validTP = this.map.getBoardSquare(row_num - 1, col_num - 1).getExplored();

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
