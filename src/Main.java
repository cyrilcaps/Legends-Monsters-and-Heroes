public class Main {
    public static void main(String[] args) {
        // load data from files
        DataLoader.heroReader();

        // start
        LegendsMonstersAndHeroes.start();

        Input.close();
    }
}
