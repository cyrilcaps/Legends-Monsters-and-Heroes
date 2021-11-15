import java.util.*;

public class TurnBasedManager<E> implements Iterator<E> {
    // allow teams
    private final List<List<E>> teams = new ArrayList<>();
    private int selector = -1;
    private boolean exit;

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

    @Override
    public boolean hasNext() {
        return !teams.isEmpty();
    }

    @Override
    public E next() {
        selector += 1;

        // select team
        int team = selector % teams.size();

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
