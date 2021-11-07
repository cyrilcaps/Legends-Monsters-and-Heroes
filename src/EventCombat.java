import java.util.ArrayList;
import java.util.List;

public class EventCombat extends Event {
    private final List<Character> monsters;

    public EventCombat(List<Character> monsters) {
        this.monsters = monsters;
    }

    @Override
    public void enter(Party party) {
        // create setting
        switch (((int) (Math.random() * 3))) {
            case 0:
                cave();
                break;
            case 1:
                forest();
                break;
            case 2:
                castle();
                break;
        }

        // declare monsters
        System.out.println("Prepare yourself for battle! You encounter... ");
        monsters.forEach(m -> System.out.println("\t" + m));

        // combat
        List<Character> characters = new ArrayList<>(party.getHeroes().values());
        Combat combat = new Combat(characters, monsters);
        combat.play();

        party.addCombat();
        if (combat.isHeroWin()) {
            party.addWin();
        }
    }

    private void cave() {
        String cave = "       ___..-.\n" +
                "     ._/  __ \\_`-.__        \n" +
                "     / .'/##\\_ `-.  \\--.     \n" +
                "     .-_/#@@##\\  /-' `\\_    \n" +
                "    - /########\\_  \\._   `-\n" +
                "    \" \"'\"''\"'\"'''\" ''\"'\"''\"" +
                "\nYou walk through a cave in the mountains...";
        System.out.println(cave);
    }

    private void forest() {
        String forest = "     ^ ^       ^\n" +
            "  ^ /|\\|\\ ^   /|\\ ^ \n" +
            " /|\\/|\\|\\/|\\ ^ |\\/|\\ \n" +
            " /|\\/|\\|\\/|\\/|\\|\\/|\\\n" +
            " /|\\/|\\|\\/|\\/|\\|\\/|\\\n" +
            "\nYou enter a dark forest...";
        System.out.println(Util.colorString(UtilPrintColors.GREEN, forest));
    }

    private void castle() {
        String castle = "  / \\               / \\\n" +
                " /   \\             /   \\\n" +
                "(_____)           (_____)\n" +
                " |   |  _   _   _  |   |\n" +
                " | O |_| |_| |_| |_| O |\n" +
                " |-  |          _  | - |\n" +
                " |   |   - _^_     |   |\n" +
                " |  _|    //|\\\\  - |   |\n" +
                " |   |   ///|\\\\\\   |  -|\n" +
                " |-  |_  |||||||   |   |\n" +
                " |   |   |||||||   |-  |\n" +
                " |___|___|||||||___|___|\n" +
                "         (      (\n" +
                "\nYou explore an abandoned castle...";
        System.out.println(castle);
    }
}
