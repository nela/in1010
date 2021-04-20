public class Tuppel {
    protected int x;
    protected int y;

    public Tuppel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
