public class Game {
    private boolean exit;

    public boolean getExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    // play the game once, loop until done or exit=true
    protected void play() {
        boolean done = false;
        while (!done && !exit) {
            if(turn()) {
                done = endTurn();
            }
        }
    }

    public boolean turn() {
        if (isFinished()) {
            return true;
        }
        return false;
    }

    public boolean endTurn() {
        return true;
    }

    public boolean isFinished() {
        return true;
    }

    public void processResult(String winningTeam) {
    }

    private void printTeams() {
        // print round order
    }
}
