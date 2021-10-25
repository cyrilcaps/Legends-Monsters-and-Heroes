public class Main {
    public static void main(String[] args) {
        // load files
        DataLoader.heroReader();

        Party party = new Party("S", UtilPrintColors.BLUE);
        party.addHero(GlobalData.getRandomHero());
        WorldGame game = new WorldGame();
        game.addParty(party);
        game.play();

        // start

        Input.close();
    }
}
