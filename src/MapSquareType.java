public enum MapSquareType {
    PLAIN("P", UtilPrintColors.RESET),
    INACCESSIBLE("X", UtilPrintColors.GREEN),
    MONSTERNEXUS("N", UtilPrintColors.RED),
    HERONEXUS("N", UtilPrintColors.RED),
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
