public enum CharacterHeroType {
    WARRIOR("Warriors.txt", UtilPrintColors.RED),
    SORCERER("Sorcerers.txt", UtilPrintColors.BLUE),
    PALADIN("Paladins.txt", UtilPrintColors.YELLOW);

    private final String fileName;
    private final UtilPrintColors stringColor;

    private CharacterHeroType(String fileName, UtilPrintColors stringColor) {
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
