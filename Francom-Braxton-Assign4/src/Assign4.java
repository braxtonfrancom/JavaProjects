import java.util.Queue;

public class Assign4 {
    public static void main(String[] args) {
        Queue<Integer> tasksToDo = TaskQueue.createTaskQueue();

        //System.out.println(Runtime.getRuntime().availableProcessors());  //Tells me I have 8 available processors
        try {
            Thread[] threads = new Thread[Runtime.getRuntime().availableProcessors()];

            long timeStart = System.currentTimeMillis();
            for (int thread = 0; thread < Runtime.getRuntime().availableProcessors(); thread++){
                threads[thread] = new Thread(new RunnableWorkerThread(String.format("Thread %d", thread + 1), tasksToDo, ResultsTable.resultsHashMap));
                threads[thread].start();
            }
            for (Thread t : threads) {
                t.join();
            }
            long timeEnd = System.currentTimeMillis();
            System.out.print("\n\n3.");
            for (int k = 1; k <= ResultsTable.resultsHashMap.size(); k++) {
                System.out.print(ResultsTable.get(k));
            }
            System.out.printf("\n\nPi computation took %d ms\n", (timeEnd - timeStart));
        }
        catch (Exception ex) {
            System.out.println("Something bad happened");
        }
    }
}
