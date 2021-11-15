public class EventTeleport extends Event {
    private int row;
    private int col;

    public EventTeleport(Party party) {
        //Default -1: unchanged if teleport is not valid
        this.row = -1;
        this.col = -1;
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

    public void enter(Party party) {
        Character character = party.getHeroes().get(party.getHeroes().keySet().toArray()[0]);
        System.out.println("To teleport, please enter a row and a column.\nRows are listed from top to bottom:" +
                "\n\t-Row 1 is at the top of the map\n\t-Row 8 is at the bottom\nColumns are listed from left to right:" +
                "\n\t-Column 1 is at the left of the map\n\t-Column 8 is at the right\nKeep in mind that a hero " +
                "can only teleport up to the furthest hero in their respective lane.");

        boolean rowValid = false;
        boolean colValid = false;
        String row_num = null;
        String col_num = null;
        while (!rowValid) {
            row_num = Input.getString("Enter a row: ");
            rowValid = checkIfValidInt(row_num);
        }
        while (!colValid) {
            col_num = Input.getString("Enter a column: ");
            colValid = checkIfValidInt(col_num);
        }

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
            if (str_to_int < 0 || str_to_int > 8) { //Rows/cols are 1-8
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

        //Check if a hero is able to teleport there (based on position of all heroes)
        //TODO, add position data member for each hero?

        //If valid, save to data members to be retrieved
        this.setRow(row_num);
        this.setCol(col_num);
        //Else save "-1" to data members
    }
}
