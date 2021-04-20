import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class SSMapList {
    private int count;
    public ArrayList<HashMap<String, SubSequence>> ssMapList;

    public SSMapList() {
        this.count = 0;
        this.ssMapList = new ArrayList<HashMap<String, SubSequence>>();
    }

    public int size() { return this.count; }

    public int add(HashMap<String, SubSequence> ssMap) {
        this.ssMapList.add(ssMap);
        return ++this.count;
    }

    public HashMap<String, SubSequence> remove() {
        this.count--;
        return this.ssMapList.remove(0);
    }

    public void printSubSequences() {
        int i = 1;
        for (HashMap<String, SubSequence> map : this.ssMapList) {
            System.out.println("HashMap: " + i);
            for(Map.Entry<String, SubSequence> e : map.entrySet()) {
                SubSequence ss = e.getValue();
                System.out.println("Key: " + e.getKey() + " SSKey: " + ss.key()
                        + " Count: " + ss.count());
            }
            i++;
        }

    }

    public HashMap<String, SubSequence> get(int index) {
        return this.ssMapList.get(0);
    }
}

