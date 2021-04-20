import java.util.ArrayList;
import java.util.HashMap;

public class SSMapMerger implements Runnable {

    SSMapListMonitor ssMapListMonitor;

    public SSMapMerger(int id, SSMapListMonitor ssMapListMonitor) {
        this.ssMapListMonitor = ssMapListMonitor;
    }

    public void run() {

        this.ssMapListMonitor.awaitSizeInitialization();

        ArrayList<HashMap<String, SubSequence>> toMerge =
            this.ssMapListMonitor.removeTwo();

        while (!this.ssMapListMonitor.allMapsMerged() || toMerge != null) {

            this.ssMapListMonitor.add(SSMapHandler.merge(
                        toMerge.get(0), toMerge.get(1)));
            toMerge = this.ssMapListMonitor.removeTwo();
        }

        this.ssMapListMonitor.signalMergerDone();
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }
}
