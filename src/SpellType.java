public enum SpellType {
    ICE("IceSpells.txt", UtilPrintColors.CYAN_BRIGHT),
    FIRE("FireSpells.txt", UtilPrintColors.RED_BRIGHT),
    LIGHTNING("LightningSpells.txt", UtilPrintColors.YELLOW_BRIGHT);

    private final String fileName;
    private final UtilPrintColors stringColor;

    SpellType(String fileName, UtilPrintColors stringColor) {
        this.fileName = fileName;
        this.stringColor = stringColor;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return Util.colorString(stringColor, super.toString());
    }
}
