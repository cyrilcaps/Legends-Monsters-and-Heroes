
public class Board {
    // board conditions
    private final int size;
    private final BoardSquare[][] board;

    public Board(int size) {
        // instantiate board
        this.size = size;
        board = new BoardSquare[size][size];

        // fill with empty squares
        resetBoard();
    }

    /*
    BoardSquares mutators/accessors
     */

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                setBoardSquare(i, j, new BoardSquare());
            }
        }
    }

    public BoardSquare getBoardSquare(int x, int y) {
        return board[x][y];
    }

    public void setBoardSquare(int x, int y, BoardSquare square) {
        board[x][y] = square;
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
            for (int col = 0; col < board[0].length; col++) {
                printRow.append(col).append("   ");
            }
            System.out.println(printRow);
        }

        System.out.println(getFillerRow(size, leftBuffer));
        for (int i = 0; i < board.length; i++) {
            StringBuilder printRow = new StringBuilder("| ");
            if (headers) {
                printRow.insert(0, i + " ");
            }
            for (int j = 0; j < board[i].length; j++) {
                printRow.append(board[i][j].toString()).append(" | ");
            }
            System.out.println(printRow);
            System.out.println(getFillerRow(size, leftBuffer));
        }
    }
}
