public class Help {

    public static boolean isValidInput(int argsLength) {
        if (argsLength <= 0 || argsLength % 2 != 0) { //If no or invalid number of parameters
            System.out.println("--- Assign 1 Help --- \n" +
                    "-fib [n] : Compute the Fibonacci of [n]; valid range [0, 40] \n" +
                    "-fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647] \n" +
                    "-e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]");
            return false;
        }
        return true;
    }

    public static void fibToString() {
        System.out.println("Fibonacci valid range is [0, 40]");
    }

    public static void facToString() { System.out.println("Factorial valid range is [0, " + Integer.MAX_VALUE + "]"); }

    public static void eToString() {
        System.out.println("Valid e iterations range is [1, " + Integer.MAX_VALUE + "]");
    }

    public static void isUnknownInput(String arg) {
        System.out.println("Unknown command line argument: " + arg);
    }

    public static void notAnIntegerErrorMessage() { System.out.println("Please do not enter two commands in a row"); }

}