public enum SpellType {
    ICE("IceSpells.txt"),
    FIRE("FireSpells.txt"),
    LIGHTNING("LightningSpells.txt");

    private final String fileName;

    SpellType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
