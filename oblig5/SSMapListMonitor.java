import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class SSMapListMonitor {
    private SSMapList ssMapList;
    private Lock lock;
    private CountDownLatch initSize;
    private CountDownLatch mergerBarrier;
    private Condition removeReady;
    private int  newAdditions;
    private int allAdditions;

    // Useful for debugging
    private boolean infected;



    public SSMapListMonitor(int adders, int mergers, boolean infected) {
        this.ssMapList = new SSMapList();
        this.lock = new ReentrantLock();
        this.initSize = new CountDownLatch(adders);
        this.mergerBarrier = new CountDownLatch(mergers);
        this.removeReady = lock.newCondition();
        this.newAdditions = 0;
        this.infected = infected;
    }

    public void awaitSizeInitialization() {
        try {
            initSize.await();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void signalSize(int count) {
        this.newAdditions += count;
        initSize.countDown();

        if (this.initSize.getCount() == 0) {
            this.allAdditions = (this.newAdditions * 2) - 1;
        }
    }

    public void mergersAwait() {
        try {
            mergerBarrier.await();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void signalMergerDone() {
        mergerBarrier.countDown();
    }


    public void  add(HashMap<String, SubSequence> map) {
        lock.lock();
        try {
            this.ssMapList.add(map);
            --this.allAdditions;
            removeReady.signal();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
          lock.unlock();
        }
    }

    public ArrayList<HashMap<String, SubSequence>> removeTwo() {
        lock.lock();

        try {
            while (size() < 2) {
                if (this.allAdditions == 0) {
                    removeReady.signal();
                    return null;
                }
                removeReady.await();
            }

            return new ArrayList<>(Arrays.asList(
                        this.ssMapList.remove(), this.ssMapList.remove()));

        } catch(InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }

    private int size() {
        return this.ssMapList.size();
    }

    public void printMap() {
        this.ssMapList.printSubSequences();
    }

    public HashMap<String, SubSequence> getMergedHashMap() {
        return this.ssMapList.get(0);
    }

    public boolean allMapsMerged() {
        return (this.allAdditions == 0 && size() == 1);
    }

}
