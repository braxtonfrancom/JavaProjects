/**
 * Assignment 4 for CS 1410
 * This program evaluates the linear and binary searching, along
 * with comparing performance difference between the selection sort
 * and the built-in java.util.Arrays.sort.
 *
 * @author James Dean Mathias
 */
public class EvaluationDriver {
    static final int MAX_VALUE = 1_000_000;
    static final int MAX_ARRAY_SIZE = 100_000;
    static final int ARRAY_INCREMENT = 20_000;
    static final int NUMBER_SEARCHES = 50_000;

    public static void main(String[] args) {
        System.out.print("--- Linear Search Timing (unsorted) ---");
        demoLinearSearchUnsorted();

        System.out.print("\n--- Linear Search Timing (Selection Sort) ---");
        demoLinearSearchSorted();

        System.out.print("\n--- Binary Search Timing (Selection Sort) ---");
        demoBinarySearchSelectionSort();

        System.out.print("\n--- Binary Search Timing (Arrays.sort) ---");
        demoBinarySearchFastSort();
    }


    public static int[] generateNumbers(int howMany, int maxValue) {
        if (howMany > 0) {
            int[] list = new int[howMany];
            for (int i = 0; i < list.length; i++) {
                list[i] = (int) (Math.random() * maxValue);
            }
            return list;

        } else {
            return null;
        }
    }

    public static boolean linearSearch(int[] data, int search) {
        boolean found = false;

        for (int i = 0; i < data.length; i++) {
            if (search == data[i]) {
                found = true;
            }
        }
        return found;
    }

    public static boolean binarySearch(int[] data, int search) {
        boolean found = false;
        int low = 0;
        int high = data.length - 1;

        while (!found && high >= low) {
            int mid = (low + high) / 2;
            if (search < data[mid])
                high = mid - 1;
            else if (search == data[mid])
                found = true;
            else
                low = mid + 1;
        }
        return found;
    }

    public static void selectionSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            //Find the minimum in data[i..data.length -1]
            int currentMin = data[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < data.length; j++) {
                if (currentMin > data[j]) {
                    currentMin = data[j];
                    currentMinIndex = j;
                }
            }
            //Now swap data[i] with data[currentMinIndex] if necessary
            if (currentMinIndex != i) {
                data[currentMinIndex] = data[i];
                data[i] = currentMin;
            }
        }
    }


    public static void demoLinearSearchUnsorted() {
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            System.out.printf("\nNumber of items       : %-6d", i);
            int[] myList = generateNumbers(i, MAX_VALUE);

            long startTime = System.currentTimeMillis();
            int count = 0;
            for (int j = 0; j <= NUMBER_SEARCHES; j++) {

                int searchNum = (int) (Math.random() * MAX_VALUE);
                linearSearch(myList, searchNum);

                if (linearSearch(myList, searchNum)) {
                    count += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime);
            System.out.printf("\nTimes value was found : %-4d", count);
            System.out.printf("\nTotal search time     : %-2d ms\n", totalTime);
        }
    }

    public static void demoLinearSearchSorted() {
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            System.out.printf("\nNumber of items       : %-6d", i);
            int[] myList = generateNumbers(i, MAX_VALUE);
            long startTime = System.currentTimeMillis();
            int count = 0;
            selectionSort(myList);
            for (int j = 0; j <= NUMBER_SEARCHES; j++) {

                int searchNum = (int) (Math.random() * MAX_VALUE);
                linearSearch(myList, searchNum);

                if (linearSearch(myList, searchNum)) {
                    count += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime);
            System.out.printf("\nTimes value was found : %-4d", count);
            System.out.printf("\nTotal search time     : %-2d ms\n", totalTime);
        }
    }

    public static void demoBinarySearchSelectionSort() {
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            System.out.printf("\nNumber of items       : %-6d", i);
            int[] myList = generateNumbers(i, MAX_VALUE);
            long startTime = System.currentTimeMillis();
            int count = 0;
            selectionSort(myList);

            for (int j = 0; j <= NUMBER_SEARCHES; j++) {

                int searchNum = (int) (Math.random() * MAX_VALUE);
                binarySearch(myList, searchNum);

                if (binarySearch(myList, searchNum)) {
                    count += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime);
            System.out.printf("\nTimes value was found : %-4d", count);
            System.out.printf("\nTotal search time     : %-2d ms\n", totalTime);
        }
    }

    public static void demoBinarySearchFastSort() {
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            System.out.printf("\nNumber of items       : %-6d", i);
            int[] myList = generateNumbers(i, MAX_VALUE);
            int count = 0;
            long startTime = System.currentTimeMillis();
            java.util.Arrays.sort(myList);
            for (int j = 0; j <= NUMBER_SEARCHES; j++) {

                int searchNum = (int) (Math.random() * MAX_VALUE);
                binarySearch(myList, searchNum);

                if (binarySearch(myList, searchNum)) {
                    count += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime);
            System.out.printf("\nTimes value was found : %-4d", count);
            System.out.printf("\nTotal search time     : %-2d ms\n", totalTime);
        }
    }
}

