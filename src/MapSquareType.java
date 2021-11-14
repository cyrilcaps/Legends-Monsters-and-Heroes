public enum MapSquareType {
    COMMON(" ", UtilPrintColors.RESET),
    INACCESSIBLE("X", UtilPrintColors.GREEN),
    MARKET("M", UtilPrintColors.YELLOW),
    NEXUS("N", UtilPrintColors.RED);

    private final String symbol;
    private final UtilPrintColors color;

    private MapSquareType(String symbol, UtilPrintColors color) {
        this.symbol = symbol;
        this.color = color;
    }

    public String getSymbol() {
        return color + symbol + UtilPrintColors.RESET;
    }
}
