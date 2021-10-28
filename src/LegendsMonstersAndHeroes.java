import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LegendsMonstersAndHeroes {
    public static void start() {
        // get symbol
        String symbol = "";
        do {
            symbol = Input.getString("Enter single character for symbol: ");
        } while (symbol.length() != 1);
        symbol = symbol.toUpperCase();

        // get color
        List<UtilPrintColors> colors = Arrays.asList(UtilPrintColors.RED,
                UtilPrintColors.BLUE,
                UtilPrintColors.PURPLE,
                UtilPrintColors.CYAN);
        List<String> colorStrings = colors.stream().map(c -> c + c.name() + UtilPrintColors.RESET)
                .collect(Collectors.toList());
        System.out.println("Choose color:");
        int selection = Input.getIntWithMenu(colorStrings, 1);
        UtilPrintColors color  = colors.get(selection - 1);

        // init party
        Party party = new Party(symbol, color);

        int partyCount = -1;
        do {
            partyCount = Input.getInt("Insert number of heroes:");
        } while(partyCount == -1);
        IntStream.range(0, partyCount).forEach(x -> party.addHero(GlobalData.getRandom(GlobalData.getHeroes())));

        // init world
        WorldGame game = new WorldGame();
        game.addParty(party);
        game.play();
    }
}
