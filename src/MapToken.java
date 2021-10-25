import java.util.Arrays;

public class MapToken {
    private int[] coordinates = new int[2];
    private String symbol;
    private UtilPrintColors color;

    public MapToken() {
    }

    public MapToken(String symbol, UtilPrintColors color) {
        this.symbol = symbol;
        this.color = color;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public UtilPrintColors getColor() {
        return color;
    }

    public void setColor(UtilPrintColors color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + symbol + UtilPrintColors.RESET;
    }
}