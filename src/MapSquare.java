public class MapSquare extends BoardSquare {
    private final MapSquareType type;
    private String occupier = "";

    public MapSquare(MapSquareType type) {
        this.type = type;
    }

    public MapSquareType getType() {
        return type;
    }

    public String getOccupier() {
        return occupier;
    }

    public void occupy(String occupier) {
        this.occupier = occupier;
    }

    public String unoccupy() {
        String toReturn = occupier;
        occupier = "";
        return toReturn;
    }

    public boolean isOccupied() {
        return !occupier.isEmpty();
    }

    @Override
    public String toString() {
        if (isOccupied()) {
            return occupier;
        }
        return type.getSymbol();
    }
}
