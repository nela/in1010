public class LabyrintKnapp {
    private final int x;
    private final int y;
    String color = "";

    public LabyrintKnapp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(char c) {
        if (c == 's') color = "svart";
        else if (c == 'h') color = "hvit";
        else if (c == 'b') color = "blaa";
    }

    public void getColor() { return this.color; }

    public void initGui() {}
}
