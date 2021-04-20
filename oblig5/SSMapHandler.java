import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SSMapHandler {

    public static void findDominantSubSequences(HashMap<String, SubSequence> infected,
            HashMap<String, SubSequence> healthy) {

        for (Map.Entry<String, SubSequence> entry : infected.entrySet()) {
            String genome = entry.getKey();

            SubSequence ssHealthy = healthy.getOrDefault(genome, null);

            if (ssHealthy != null) {
                int diff = entry.getValue().count() - ssHealthy.count();
                if (diff >= 5) System.out.println("SS: " + genome + " Diff: " + diff);
            }
        }


    }

    public static HashMap<String, SubSequence> merge(
            HashMap<String, SubSequence> ssMap1,
            HashMap<String, SubSequence> ssMap2) {
        HashMap<String, SubSequence> ssMapNew = new HashMap<String, SubSequence>();

        SubSequence ssRemoved;
        for (SubSequence ss : ssMap1.values()) {
            ssRemoved = ssMap2.remove(ss.key());

            if (ssRemoved == null) {
                ssMapNew.put(ss.key(), ss);
            } else {
                ss.add(ssRemoved.count());
                ssMapNew.put(ss.key(), ss);
            }
        }

        for (SubSequence ss : ssMap2.values()) ssMapNew.put(ss.key(), ss);

        return ssMapNew;
    }

    public static HashMap<String, SubSequence> readPatientFile(
            String filepath, int ssLength) {

        String line, genome;
        HashMap<String, SubSequence> ssMap = new HashMap<String, SubSequence>();

        try (Scanner reader = new Scanner(new File(filepath))) {

            while(reader.hasNextLine()) {
                line = reader.nextLine();
                line = line.trim();

                for (int i = 0; i + ssLength <= line.length(); i++) {
                    genome = line.substring(i, i + ssLength);

                    SubSequence ss = ssMap.getOrDefault(genome, null);
                    if (ss != null) ss.addOne();
                    else ssMap.putIfAbsent(genome, new SubSequence(genome));
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return ssMap;
    }
}
