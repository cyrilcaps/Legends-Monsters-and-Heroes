import java.util.Arrays;

public class MapToken {
    private int[] coordinates = new int[2];
    private String symbol;
    // for Valor, the token is H1, H2, H3 and M1, M2, M3.
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
        if(symbol.length()<3){
            return color + symbol + UtilPrintColors.RESET+" ";
        }
        return color + symbol + UtilPrintColors.RESET;
    }
}
