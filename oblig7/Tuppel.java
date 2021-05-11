public class Tuppel {
    protected int y;
    protected int x;

    public Tuppel(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
