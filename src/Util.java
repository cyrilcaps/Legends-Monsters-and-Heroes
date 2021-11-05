public class Util {
    public static String colorString(UtilPrintColors color, String message) {
        return color.getColor() + message + UtilPrintColors.RESET.getColor();
    }
}
