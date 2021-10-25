import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestWorld {
    @Test
    public void testPopulate() {
        World world = new World(8);
        world.printMap();
    }

    @Test
    public void testMove() {
        MapToken token = new MapToken("S", UtilPrintColors.RED);

        World world = new World(8);
        world.addToken(token);
        world.printMap();

        boolean valid = world.move(token, 0, 1);
        world.printMap();

        if (valid) {
            Assertions.assertArrayEquals(new int[]{0, 1}, token.getCoordinates());
        } else {
            Assertions.assertFalse(Arrays.equals(new int[]{0, 1}, token.getCoordinates()));
        }

    }
}
