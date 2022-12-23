import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TaskLRU implements Runnable {
    private int[] sequence;
    private int maxMemoryFrames;
    private int maxPageReference;
    private int[] pageFaults;

    public TaskLRU(int[] sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
        this.sequence = sequence;
        this.maxMemoryFrames = maxMemoryFrames;
        this.maxPageReference = maxPageReference;
        this.pageFaults = pageFaults;
    }


    @Override
    public void run() {
        HashSet<Integer> set = new HashSet<>(maxMemoryFrames);
        HashMap<Integer, Integer> indexes = new HashMap<>();

        int page_faults = 0;
        for (int i = 0; i < sequence.length; i ++) {
            if (set.size() < maxMemoryFrames) {
                if (!set.contains(sequence[i])) {
                    set.add(sequence[i]);
                    page_faults++;
                }
                indexes.put(sequence[i], i);
            }
            else {
                if (!set.contains(sequence[i])) {
                    int lru = Integer.MAX_VALUE, val = Integer.MIN_VALUE;
                    Iterator<Integer> iterator = set.iterator();

                    while (iterator.hasNext()) {
                        int tempVal = iterator.next();
                        if (indexes.get(tempVal) < lru) {
                            lru = indexes.get(tempVal);
                            val = tempVal;
                        }
                    }
                    set.remove(val);
                    indexes.remove(val);
                    set.add(sequence[i]);
                    page_faults++;
                }
                indexes.put(sequence[i], i);
            }
        }
        pageFaults[maxMemoryFrames] = page_faults;
    }
}
