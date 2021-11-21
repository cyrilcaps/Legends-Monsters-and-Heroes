import java.util.*;

public class TurnBasedManager<E> implements Iterator<E> {
    // allow teams
    private final List<List<E>> teams = new ArrayList<>();
    private int selector = -1;
    private int round = -1;
    private boolean startRound = false;

    public TurnBasedManager() {

    }

    public List<List<E>> getTeams() {
        return teams;
    }

    public void addTeam(List<E> team) {
        teams.add(team);
    }

    public int getSelector() {
        return selector;
    }

    public int getRound() {
        return round;
    }

    public boolean isStartRound() {
        return startRound;
    }

    @Override
    public boolean hasNext() {
        return !teams.isEmpty();
    }

    @Override
    public E next() {
        startRound = false;
        selector += 1;

        // select team
        int team = selector % teams.size();

        // increment round when team 0 goes
        if (team == 0) {
            round += 1;
            startRound = true;
        }

        // select player in team
        int player = team/teams.size() % teams.get(team).size();

        return teams.get(team).get(player);
    }

    public List<E> getTeam(int i) {
        return teams.get(i);
    }

    public List<E> getNextTeam() {
        // select team
        int team = (selector + 1) % teams.size();
        return teams.get(team);
    }
}
