import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader {
    private static final String DATA_DIRECTORY = "Legends_Monsters_and_Heroes" + File.separator;

    public static void loadAll() {
        heroReader();
        monsterReader();
        armorReader();
        weaponReader();
        potionsReader();
        spellsReader();
    }

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

                    // load hero based on type
                    CharacterHero hero = CharacterFactory.generateHero(type, vals);
                    if (hero == null) {
                        continue;
                    }
                    GlobalData.getHeroes(type).put(hero.getName(), hero);
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

                    CharacterMonster monster = CharacterFactory.generateMonster(type, vals);
                    if (monster == null) {
                        continue;
                    }
                    GlobalData.getMonsters().put(monster.getName(), monster);
                }
            } catch (IOException e) {
                // do nothing
                System.out.println("Failed to read " + fileName);
            }
        }
    }


    public static void armorReader() {
        String fileName = "Armory.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_DIRECTORY + fileName))) {
            // parse file - 1st line is header
            String line = br.readLine();

            // monster format is Name/level/damage/defense/dodge chance
            while ((line = br.readLine()) != null) {
                String[] vals = line.split("\\s+");

                ItemArmor armor = new ItemArmor(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]));
                GlobalData.getArmors().put(armor.getName(), armor);
            }
        } catch (IOException e) {
            // do nothing
            System.out.println("Failed to read " + fileName);
        }
    }

    public static void weaponReader() {
        String fileName = "Weaponry.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_DIRECTORY + fileName))) {
            // parse file - 1st line is header
            String line = br.readLine();

            // monster format is Name/level/damage/defense/dodge chance
            while ((line = br.readLine()) != null) {
                String[] vals = line.split("\\s+");

                ItemWeapon weapon = new ItemWeapon(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        Integer.parseInt(vals[3]),
                        Integer.parseInt(vals[4]));
                GlobalData.getWeapons().put(weapon.getName(), weapon);
            }
        } catch (IOException e) {
            // do nothing
            System.out.println("Failed to read " + fileName);
        }
    }

    public static CharacterStats statReader(int value, String statString) {
        CharacterStats characterStats = new CharacterStats();
        statString = statString.toUpperCase();
        String[] stats = statString.split("/");
        for (String stat : stats) {
            switch (stat) {
                case "STRENGTH":
                    characterStats.setStrength(value);
                    break;
                case "AGILITY":
                    characterStats.setAgility(value);
                    break;
                case "DEXTERITY":
                    characterStats.setDexterity(value);
                    break;
                case "HEALTH":
                    characterStats.setHealth(value);
                    break;
                case "MANA":
                    characterStats.setMana(value);
                    break;
            }
        }
        return characterStats;
    }

    public static void potionsReader() {
        String fileName = "Potions.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_DIRECTORY + fileName))) {
            // parse file - 1st line is header
            String line = br.readLine();

            // monster format is Name/level/damage/defense/dodge chance
            while ((line = br.readLine()) != null) {
                String[] vals = line.split("\\s+");

                ItemPotion consumable = new ItemPotion(vals[0],
                        Integer.parseInt(vals[1]),
                        Integer.parseInt(vals[2]),
                        statReader(Integer.parseInt(vals[3]), vals[4]));
                GlobalData.getPotions().put(consumable.getName(), consumable);
            }
        } catch (IOException e) {
            // do nothing
            System.out.println("Failed to read " + fileName);
        }
    }

    public static void spellsReader() {
        for (SpellType type : SpellType.values()) {
            String fileName = type.getFileName();
            try (BufferedReader br = new BufferedReader(new FileReader(DATA_DIRECTORY + fileName))) {
                // parse file - 1st line is header
                String line = br.readLine();

                // monster format is Name/level/damage/defense/dodge chance
                while ((line = br.readLine()) != null) {
                    String[] vals = line.split("\\s+");
                    Spell spell = new Spell(vals[0],
                            Integer.parseInt(vals[1]),
                            Integer.parseInt(vals[2]),
                            Integer.parseInt(vals[3]),
                            Integer.parseInt(vals[4]),
                            type);
                    GlobalData.getSpells().put(spell.getName(), spell);
                }
            } catch (IOException e) {
                // do nothing
                System.out.println("Failed to read " + fileName);
            }
        }
    }
}
