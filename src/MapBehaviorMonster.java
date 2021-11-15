public class MapBehaviorMonster implements MapBehavior {
    @Override
    public ActionWorld action(Party party) {
        // 1st option = move
        if (!party.isHasMoved()) {
            MapToken token = party.getToken();
            int[] coordinates = new int[]{token.getCoordinates()[0] + 1, token.getCoordinates()[1]};
            return new ActionWorld(ActionMapType.MOVE, coordinates);
        }
        // 2nd option = attack
        if (!party.isHasAttacked()) {
            return new ActionWorld(ActionMapType.ATTACK, new int[]{0,0});
        }

        // end turn if nothing left
        return new ActionWorld(ActionMapType.END, new int[]{0,0});
    }
}
