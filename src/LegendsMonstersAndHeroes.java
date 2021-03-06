import java.util.*;
import java.util.stream.Collectors;

public class LegendsMonstersAndHeroes {
    public static void start() {
        // load data from files
        DataLoader.loadAll();

        // welcome
        System.out.println("              />\n" +
                " (           //------------------------------------------------------\\\n" +
                "(*)OXOXOXOXO(*>             LEGENDS OF VALOR                          \\\n" +
                " (           \\\\------------------------------------------------------/\n" +
                "              \\>");
        System.out.println("Welcome! Please set-up your party.");


        // Don't need to get symbol for this implementation
        //Symbols are always H1, H2, H3 for the three heroes
//        String symbol;
//        do {
//            symbol = Input.getString("Enter single character for your map symbol: ");
//        } while (symbol.length() != 1);
//        symbol = symbol.toUpperCase();

        // get color
        List<UtilPrintColors> colors = Arrays.asList(UtilPrintColors.RED,
                UtilPrintColors.BLUE,
                UtilPrintColors.PURPLE,
                UtilPrintColors.CYAN);
        List<String> colorStrings = colors.stream().map(c -> c + c.name() + UtilPrintColors.RESET)
                .collect(Collectors.toList());
        System.out.println("Choose a display color for the heroes:");
        int selection = Input.getIntWithMenu(colorStrings, 1);
        UtilPrintColors color  = colors.get(selection);

        // init party - party class for each hero

        System.out.println("Select three heroes!\n");
        int partyCount = 3;
        ArrayList<Party> heroParties = new ArrayList<>();
        Map<String, CharacterHero> heroes = new HashMap<>();
        while (heroes.size() < partyCount) {
            System.out.println("Selecting hero " + (heroes.size() + 1) + "!");
            CharacterHero hero = heroSelector();
            if (hero != null) {
                if (heroes.put(hero.getName(), hero) != null) {
                    System.out.println(hero.getName() + " has already been selected.");
                } else {
                    // Give heroes symbols H1, H2, H3
                    Party party = new Party("H" + (heroes.size()), color);
                    party.addHero(hero);
                    heroParties.add(party);
                }
            }
        }

        // init world
        WorldGame game = new WorldGame();
        //Add 3 parties -> 3 heroes, to the map
        int lane = 0;
        for (Party heroParty : heroParties) {
            game.addHero(heroParty, lane++);
        }
        Board<MapSquare> map = game.getWorld().getMap();
        //Passing map in order to teleport
        game.play(map);

        //Stats message
        System.out.println("\n************************************\n");
        System.out.println("Thank you for playing!");
        System.out.println("The world will remember the heroic efforts of your party:");
        CharacterHero character;
        boolean pluralFlag = true;
        for (int i = 0; i < partyCount; i++) {
            System.out.println("Hero " + (i + 1) + ": ");
            character = (CharacterHero) heroParties.get(i).getCharacter();
            System.out.println("\t" + Util.colorString(character.getType().getStringColor(), character.getName()
                    + " " + character.getType().name() + " Lvl " + character.getLevel().getLevel()));
            if (heroParties.get(i).getWins() == 1) {
                pluralFlag = false;
            }
            System.out.print("\tYou defeated " + heroParties.get(i).getWins() + " monster");
            if (pluralFlag) { System.out.print("s"); }
            System.out.println(".");
            pluralFlag = true;
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
