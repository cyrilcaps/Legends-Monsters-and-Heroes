import java.util.*;
import java.util.stream.Collectors;

public class WorldGame extends Game {
    private final World world = new World(8);
    private final List<Party> heroPartyList = new ArrayList<>();
    private final Map<String, Party> parties = new HashMap<>();
    private final TurnBasedManager<Party> turnBasedManager = new TurnBasedManager<>();

    public WorldGame() {
    }

    public World getWorld() {
        return world;
    }

    public void addParty(Party party) {
        parties.put(party.getToken().getSymbol(), party);
    }

    public void addHero(Party party, int lane) {
        world.spawnTokenHeroNexus(party.getToken(), lane);
        heroPartyList.add(party);
        addParty(party);
    }

    public void addMonster(Party party, int lane) {
        boolean valid = world.spawnTokenMonsterNexus(party.getToken(), lane);
        if (!valid) {
            System.out.println("Could not add monster, monster nexus is full!");
            return;
        }
        addParty(party);
    }

    //Took away override to pass map, need to see which tiles have been explored for teleport to work
    public void play(Board<MapSquare> map) {
        for (Party value : heroPartyList) {
            List<Party> oneHeroParty = new ArrayList<>();
            oneHeroParty.add(value);
            turnBasedManager.addTeam(oneHeroParty);
        }

        // boolean for start of turn actions
        boolean start = true;
        int monsterRound = 0;

        // start turn based
        Party party = turnBasedManager.next();
        while (turnBasedManager.hasNext()) {
            // turn start
            if (start) {
                // spawn monsters every 7 rounds and on start
                if (turnBasedManager.getRound() == monsterRound) {
                    int averageLevel = 0;
                    for (Party heroParty : heroPartyList) {
                        averageLevel += heroParty.getCharacter().getLevel().getLevel();
                    }
                    averageLevel = averageLevel / heroPartyList.size();

                    for (int i = 0; i < 3; i++) {
                        Party monsterParty = new Party("M" + ((turnBasedManager.getRound()/7 * 3) + i + 1),
                                UtilPrintColors.RED_BOLD_BRIGHT);
                        monsterParty.setMonster(true);
                        monsterParty.addHero(CharacterFactory.generateMonster(averageLevel, 1).get(0));
                        monsterParty.setBehavior(new MapBehaviorMonster());
                        addMonster(monsterParty, i);

                        turnBasedManager.addTeam(Collections.singletonList(monsterParty));
                    }
                    monsterRound += 7;
                }

                // heroes, start of round - respawn or recover
                if (party.isHero()) {
                    if (party.getCharacter().isFainted()) {
                        System.out.println("Respawning hero: " + party.getToken());
                        // perform back action for free
                        party.getCharacter().getStats().addHealth(party.getCharacter().getStats().getMaxHealth() / 2);
                        world.move(party.getToken(), 7, party.getToken().getCoordinates()[1]);
                    } else {
                        // recover some health/mana
                        ((CharacterHero) party.getCharacter()).recover();
                    }
                }

                // print map after start of turn actions
                world.printMap();
            }
            start = false;

            System.out.println("Turn = " + party.getToken() + " starting at (" +
                    party.getToken().getCoordinates()[0] + ", " + party.getToken().getCoordinates()[1] + ")");
            System.out.println(party.getCharacter().toString());

            // get action
            // Pass all parties as input to check position of all heroes to teleport
            ActionWorld action = party.move(map);

            // quit will exit play
            boolean valid = false;
            boolean turnEnded = false;
            switch (action.getType()) {
                case END:
                    turnEnded = true;
                    break;
                case MOVE:
                    if (!party.isHasMoved()) {
                        //first remove the buff given by the previous cell
                        parties.get(party.getToken().getSymbol()).getCharacter().getStats().removeBuffs();
                        valid = world.move(party.getToken(), action.getCoordinates()[0], action.getCoordinates()[1]);
                        if (valid) {
                            party.setHasMoved(true);
                            if(party.isHero()){
                                //add new buffs(doesn't matter moved or not)
                                int[] newposition = party.getToken().getCoordinates();
                                MapSquare newsquare = world.getMapSquare(newposition[0], newposition[1]);
                                parties.get(party.getToken().getSymbol()).getCharacter().getStats().cellBuff(newsquare.getType());
                            }

                        } else {
                            // invalid move for monster = done moving
                            if (!party.isHero()) {
                                party.setHasMoved(true);
                            }
                        }
                    } else {
                        System.out.println("You have already moved!");
                    }
                    break;
                case ATTACK:
                    attack(party);
                    break;
                case SPELL:
                    cast(party);
                    break;
                case MAP:
                    world.printMap();
                    break;
                case NONE:
                    break;
                case QUIT:
                    return;
            }

            // print new location
//            world.printMap();

            // resolve new square - market or common
            if (valid) {
                // generate event for new location
                MapSquare currentSquare = world.getMapSquare(party.getToken());
                Event event = EventFactory.generateEvent(currentSquare.getType(),
                        new ArrayList<>(party.getHeroes().values()));

                // enter event if exists
                if (event != null) {
                    if (party.isHero()) {
                        event.enter(party);
                    }

                    // print location
//                    world.printMap();
                }
            }
            //check win here after an event.
            int[] currentposition = party.getToken().getCoordinates();
            if(party.isHero()){
                if(currentposition[0] == 0){
                    world.printMap();
                    System.out.println("********************************************************\n");
                    System.out.println("A hero has entered the Monster Nexus and destroyed it!!\n");
                    System.out.println("Heroes won the game!!!\n");
                    System.out.println("********************************************************");
                    return;
                }
            }else{
                if(currentposition[0] ==7){
                    world.printMap();
                    System.out.println("*************************************************\n");
                    System.out.println("Oh, no! A Monster has entered the Hero Nexus\n");
                    System.out.println("Monsters now rule the world!!!\n");
                    System.out.println("YOU LOSE!!!\n");
                    System.out.println("************************************************");
                    return;
                }
            }


            // Next turn if:
            // Hero ends turn
            // Forced to end turn if: attacked or moved
            if (turnEnded) {
                party.setHasMoved(false);
                party.setHasAttacked(false);

                party = turnBasedManager.next();
                start = true;
            }

        }
    }

    // can do attack/spell if character not attacked and targets available
    private boolean canAttack(Party party, List<Character> targets) {
        if (party.isHasAttacked()) {
            System.out.println(party.getToken().getSymbol() + " has already attacked!");
            return false;
        }
        if (targets.isEmpty()) {
            System.out.println("No targets nearby");
            return false;
        }

        return true;
    }

    private void attack(Party party) {
        List<Party> parties = getCharactersInRange(party.getToken(), party.isHero());
        List<Character> targets = parties.stream().map(Party::getCharacter).collect(Collectors.toList());
        if (canAttack(party, targets)) {
            // monster = attack first target, hero = use target selector
            Character target = targets.get(0);
            if (party.isHero()) {
                target = CombatPlayer.targetSelector(targets);
            }

            // apply damage to target
            if (target != null) {
                party.setHasAttacked(true);
                //Changed to target instead of target.getName(), prevents attacking every monster with the same name bug
                //Ex: M1 and M3 named "Blinky", both would get damaged
                target.applyCombat(new ActionCombat(ActionCombatType.ATTACK,
                        party.getCharacter().getDamage(), target));

                // remove fainted tokens
                resolveFainted(party, parties, target);
            }
        }
        if (!party.isHero()) {
            party.setHasAttacked(true);
        }
    }

    private void cast(Party party) {
        List<Party> parties = getCharactersInRange(party.getToken(), party.isHero());
        List<Character> targets = parties.stream().map(Party::getCharacter).collect(Collectors.toList());
        if (canAttack(party, targets)) {
            ActionCombat spellAction = (new CombatPlayer()).castSpell(targets, party.getCharacter());
            if (spellAction != null) {
                party.setHasAttacked(true);

                // remove fainted tokens
                resolveFainted(party, parties, spellAction.getTarget());
            }
        }
    }

    private void resolveFainted(Party party, List<Party> targetParties, Character target) {
        if (target.isFainted()) {
            for (Party targetParty : targetParties) {
                if (targetParty.getCharacter().getName().equals(target.getName())) {
                    // remove target from world if fainted
                    world.removeToken(targetParty.getToken());

                    // reward party if character hero
                    if (party.getCharacter() instanceof CharacterHero) {
                        //Add 1 win
                        //System.out.println("Win added");
                        party.addWin();

                        //Reward the lone hero
                        party.getCharacter().endCombat(target.getLevel().getLevel() * 100, 2);
                    }
                    break;
                }
            }
        }
    }

    private List<Party> getCharactersInRange(MapToken token, boolean getMonsters) {
        List<Party> parties = new ArrayList<>();

        int range = 1;
        int tokenX = token.getCoordinates()[0];
        int tokenY = token.getCoordinates()[1];
        for (int i = tokenX - range ; i < tokenX + range + 1; i++) {
            for (int j = tokenY - range; j < tokenY + range + 1; j++) {
                MapSquare square = world.getMapSquare(i, j);

                if (square != null && !square.isEmpty()) {
                    if(getMonsters){
                        if(square.hasMonster()){
                            String name = square.getMonster().get(0).getSymbol();
                            parties.add(this.parties.get(name));
                        }

                    }else{
                        if(square.hasHero()){
                            String name = square.getHero().get(0).getSymbol();
                            parties.add(this.parties.get(name));
                        }

                    }
                }
            }
        }
        return parties;
    }
}
