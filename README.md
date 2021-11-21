# Legends-of-Valor

## Student Info
* Names: 
  * Cyril Caparanga 
  * Luke Staib 
  * Kangning Zhang
* Emails: 
  * cyrilc@bu.edu 
  * ljstaib@bu.edu 
  * katzhang@bu.edu   
* BU IDs: 
  * Cyril - U32526192 
  * Luke - U39863533 
  * Kangning - U31078191

## Compilation and Execution Instruction
Developed in Windows environment using IntelliJ IDE.

1. Navigate to the ``src`` directory
2. Enter the following commands:
```
> cd scr
> javac *.java
> java Main
```

## Specifications
Using the provided specifications and configuration files.             

## Bonuses
### Design Patterns
* Factory Pattern
  * EventFactory
  * CharacterFactory
* Iterator Pattern
  * TurnBasedManager

### Miscellaneous
* Added console colors (Used black background during testing): hero types, monster types, spell types, world map
* ASCII art for in welcome page, for combat/market events and for player winning the game.

## Class Descriptions
* ActionCombat: Object for a character's action during combat (i.e. an attack)
* ActionCombatType: Type of combat action taken - attack, spell, potion
* ActionMapType: Type of action taken on the world map - move, none, quit
* ActionWorld: Object for a party's action for their turn in the world game
* Board: Base class for a grid containing squares
* BoardSquare: Base class for a square on the board
* Castable: Interface for a spell to be cast by a player to some target
* Character: Base character class containing name, level, stats, equipment, spells, potions
* CharacterCurrency: Currency object for Character's gold and basic manipulation
* CharacterEquipment: Character's weapon and armor
* CharacterFactory: Factory for creating heroes and monsters from config files
* CharacterHero: Hero base class extending Character, must be of some hero type
* CharacterHeroPaladin: Paladin version of CharacterHero
* CharacterHeroSorcerer: Sorcerer version of CharacterHero, starts with a spell
* CharacterHeroType: Enum for the hero types
* CharacterHeroWarrior: Warrior version of CharacterHero
* CharacterInventory: Character's inventory, manipulation of Character's potions
* CharacterLevel: Managers Character's level and experience
* CharacterMonster: Monster base class extending Character
* CharacterMonsterDragon: Dragon version of CharacterMonster
* CharacterMonsterExoskeleton: Exoskeleton version of CharacterMonster
* CharacterMonsterSpirit: Spirit version of CharacterMonster
* CharacterMonsterType: Enum for the different monster types
* CharacterStats: all the stats for a Character: strength, dexterity, agility, etc.
* Combat: Class handling turn based combat between party and monsters
* CombatBehavior: Behavior Character will take during combat
* CombatPlayer: Determines player's desired action during their combat round
* CombatRandom: Automatic combat action, randomly attacks an enemy
* Consumable: Interface for consuming (a potion), applying some effect on player
* DataLoader: Class for reading and saving data from config files
* Equipable: Interface for equipping items, i.e. weapon and armor
* Event: World event
* EventCombat: Combat event, party will enter into combat versus monsters
* EventFactory: Generates event based on where party is on the world map
* EventInfo: Generates information about status of the hero and allows a hero to equip weapons/armor
* EventPotion: event of hero consuming a potion
* EventShop: Shop event, party will enter a shop with random items
* EventTeleport: the event where a hero teleport to a different lane
* EventType: Enum for event types - combat or shop
* Game: Based class for a game
* GlobalData: Static data store of loaded config files
* Input: Utility class for receiving input from player
* Item: Base class for all items - name, price, level requirement
* ItemArmor: Equipable armor for heroes
* ItemPotion: Consumable potion for heroes
* ItemWeapon: Equipable weapon for heroes
* LegendsMonstersAndHeroes: Entry point for game, does set-up for player
* Main: Program entry point, will start LegendsMonstersAndHeroes 
* MapBehavior: Interface for party behavior on the board/map
* MapBehaviorMonster: Implementation of monster behavior on board/map
* MapSquare: Extends board square, allows grid square to act as a map location
* MapSquareType: Enum for different map locations
* MapToken: Object representing where Player's party is on the map
* Party: Represents the player's collection of heroes
* Spell: Castable spell of a hero, has some effect based on element
* SpellType: Enum for spell elements
* TurnBasedManager: Iterator for determining turn based actions
* Util: Utility class for random methods
* UtilPrintColors: Enum for console colors
* World: Creates a board of MapSquares, used for the world that player will explore
* WorldGame: Game controlling how party explores the world
