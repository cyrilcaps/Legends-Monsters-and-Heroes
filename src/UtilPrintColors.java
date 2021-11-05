public enum UtilPrintColors {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    RED_BOLD("\033[1;31m"),
    YELLOW_BOLD("\033[1;33m"),
    WHITE_BOLD("\033[1;37m"),
    RED_BRIGHT("\033[0;91m"),
    YELLOW_BRIGHT("\033[0;93m"),
    CYAN_BRIGHT("\033[0;96m");

    private final String color;

    UtilPrintColors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color;
    }
}
