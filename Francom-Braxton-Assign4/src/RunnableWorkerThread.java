import java.util.HashMap;
import java.util.Queue;

public class RunnableWorkerThread implements Runnable {

    private final HashMap<Integer, Integer> myHashTable;
    private final Queue<Integer> myQueue;
    private String name;

    public RunnableWorkerThread(String name, Queue<Integer> myQueue, HashMap<Integer, Integer> myHashTable) {
        this.name = name;
        this.myQueue = myQueue;
        this.myHashTable = myHashTable;
    }

    @Override
    public void run() {
        try {
            //System.out.printf("Thread %s running on pool thread %s is starting to run...\n", this.name, Thread.currentThread().getName());
            while (!this.myQueue.isEmpty()) {
                Bpp digitsToFind = new Bpp();
                int element = TaskQueue.remove(this.myQueue);
                String result = String.valueOf(digitsToFind.getDecimal(element));
                if (result.length() <= 8) {
                    int firstDigit = 0;
                    ResultsTable.put(element, firstDigit);
                }
                else {
                    int firstDigit = Integer.parseInt(result.substring(0, 1)); //Takes only the first number
                    ResultsTable.put(element, firstDigit);
                }
                //System.out.printf("%s put an element in the hash table\n", this.name);
                ResultsTable.print();
            }
            //System.out.printf("%s is done processing\n", this.name);
        }
        catch (Exception ex) {
        }
    }
}
