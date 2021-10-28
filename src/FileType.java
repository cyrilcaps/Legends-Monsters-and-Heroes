public enum FileType {
    ICE("IceSpells.txt", Spell.class),
    FIRE("FireSpells.txt", Spell.class),
    LIGHTNING("LightningSpells.txt", Spell.class);

    private final String fileName;
    private final Class<?> aClass;

    FileType(String fileName, Class<?> aClass) {
        this.fileName = fileName;
        this.aClass = aClass;
    }

    public String getFileName() {
        return fileName;
    }

    public Class<?> getaClass() {
        return aClass;
    }
}
