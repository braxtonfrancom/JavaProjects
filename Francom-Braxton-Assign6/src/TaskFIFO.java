import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TaskFIFO implements Runnable {
    private int[] sequence;
    private int maxMemoryFrames;
    private int maxPageReference;
    private int[] pageFaults;

    public TaskFIFO(int[] sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
        this.sequence = sequence;
        this.maxMemoryFrames = maxMemoryFrames;
        this.maxPageReference = maxPageReference;
        this.pageFaults = pageFaults;
    }

    @Override
    public void run() {
        HashSet<Integer> set = new HashSet<>(maxMemoryFrames);
        Queue<Integer> indexes = new LinkedList<>() ;

        int page_faults = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (set.size() < maxMemoryFrames) {
                if (!set.contains(sequence[i])) {
                    set.add(sequence[i]);
                    page_faults++;
                    indexes.add(sequence[i]);
                }
            }
            else {
                if (!set.contains(sequence[i])) {
                    if (indexes.peek() != null) {
                        int val = indexes.peek();
                        indexes.poll();
                        set.remove(val);
                        set.add(sequence[i]);
                        indexes.add(sequence[i]);
                        page_faults++;
                    }
                }
            }
        }
        pageFaults[maxMemoryFrames] = page_faults;
    }
}
