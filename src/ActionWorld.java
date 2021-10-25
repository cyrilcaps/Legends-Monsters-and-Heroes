public class ActionWorld extends Action {
    private final ActionMapType type;
    private final int[] coordinates;

    public ActionWorld(ActionMapType type, int[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public ActionMapType getType() {
        return type;
    }

    public int[] getCoordinates() {
        return coordinates;
    }
}
