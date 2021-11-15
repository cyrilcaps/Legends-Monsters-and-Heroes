import java.util.*;

public class MapSquare extends BoardSquare {
    private final MapSquareType type;
    
    private boolean containsHero = false;
    private boolean containsMonster = false;
    private List<MapToken> occupier = new ArrayList<MapToken>();
    // occupier stores who's in a cell

    public MapSquare(MapSquareType type) {
        this.type = type;
    }

    public MapSquareType getType() {
        return type;
    }

    public List<MapToken> getOccupier() {
        return occupier;
    }

    public void occupy(MapToken occupier) {
        this.occupier.add(occupier);
    }

    public MapToken unoccupy(MapToken token) {
        MapToken toReturn = token;
        occupier.remove(token);
        return toReturn;
    }

    public boolean isFull(){
        return occupier.size() == 2;
    }

    public boolean isOccupied() {
        return !occupier.isEmpty();
    }

    @Override
    public String toString() {
        if (isOccupied()) {
            if(isFull()){
                StringBuilder a = new StringBuilder();
                for(MapToken e : occupier){
                a.append(e.getSymbol());
                }  
                return a.toString();
            }else{
                StringBuilder a = new StringBuilder();
                for(MapToken e : occupier){
                a.append(" ").append(e.getSymbol()).append("  ");
                }  
                return a.toString();
            }
        }else if(type.getSymbol().equals(UtilPrintColors.RED+ "X" +UtilPrintColors.RESET)){
            return "XXXXX";
        }

        return "     ";
    
    }
}
