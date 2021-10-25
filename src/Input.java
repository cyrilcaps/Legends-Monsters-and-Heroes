import java.util.List;
import java.util.Scanner;

public class Input {
    private static final Scanner keyScan = new Scanner(System.in);

    public static void close() {
        keyScan.close();
    }

    public static int getInt(String message) {
        System.out.print(message);

        int val = -1;
        if (keyScan.hasNextInt()) {
            val = keyScan.nextInt();
        }

        // flush scanner non-int and newline
        keyScan.nextLine();
        return val;
    }

    public static String getString(String message) {
        System.out.print(message);
        return keyScan.nextLine();
    }

    public static int getIntWithMenu(List<?> menuOptions, int startIndex) {
        StringBuilder menu = new StringBuilder();
        for (int i = 0; i < menuOptions.size(); i++) {
            menu.append("[").append(i + startIndex).append("] ").append(menuOptions.get(i)).append("\n");
        }
        int input;
        while (true) {
            input = getInt(menu + "Selection? ");
            if (input < startIndex || input > startIndex + menuOptions.size() - 1) {
                System.out.println("Invalid input.");
            } else {
                break;
            }
        }
        return input;
    }

    public static String getStringWithMenu(List<String> menuOptions, int startIndex) {
        int selection = getIntWithMenu(menuOptions, startIndex);
        return menuOptions.get(selection + startIndex);
    }

    public static boolean getConfirm(String message, List<String> confirmStrings) {
        String input = getString(message);
        return confirmStrings.contains(input);
    }
}
