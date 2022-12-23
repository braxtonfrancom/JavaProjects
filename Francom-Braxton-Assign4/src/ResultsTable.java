import java.util.HashMap;

public class ResultsTable {
    static HashMap<Integer, Integer> resultsHashMap = new HashMap<>();

    public static synchronized void put(int element, int firstDigit) {
        resultsHashMap.put(element, firstDigit);
    }
    public static synchronized int get(int element) {
        return resultsHashMap.get(element);
    }
    public static synchronized void print() {
        if (resultsHashMap.size() % 10 == 0) {
            System.out.print(".");
            System.out.flush();
        }
    }
}
