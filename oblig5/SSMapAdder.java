import java.util.List;

public class SSMapAdder implements Runnable {

    SSMapListMonitor ssMapListMonitor;
    List<String> filenames;
    String datapath;
    int ssLength;

    public SSMapAdder(SSMapListMonitor ssMapListMonitor, List<String> filenames,
            String datapath, int ssLength) {
        this.ssMapListMonitor = ssMapListMonitor;
        this.filenames = filenames;
        this.datapath = datapath;
        this.ssLength = ssLength;
    }

    public void run() {

        this.ssMapListMonitor.signalSize(filenames.size());
        this.ssMapListMonitor.awaitSizeInitialization();

        for (String line : filenames) {
            this.ssMapListMonitor.add(
                    SSMapHandler.readPatientFile(datapath + line, ssLength));
        }
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }
}
