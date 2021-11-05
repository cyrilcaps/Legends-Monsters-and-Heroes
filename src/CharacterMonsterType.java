public enum CharacterMonsterType {
    DRAGON("Dragons.txt", UtilPrintColors.GREEN),
    EXOSKELETON("Exoskeletons.txt", UtilPrintColors.WHITE_BOLD),
    SPIRIT("Spirits.txt", UtilPrintColors.CYAN);

    String fileName;
    UtilPrintColors stringColor;

    private CharacterMonsterType(String fileName, UtilPrintColors stringColor) {
        this.fileName = fileName;
        this.stringColor = stringColor;
    }

    public String getFileName() {
        return fileName;
    }

    public UtilPrintColors getStringColor() {
        return stringColor;
    }

    @Override
    public String toString() {
        return Util.colorString(stringColor, super.toString());
    }
}
