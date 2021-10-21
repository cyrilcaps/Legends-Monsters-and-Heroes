public class Util {
    public static void print(UtilPrintColors color, String message) {
        System.out.println(color.getColor() + message + UtilPrintColors.RESET.getColor());
    }
}
