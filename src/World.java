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

    public MapSquare getMapSquare(int x, int y) {
        return map.getBoardSquare(x, y);
    }

    // randomly populate map using weights
    private void populateMap() {
        // 20% of each special cells, 40% plain and the rest is inaccessable(16 cells) and nexus(12 cells).
        int totalSize = size * size;
        int inaccessable = totalSize/4;
        int nexus = (size/4 )* 3;
        totalSize -= inaccessable + nexus;
        int plain = (int) (totalSize * 0.4);
        int bush = (int) (totalSize * 0.2);
        int cave = (int) (totalSize * 0.2);
        int koulou = (int) (totalSize * 0.2);

        // randomize list with possible squares
        List<MapSquare> mapSquares = new ArrayList<>();
        IntStream.range(0, plain).forEach(i -> mapSquares.add(new MapSquare(MapSquareType.PLAIN)));
        IntStream.range(0, bush).forEach(i -> mapSquares.add(new MapSquare(MapSquareType.BUSH)));
        IntStream.range(0, cave).forEach(i -> mapSquares.add(new MapSquare(MapSquareType.CAVE)));
        IntStream.range(0, koulou).forEach(i -> mapSquares.add(new MapSquare(MapSquareType.KOULOU)));
      

        // shuffle squares and 
        
            Collections.shuffle(mapSquares);
            int count = 0;
            //count is the pointer for mapSquares.
            for (int i = 0; i < size; i++) {
                if(i==0){
                    //take care of the first row - monster nexus
                    for (int j = 0; j < 2; j++) {
                        map.setBoardSquare(i, j, new MapSquare(MapSquareType.MONSTERNEXUS));
                    }
    
                    map.setBoardSquare(i, 2, new MapSquare(MapSquareType.INACCESSIBLE));
                    
    
                    for (int j = 3; j < 5; j++) {
                        map.setBoardSquare(i, j, new MapSquare(MapSquareType.MONSTERNEXUS));
                    }
    
                    map.setBoardSquare(i, 5, new MapSquare(MapSquareType.INACCESSIBLE));
                  
    
                    for (int j = 6; j < size; j++) {
                        map.setBoardSquare(i, j, new MapSquare(MapSquareType.MONSTERNEXUS));
                    }

                }else if(i == size-1){
                    //take care of the hero nexus
                    for (int j = 0; j < 2; j++) {
                        map.setBoardSquare(i, j, new MapSquare(MapSquareType.HERONEXUS));
                    }
    
                    map.setBoardSquare(i, 2, new MapSquare(MapSquareType.INACCESSIBLE));
                    
    
                    for (int j = 3; j < 5; j++) {
                        map.setBoardSquare(i, j, new MapSquare(MapSquareType.HERONEXUS));
                    }
    
                    map.setBoardSquare(i, 5, new MapSquare(MapSquareType.INACCESSIBLE));
                  
    
                    for (int j = 6; j < size; j++) {
                        map.setBoardSquare(i, j, new MapSquare(MapSquareType.HERONEXUS));
                    }
                }else{
                    // for the rest of the rows
                    for (int j = 0; j < 2; j++) {
                    map.setBoardSquare(i, j, mapSquares.get(count));
                    count++;
                }

                map.setBoardSquare(i, 2, new MapSquare(MapSquareType.INACCESSIBLE));
                

                for (int j = 3; j < 5; j++) {
                    map.setBoardSquare(i, j, mapSquares.get(count));
                    count++;
                }

                map.setBoardSquare(i, 5, new MapSquare(MapSquareType.INACCESSIBLE));
              

                for (int j = 6; j < size; j++) {
                    map.setBoardSquare(i, j, mapSquares.get(count));
                    count++;
                }
            }
            }
       
    }

    // basic check that each squares has ONE possible move
    // will not catch if multiple squares are blocked off
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

    /*
    Place token on a random valid square on map
     */
    public void spawnToken(MapToken token) {
        boolean valid = false;
        while(!valid) {
            int x = Math.min((int) (Math.random() * size), 7);
            int y = Math.min((int) (Math.random() * size), 7);
            valid = move(token, x, y);
            token.getCoordinates()[0] = x;
            token.getCoordinates()[1] = y;
        }
    }

    // get square of token's location
    public MapSquare getMapSquare(MapToken token) {
        return map.getBoardSquare(token.getCoordinates()[0], token.getCoordinates()[1]);
    }

    // try to move token to valid square, true is success, false if invalid
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
                .unoccupy(token);
        map.getBoardSquare(newRow, newCol)
                .occupy(token);
        token.setCoordinates(new int[]{newRow, newCol});

        // determine event
        return true;
    }

    // print map
    public void printMap() {
        //map.printBoard(false);

        for(int i = 0; i<size; i++){
            
            for(int count = 0; count < 3; count ++){
                StringBuilder rowbuild = new StringBuilder();
                if(count ==0 || count == 2){
                    for(int j = 0; j < size; j++){
                        String symbol = map.getBoardSquare(i, j).getType().getSymbol();
                        rowbuild.append(" "+symbol+"-").append(symbol+"-").append(symbol+"  ");

                    }
                }else{
                    for(int j = 0; j < size; j++){
                        String content = map.getBoardSquare(i, j).toString();
                        rowbuild.append("|").append(content).append("| ");
                    }
                }
                System.out.println(rowbuild.toString());
                
            }
            System.out.println();
        }
    }
}
