public enum CharacterHeroType {
    WARRIOR("Warriors.txt"),
    SORCERER("Sorcerers.txt"),
    PALADIN("Paladins.txt");

    String fileName;

    private CharacterHeroType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
