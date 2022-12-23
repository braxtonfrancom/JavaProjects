public class Fib {
    public static int findFibonacciHelper(String value) {
        int num = Integer.parseInt(value);
        return num;
    }
    public static Integer findFibonacci(String value) {
        int numToUse = findFibonacciHelper(value);

        if (numToUse <= 1) {
            return numToUse;
        }
        return findFibonacci(String.valueOf(numToUse - 1)) + findFibonacci(String.valueOf(numToUse - 2));
    }
}