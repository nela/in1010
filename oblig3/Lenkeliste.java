public class Lenkeliste<T> implements Liste<T> {

    private class Node {
        public Node next;
        public Node prev;
        public T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    public int stoerrelse() { return this.size; }

    public void leggTil(int pos, T x) {
        if (pos < 0 || pos > this.size) { throw new UgyldigListeIndeks(pos); }

        if ((pos == 0 && this.size == 0) || pos == this.size) {
            leggTil(x);
            return;
        }

        Node newNode = new Node(x);
        Node oldNode = hentNode(pos);

        if (oldNode.prev != null) {
            oldNode.prev.next = newNode;
            newNode.prev = oldNode.prev;
        }

        newNode.next = oldNode;
        oldNode.prev = newNode;

        if (newNode.prev == null) { this.head = newNode; }
        if (oldNode.next == null) { this.tail = oldNode; }

        this.size++;
        return;
    }

    public void leggTil(T x) {
        Node node = new Node(x);

        if (this.head == null) {
            this.head = node;
            this.tail = this.head;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
        return;
    }


    public void sett(int pos, T x) {
        Node node = hentNode(pos);
        node.data = x;
    }

    public T hent(int pos) {
        Node node = hentNode(pos);
        return node.data;
    }

    private Node hentNode(int pos) {
        if (this.head == null || this.size <= 0 || pos >= this.size || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }

        if (pos == 0) { return this.head; }
        else if (pos == this.size - 1) {
            return this.tail;
        }

        boolean b = pos < (this.size / 2); // Nærmest start eller slutt
        Node node;

        if (b) {
            node = this.head;
            for (int i = 0; i < pos; i++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int i = this.size - 1; i > pos; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    public T fjern(int pos) {
        if (this.size <= 0 || pos >= this.size) throw new UgyldigListeIndeks(pos);
        return fjernNode(pos).data;
    }

    private Node fjernNode(int pos) {

        if (pos == 0) {
            this.size--;
            return this.head;
        }

        Node node = hentNode(pos);

        Node prev = node.prev;
        Node next = node.next;

        if (prev != null) prev.next = next;
        if (next != null) next.prev = prev;
        if (prev.next == null) this.tail = prev;

        this.size--;
        return node;
    }

    public T fjern() {
        return fjernNode().data;
    }

    private Node fjernNode() {
        if (this.size <= 0) { throw new UgyldigListeIndeks(-1);}

        Node node = head;

        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } else {
            this.head.next.prev = null;
            this.head = this.head.next;
        }

        this.size--;
        return node;
    }

    @Override
    public String toString() {
        if (this.size == 0) return ("List is empty.");

        StringBuilder builder = new StringBuilder();

        Node node = head;
        for (int i = 0; i < this.size; i++) {
            String idx = "\n\nNode index:\t\t" + i;
            String prev = "\nNode prev:\t\t" + String.valueOf(node.prev);
            String next = "\nNode next:\t\t" + String.valueOf(node.next);
            String data = "\nNode data:\t\t" + String.valueOf(node.data);

            builder.append(idx).append(prev).append(next).append(data);
            node = node.next;
        }

        return builder.append("\n\n").toString();
    }
}
