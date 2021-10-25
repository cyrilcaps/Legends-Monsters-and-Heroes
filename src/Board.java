import java.util.ArrayList;
import java.util.List;

public class Board <T extends BoardSquare> {
    // board conditions
    private final int size;
    private final List<List<T>> board;

    public Board(int size) {
        // instantiate board
        this.size = size;
        board = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>());
        }

        // fill with empty squares
        resetBoard();
    }

    /*
    BoardSquares mutators/accessors
     */

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            board.get(i).clear();
            for (int j = 0; j < size; j++) {
                board.get(i).add(null);
            }
        }
    }

    public T getBoardSquare(int x, int y) {
        return board.get(x).get(y);
    }

    public void setBoardSquare(int x, int y, T square) {
        board.get(x).set(y, square);
    }

    public boolean isValid(int row, int col) {
        return row < size && row >= 0 && col < size && col >= 0;
    }

    /*
    Board printing methods
     */

    private String getFillerRow(int numBoardSquares, String leftBuffer) {
        StringBuilder printRow = new StringBuilder(leftBuffer);
        for (int i = 0; i < numBoardSquares; i++) {
            printRow.append("+---");
        }
        printRow.append("+");
        return printRow.toString();
    }

    public void printBoard(boolean headers) {
        System.out.println();

        String leftBuffer = "";
        if (headers) {
            leftBuffer = "  ";
            StringBuilder printRow = new StringBuilder("    ");
            for (int col = 0; col < board.get(0).size(); col++) {
                printRow.append(col).append("   ");
            }
            System.out.println(printRow);
        }

        System.out.println(getFillerRow(size, leftBuffer));
        for (int i = 0; i < board.size(); i++) {
            StringBuilder printRow = new StringBuilder("| ");
            if (headers) {
                printRow.insert(0, i + " ");
            }
            for (int j = 0; j < board.get(i).size(); j++) {
                printRow.append(getBoardSquare(i, j).toString()).append(" | ");
            }
            System.out.println(printRow);
            System.out.println(getFillerRow(size, leftBuffer));
        }
    }
}
