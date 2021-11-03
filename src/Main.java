public class Main {
    public static void main(String[] args) {
        // load data from files
        DataLoader.loadAll();

        // start
        LegendsMonstersAndHeroes.start();

        Input.close();
    }
}
