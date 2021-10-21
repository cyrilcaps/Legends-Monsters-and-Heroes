import java.util.*;

public class Combat {
    private final List<CharacterHero> heroes = new ArrayList<>();
    private final List<CharacterHero> monsters = new ArrayList<>();
    private int totalCharacters;
    private int round;
    private boolean exit;

    public boolean getExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public Character getCurrentPlayer() {
        Character currentCharacter;
        int selection = round / 2;
        if (round % 2 == 1) {
            selection = selection % heroes.size();
            currentCharacter = heroes.get(selection);
        } else {
            selection = selection % monsters.size();
            currentCharacter = monsters.get(selection);
        }
        return currentCharacter;
    }

    // play the game once, loop until done or exit=true
    protected void fight() {
        boolean done = false;
        while (!done && !exit) {
            if(turn()) {
                done = endTurn();
            }
        }
    }

    public boolean turn() {
        if(isFinished()) {
            return true;
        }

        round += 1;
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
