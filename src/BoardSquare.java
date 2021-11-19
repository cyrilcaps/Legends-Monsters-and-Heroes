public abstract class BoardSquare {
    private boolean explored; //Set to true if square has been explored, false if not explored, used for teleport

    public BoardSquare() {
        this.explored = false;
    }

    public boolean getExplored() { return explored; }

    public void setExplored(boolean explored) { this.explored = explored; }
}
