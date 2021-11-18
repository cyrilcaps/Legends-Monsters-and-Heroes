import java.util.*;

public class MapSquare extends BoardSquare {
    private final MapSquareType type;
    
    private boolean containsHero = false;
    private boolean containsMonster = false;
    private List<MapToken> Moccupier = new ArrayList<MapToken>();
    private List<MapToken> Hoccupier = new ArrayList<MapToken>();

    // occupier stores who's in a cell

    public MapSquare(MapSquareType type) {
        this.type = type;
    }

    public MapSquareType getType() {
        return type;
    }

    public boolean hasMonster(){
        return containsMonster;
    }

    public boolean hasHero(){
        return containsHero;
    }

    public List<MapToken> getMonster() {
        return Moccupier;
    }

    public List<MapToken> getHero(){
        return Hoccupier;
    }

    public void occupy(MapToken occupier) {
        if(occupier.getSymbol().charAt(0) == 'H'){
            Hoccupier.add(occupier);
            containsHero = true;
        }else{
            Moccupier.add(occupier);
            containsMonster = true;
        }
    }

    public MapToken unoccupy(MapToken token) {
        MapToken toReturn = token;
        if(token.getSymbol().charAt(0) == 'H'){
            Hoccupier.remove(token);
            containsHero = false;
        }else{
            Moccupier.remove(token);
            containsMonster = false;
        }
        
        return toReturn;
    }

    public boolean isFull(){
        return Hoccupier.size() + Moccupier.size() == 2;
    }

    public boolean isOccupied() { // contains at least one character
        return !Hoccupier.isEmpty() || !Moccupier.isEmpty();
    }

    public boolean isEmpty(){
        return Hoccupier.isEmpty() && Moccupier.isEmpty();
    }

    @Override
    
    public String toString() {
  
        if(isFull()){
            
            StringBuilder a = new StringBuilder();
            for(MapToken e : Hoccupier){
                a.append(e.getSymbol());
            }
            for(MapToken e : Moccupier){
                a.append(e.getSymbol());
            }
            a.append(" ");
            return a.toString();

        }else if(isOccupied()){
            List<MapToken> occupier;
            if(Hoccupier.size()>0){
                occupier = Hoccupier;
            }else{
                occupier = Moccupier;
            }
                StringBuilder a = new StringBuilder();
                for(MapToken e : occupier){
                    a.append(" ").append(e.getSymbol()).append("  ");
                }  
                return a.toString();

            }else if(type.equals(MapSquareType.INACCESSIBLE)){
            return "XXXXX";
        }

        return "     ";
    
    }
}
