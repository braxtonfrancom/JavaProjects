import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Assign6 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[][][] pageFaults = new int[1000][3][100];
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int simulation = 0; simulation < 1000; simulation++) { //1000 simulations
            int[] p = new int[1000]; //Randomized page reference sequence for each simulation
            for (int j = 0; j < p.length; j++) {
                Random rand = new Random();
                int random_int = rand.nextInt(250 - 1) + 1;
                p[j] = random_int;
            }
            for (int frame = 0; frame < pageFaults[simulation][0].length; frame++) { //Should this be modified??
                Runnable fifo = new TaskFIFO(p, frame, 250, pageFaults[simulation][0]);
                threadPool.execute(fifo);
                Runnable lru = new TaskLRU(p, frame, 250, pageFaults[simulation][1]);
                threadPool.execute(lru);
                Runnable mru = new TaskMRU(p, frame, 250, pageFaults[simulation][2]);
                threadPool.execute(mru);
            }
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        }
        catch (Exception ex) {
            System.out.println("Error in waiting for shutdown");
        }

        int bestFifo = 0, bestLru = 0, bestMru = 0;
        for (int sim = 0; sim <= pageFaults.length - 1; sim++) {
            for (int pf = 0; pf <= pageFaults[sim][0].length - 1; pf++) {
                int fifoNum = pageFaults[sim][0][pf];
                int lruNum = pageFaults[sim][1][pf];
                int mruNum = pageFaults[sim][2][pf];

                if (fifoNum > lruNum && fifoNum > mruNum) { // FIFO beats them all
                    bestFifo++;
                }
                else if (lruNum > fifoNum && lruNum > mruNum) { // LRU beats them all
                    bestLru++;
                }
                else if (mruNum > fifoNum && mruNum > lruNum) { // MRU beats them all
                    bestMru++;
                }
                else {
                    if (fifoNum == lruNum && fifoNum == mruNum) { // 3-way tie
                        bestFifo++;
                        bestLru++;
                        bestMru++;
                    }
                    if (fifoNum == lruNum && fifoNum != mruNum) { // 2-way tie between fifo and lru
                        bestFifo++;
                        bestLru++;
                    }
                    if (fifoNum == mruNum && fifoNum != lruNum) { // 2-way tie between fifo and mru
                        bestFifo++;
                        bestMru++;
                    }
                    if (lruNum == mruNum && lruNum != fifoNum) { // 2-way tie between lru and mru
                        bestLru++;
                        bestMru++;
                    }
                }
            }
        }

        long end = System.currentTimeMillis();
        long totalTime = (end - start);
        System.out.println("Simulation took " + totalTime + " ms\n");
        System.out.println("FIFO min PF: " + bestFifo);
        System.out.println("LRU min PF : " + bestLru);
        System.out.println("MRU min PF : " + bestMru + "\n");

        System.out.println("Belady's Anomaly Report for FIFO");
        int[] fifoAnomAndDelta = findAnoms(pageFaults, 0);
        System.out.println("  Anomaly detected " + fifoAnomAndDelta[0] + " times in 1000 simulations with a max delta of " + fifoAnomAndDelta[1] + "\n");

        System.out.println("Belady's Anomaly Report for LRU");
        int[] lruAnomAndDelta = findAnoms(pageFaults, 1);
        System.out.println("  Anomaly detected " + lruAnomAndDelta[0] + " times in 1000 simulations with a max delta of " + lruAnomAndDelta[1] + "\n");

        System.out.println("Belady's Anomaly Report for MRU");
        int[] mruAnomAndDelta = findAnoms(pageFaults, 2);
        System.out.println("  Anomaly detected " + mruAnomAndDelta[0] + " times in 1000 simulations with a max delta of " + mruAnomAndDelta[1] + "\n");

        //testLRU();
        //testMRU();
    }


    public static int[] findAnoms(int[][][] pageFaults, int typeOfSim) {
        int[] anomAndDelta = new int[2];
        int anomCount = 0;
        int maxDelta = 0;

        for (int simNum = 0; simNum <= pageFaults.length - 1; simNum++) {
            for (int simType = 0; simType <= pageFaults[simNum].length - 1; simType++) {
                for (int index = 0; index <= pageFaults[simNum][simType].length - 1; index++) {

                    if (simType == typeOfSim) {
                        if (index > 1) {
                            if (pageFaults[simNum][simType][index] > pageFaults[simNum][simType][index - 1]) {
                                System.out.printf("        Anomaly detected in simulation #" + "%03d" + " - " + pageFaults[simNum][simType][index - 1] + " PF's @ " + (index - 1) + " frames vs. " + pageFaults[simNum][simType][index] + " PF's @  " + index + " frames (Δ" + ((pageFaults[simNum][simType][index]) - (pageFaults[simNum][simType][index - 1])) + ")\n", (simNum + 1));
                                anomCount++;
                                if (((pageFaults[simNum][simType][index]) - (pageFaults[simNum][simType][index - 1])) > maxDelta) {
                                    maxDelta = ((pageFaults[simNum][simType][index]) - (pageFaults[simNum][simType][index - 1]));
                                }
                            }
                        }
                    }
                }
            }
        }
        anomAndDelta[0] = anomCount;
        anomAndDelta[1] = maxDelta;
        return anomAndDelta;
    }





    public static void testLRU() {
        int[] sequence1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] sequence2 = {1, 2, 1, 3, 2, 1, 2, 3, 4};
        int[] pageFaults = new int[4];  // 4 because maxMemoryFrames is 3

        // Replacement should be: 1, 2, 3, 4, 5, 6, 7, 8
        // Page Faults should be 9
        (new TaskLRU(sequence1, 1, 250, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[1]);

        // Replacement should be: 2, 1, 3, 1, 2
        // Page Faults should be 7
        (new TaskLRU(sequence2, 2, 250, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[2]);

        // Replacement should be: 1
        // Page Faults should be 4
        (new TaskLRU(sequence2, 3, 250, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[3]);
    }




    public static void testMRU() {
        int[] sequence1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] sequence2 = {1, 2, 1, 3, 2, 1, 2, 3, 4};
        int[] sequence3 = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};


        int[] pageFaults = new int[4];  // 4 because maxMemoryFrames is 3

        // Replacement should be: 1, 2, 3, 4, 5, 6, 7, 8
        // Page Faults should be 9
        (new TaskMRU(sequence1, 1, 250, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[1]);

        // Replacement should be: 1, 2, 1, 3
        // Page Faults should be 6
        (new TaskMRU(sequence2, 2, 250, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[2]);

        // Replacement should be: 3
        // Page Faults should be 4
        (new TaskMRU(sequence2, 3, 250, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[3]);
    }

}
