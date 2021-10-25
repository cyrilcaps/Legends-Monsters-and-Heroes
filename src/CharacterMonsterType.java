public enum CharacterMonsterType {
    DRAGON("Dragons.txt"),
    EXOSKELETON("Exoskeletons.txt"),
    SPIRIT("Spirits.txt");

    String fileName;

    private CharacterMonsterType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
