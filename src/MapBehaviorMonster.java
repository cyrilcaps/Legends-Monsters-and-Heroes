public class MapBehaviorMonster implements MapBehavior {
    @Override
    public ActionWorld action(Party party) {
        if (!party.getCharacter().isFainted()) {
            // 1st option = attack
            if (!party.isHasAttacked()) {
                System.out.println("Try to attack");
                return new ActionWorld(ActionMapType.ATTACK, new int[]{0, 0});
            }

            // 2nd option = move down
            if (!party.isHasMoved()) {
                System.out.println("Try to move down");
                MapToken token = party.getToken();
                int[] coordinates = new int[]{token.getCoordinates()[0] + 1, token.getCoordinates()[1]};
                return new ActionWorld(ActionMapType.MOVE, coordinates);
            }
        }

        // end turn if nothing left
        return new ActionWorld(ActionMapType.END, new int[]{0,0});
    }
}
