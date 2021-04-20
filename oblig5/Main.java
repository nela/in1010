import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Main {
    public  static int ADDERS;
    public  static int MERGERS;
    public  static int SSLENGTH;
    public  static String DATAPATH;

    public static void main (String[] args) {

        if (args.length != 8)
            System.out.println("Incorrect program arguments.\n" +
                    "java Main --adders <number-of-adder-threads> " +
                    "--mergers <number-of-merger-threads> " +
                    "--ss-length <subsequence-length> " +
                    "--datapath <path-do-data-directory> \nFor instance:\n" +
                    "java Main --adders 3 --mergers 6 --ss-length 3 --datapath Data/");


        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("--adders") || args[i].equals("-a"))
                ADDERS = Integer.parseInt(args[i+1]);
            else if ((args[i].equals("--mergers") || args[i].equals("-m")))
                MERGERS = Integer.parseInt(args[i+1]);
            else if ((args[i].equals("--ss-length") || args[i].equals("-s")))
                SSLENGTH = Integer.parseInt(args[i+1]);
            else if (args[i].equals("--datapath") || args[i].equals("-d"))
                DATAPATH = args[i+1];
        }

        System.out.println("Adder threads:\t\t\t" + ADDERS +
                "\nMerger threads:\t\t\t" + MERGERS +
                "\nSubSequence length:\t\t" + SSLENGTH +
                "\nDatapath:\t\t\t" + DATAPATH + "\n");


        ArrayList<ArrayList<String>> sortedFilenames = readFile(
                DATAPATH + "metadata.csv");
        ArrayList<String> infectedFilenames = sortedFilenames.get(0);
        ArrayList<String> healthyFilenames = sortedFilenames.get(1);

        if (ADDERS > infectedFilenames.size() + healthyFilenames.size()) {
            System.out.println(
                    "Number of adder threads should not exceed number of files");
            System.exit(1);
        }

        int infectedAdders = (ADDERS % 2 != 0) ?
            1 + (ADDERS / 2) : ADDERS / 2;
        int healthyAdders = ADDERS / 2;

        int infectedMergers = (MERGERS % 2 != 0) ?
            1 + (MERGERS / 2) : MERGERS / 2;
        int healthyMergers = MERGERS / 2;

        SSMapListMonitor infectedPool = new SSMapListMonitor(
                infectedAdders, infectedMergers, true);

        SSMapListMonitor healthyPool = new SSMapListMonitor(
                healthyAdders, healthyMergers, false);

        startThreads(infectedPool, infectedFilenames, DATAPATH,
                infectedAdders, infectedMergers, SSLENGTH);

        startThreads(healthyPool, healthyFilenames, DATAPATH,
                healthyAdders, healthyMergers, SSLENGTH);

        infectedPool.mergersAwait();
        healthyPool.mergersAwait();

        SSMapHandler.findDominantSubSequences(infectedPool.getMergedHashMap(),
                healthyPool.getMergedHashMap());

        System.out.println("Terminerer..");

    }

    public static void startThreads (SSMapListMonitor ssMapListMonitor,
            ArrayList<String> filenames, String datapath, int adders,
            int mergers, int subSequenceLength) {

        int entriesPrThread = filenames.size() / adders;
        int index = 0;

        // Adder Threads
        for (int i = 0; i < adders; i++) {
            int entries = (i == adders - 1) ?
                filenames.size() - index : entriesPrThread;

            int start = index;
            int end = index + entries;

            SSMapAdder mapAdder = new SSMapAdder(ssMapListMonitor,
                    filenames.subList(start, end),
                    datapath, subSequenceLength);

            mapAdder.start();
            index = end;
        }

        // Merger Threads
        for (int i = 0; i < mergers; i++) {
            SSMapMerger mapMerger = new SSMapMerger(i, ssMapListMonitor);
            mapMerger.start();
        }
    }

    public static ArrayList<ArrayList<String>> readFile(String path) {
        Scanner file = null;
        try {
            file = new Scanner(new File(path));
        } catch(Exception e){
            e.printStackTrace();
        }

        // Ignore first line
        file.nextLine();
        ArrayList<String> infected = new ArrayList<String>();
        ArrayList<String> healthy = new ArrayList<String>();

        while(file.hasNextLine()) {

            String[] s = file.nextLine().split(",");

            if (s[1].equals("True")) infected.add(s[0]);
            else healthy.add(s[0]);
        }

        return new ArrayList<>(Arrays.asList(infected, healthy));
    }

}
