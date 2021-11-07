import java.util.*;
import java.util.stream.Collectors;

public class LegendsMonstersAndHeroes {
    public static void start() {
        // welcome
        System.out.println("              />\n" +
                " (           //------------------------------------------------------\\\n" +
                "(*)OXOXOXOXO(*>             LEGENDS: MONSTERS AND HEROES              \\\n" +
                " (           \\\\------------------------------------------------------/\n" +
                "              \\>");
        System.out.println("Welcome! Please set-up your party.");


        // get symbol
        String symbol;
        do {
            symbol = Input.getString("Enter single character for your map symbol: ");
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
        UtilPrintColors color  = colors.get(selection);

        // init party
        Party party = new Party(symbol, color);

        int partyCount;
        do {
            partyCount = Input.getInt("Insert number of heroes (1-3):");
        } while(partyCount == -1 || partyCount > 3);
        Map<String, CharacterHero> heroes = new HashMap<>();
        while (heroes.size() < partyCount) {
            CharacterHero hero = heroSelector();
            if (hero != null) {
                if (heroes.put(hero.getName(), hero) != null) {
                    System.out.println(hero.getName() + " already in party.");
                } else {
                    party.addHero(hero);
                }
            }
        }

        // init world
        WorldGame game = new WorldGame();
        game.addParty(party);
        game.play();

        // good bye message
        System.out.println("\n************************************\n");
        System.out.println("Thank you for playing!");
        System.out.println("The world will remember the heroic efforts of your party:");
        System.out.println("\tYou were victorious in " + party.getWins() + " out of " +
                party.getCombats() + " battles.");
        for (CharacterHero character : party.getHeroes().values()) {
            System.out.println("\t" + Util.colorString(character.getType().getStringColor(), character.getName()
                    + " " + character.getType().name() + " Lvl " + character.getLevel().getLevel()));
        }
    }

    private static CharacterHero heroSelector() {
        CharacterHeroType type = Input.getInputWithMenuBack(Arrays.asList(CharacterHeroType.values()), false);
        if (type == null) {
            return null;
        }
        List<CharacterHero> heroes = new ArrayList<>(GlobalData.getHeroes(type).values());
        return Input.getInputWithMenuBack(heroes, true);
    }
}
