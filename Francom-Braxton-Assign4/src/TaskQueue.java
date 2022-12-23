import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class TaskQueue {
    static ArrayList<Integer> myList = new ArrayList<>();

    public static synchronized Queue<Integer> createTaskQueue() {
        Queue<Integer> myQueue = new LinkedList<>();
        for (int i = 1; i <= 1000; i++) {
            myList.add(i);
        }
        java.util.Collections.shuffle(myList);

        for (int i = 0; i < myList.size(); i++) {
            TaskQueue.add(myQueue, myList.get(i));
        }
        return myQueue;
    }

    public static synchronized void add(Queue<Integer> myQueue, int x) {
        myQueue.add(x);
    }

    public static synchronized int remove(Queue<Integer> myQueue) {
        return myQueue.remove();
    }
}