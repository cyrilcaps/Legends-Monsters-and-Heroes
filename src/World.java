import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class World {
    private final Board<MapSquare> map;
    private final int size;

    public World(int size) {
        this.size = size;
        map = new Board<>(8);
        populateMap();
    }

    private void populateMap() {
        // 20% non-accessible cells, 10% markets and 70% common cells
        int totalSize = size * size;
        int inaccessible = (int) (totalSize * 0.2);
        int markets = (int) (totalSize * 0.1);
        int common = totalSize - inaccessible - markets;

        // randomize list with possible squares
        List<MapSquare> mapSquares = new ArrayList<>();
        IntStream.range(0, inaccessible).forEach(i -> mapSquares.add(new MapSquare(MapSquareType.INACCESSIBLE)));
        IntStream.range(0, markets).forEach(i -> mapSquares.add(new MapSquare(MapSquareType.MARKET)));
        IntStream.range(0, common).forEach(i -> mapSquares.add(new MapSquare(MapSquareType.COMMON)));


        // shuffle list, set squares, repeat if invalid
        do {
            Collections.shuffle(mapSquares);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map.setBoardSquare(i, j, mapSquares.get(i * size + j));
                }
            }
        } while (!validate());
    }

    // basic check that all accessible squares have a possible move
    private boolean validate() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                MapSquare square = map.getBoardSquare(i, j);
                if (!square.getType().equals(MapSquareType.INACCESSIBLE)) {
                    if (i != size - 1 && !map.getBoardSquare(i + 1, j).getType().equals(MapSquareType.INACCESSIBLE)) {
                        continue;
                    } else if (i != 0 && !map.getBoardSquare(i - 1, j).getType().equals(MapSquareType.INACCESSIBLE)) {
                        continue;
                    } else if (j != size -1 && !map.getBoardSquare(i, j + 1).getType().equals(MapSquareType.INACCESSIBLE)) {
                        continue;
                    } else if (j != 0 && !map.getBoardSquare(i, j - 1).getType().equals(MapSquareType.INACCESSIBLE)) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void addToken(MapToken token) {
        boolean valid = false;
        while(!valid) {
            int x = Math.min((int) (Math.random() * size), 7);
            int y = Math.min((int) (Math.random() * size), 7);
            valid = move(token, x, y);
            token.getCoordinates()[0] = x;
            token.getCoordinates()[1] = y;
        }
    }

    public boolean move(MapToken token, int newRow, int newCol) {
        // check next square valid
        if(!map.isValid(newRow, newCol)) {
            System.out.println("invalid");
            return false;
        }

        // check new square occupied
        if (map.getBoardSquare(newRow, newCol).getType().equals(MapSquareType.INACCESSIBLE)) {
            return false;
        }

        // move symbol from old square to new square
        map.getBoardSquare(token.getCoordinates()[0], token.getCoordinates()[1])
                .unoccupy();
        map.getBoardSquare(newRow, newCol)
                .occupy(token.toString());
        token.setCoordinates(new int[]{newRow, newCol});

        // determine event
        return true;
    }

    public void printMap() {
        map.printBoard(false);
    }
}
