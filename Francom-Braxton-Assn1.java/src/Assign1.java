import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Assign1 {
    public static void main(String[] args) {

        if (args.length <= 0 || args.length % 2 != 0) { //If no or invalid number of parameters
            System.out.println("--- Assign 1 Help --- \n" +
                    "-fib [n] : Compute the Fibonacci of [n]; valid range [0, 40] \n" +
                    "-fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647] \n" +
                    "-e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]");
        }
        else {
            for(int i = 0; i < args.length; i++) {
                if (args[i].equals("-fib")) {
                    if (Integer.parseInt(args[i+1]) >= 0 && Integer.parseInt(args[i+1]) <= 40) {
                        System.out.println("Fibonacci of " + args[i+1] + " is " + findFibonacci(args[i+1]));
                    }
                    else {
                        System.out.println("Fibonacci valid range is [0, 40]");
                    }
                }
                if (args[i].equals("-fac")) {
                    System.out.println("Factorial of " + args[i+1] + " is " + findFactorial(args[i+1]));
                }

                if (args[i].equals("-e")) {
                    if ((Integer.parseInt(args[i+1]) >= 1 && (Integer.parseInt(args[i+1]) < 2147483647))) {
                        System.out.println("Value of e using " + args[i+1] + " iterations is " + findE(args[i+1])); //CRASHES IF I USE BIGGER NUMBERS THAN 1200 ex::: -e 1200
                    }
                    else {
                        System.out.println("Valid e iterations range is [1, " + Integer.MAX_VALUE + "]");
                    }
                }
                if (i % 2 == 0 && !args[i].equals("-e") && !args[i].equals("-fac") && !args[i].equals("-fib")) {
                    System.out.println("Unknown command line argument: " + args[i]);
                    System.exit(1);
                }
            }
        }
    }

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


    public static BigInteger findFactorial(String value) {

        BigInteger num = BigInteger.ONE;
        BigInteger valueToUse = BigInteger.valueOf(Long.parseLong(value));
        int intValueToUse = Integer.parseInt(value);

        if (intValueToUse == 0) {
            return num;
        }
        return valueToUse.multiply(findFactorial(String.valueOf(intValueToUse - 1)));
    }

    static double f = 1;
    public static BigDecimal findE(String value) {
        BigDecimal r;
        int intValue = Integer.parseInt(value) - 1;

        if (intValue == 0) {
            f = 1;
            return BigDecimal.ONE;
        }
        r = findE(String.valueOf(intValue));

        //Finds factorials
        f = f * intValue;

        return (r.add(BigDecimal.ONE.divide(BigDecimal.valueOf(f), 16, RoundingMode.HALF_UP)));  //Maybe re-look at the  parenthesis placement   //return (r + p / f); -->I used this before switching from returning a long to a BigDecimal
    }

}