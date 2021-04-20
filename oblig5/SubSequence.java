public class SubSequence {
    private String sequence;
    private int occurences = 1;

    public SubSequence(String sequence) {
        this.sequence = sequence;
    }

    public String key() { return this.sequence; }
    public int count() { return this.occurences; }
    public int addOne() { return this.occurences++;  }
    public int add(int count) { return this.occurences += count; }
}
