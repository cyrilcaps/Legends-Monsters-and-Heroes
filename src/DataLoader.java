import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader {
    private static final String DATA_DIRECTORY = "Legends_Monsters_and_Heroes" + File.separator;

    public static void heroReader() {
        for (CharacterHeroType type : CharacterHeroType.values()) {
            String fileName = type.getFileName();
            try (BufferedReader br = new BufferedReader(new FileReader(DATA_DIRECTORY + fileName))) {
                // parse file - 1st line is header
                br.readLine();

                // Name/mana/strength/agility/dexterity/starting money/starting experience
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    String[] vals = line.split("\\s+");

                    CharacterHero hero = new CharacterHero(vals[0],
                            Integer.parseInt(vals[1]),
                            Integer.parseInt(vals[2]),
                            Integer.parseInt(vals[3]),
                            Integer.parseInt(vals[4]),
                            Integer.parseInt(vals[5]),
                            Integer.parseInt(vals[6]),
                            type);
                    GlobalData.getHeroes().put(hero.getName(), hero);
                }
            } catch (IOException e) {
                // do nothing
                System.out.println("Failed to read " + fileName);
            }
        }
    }

    public static void monsterReader() {
        for (CharacterMonsterType type : CharacterMonsterType.values()) {
            String fileName = type.getFileName();
            try (BufferedReader br = new BufferedReader(new FileReader(DATA_DIRECTORY + fileName))) {
                // parse file - 1st line is header
                String line = br.readLine();

                // monster format is Name/level/damage/defense/dodge chance
                while ((line = br.readLine()) != null) {
                    String[] vals = line.split("\\s+");

                    CharacterMonster monster = new CharacterMonster(vals[0],
                            Integer.parseInt(vals[1]),
                            Integer.parseInt(vals[2]),
                            Integer.parseInt(vals[3]),
                            Integer.parseInt(vals[4]),
                            type);
                    GlobalData.getMonsters().put(monster.getName(), monster);
                }
            } catch (IOException e) {
                // do nothing
                System.out.println("Failed to read " + fileName);
            }
        }
    }
}
