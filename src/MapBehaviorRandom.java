public class MapBehaviorRandom implements MapBehavior {
    @Override
    public ActionWorld action() {
        return new ActionWorld(ActionMapType.NONE, new int[]{0,0});
    }
}
