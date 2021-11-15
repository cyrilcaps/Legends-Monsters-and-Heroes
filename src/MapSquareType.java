public enum MapSquareType {
    PLAIN("P", UtilPrintColors.RESET),
    INACCESSIBLE("X", UtilPrintColors.RED),
    MONSTERNEXUS("N", UtilPrintColors.GREEN),
    HERONEXUS("N", UtilPrintColors.GREEN),
    BUSH("B", UtilPrintColors.CYAN),
    CAVE("C", UtilPrintColors.PURPLE),
    KOULOU("K", UtilPrintColors.YELLOW);

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
