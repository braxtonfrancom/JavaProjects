public class Assn2 {
    public static void main(String[] args) throws Exception {

        if (!Help.isValidInput(args.length)) {}

        else {
            for (int i = 0; i < args.length; i += 2) {

                try {
                    long value = Long.parseLong(args[i+1]);

                    if (args[i].equals("-fib")) {
                        if (value >= 0 && value <= 40) {
                            System.out.println("Fibonacci of " + value + " is " + Fib.findFibonacci(Long.toString(value)));
                        } else {
                            Help.fibToString();
                        }
                    }
                    if (args[i].equals("-fac")) {
                        if (value >= 0 && value < 2147483647) {
                            System.out.println("Factorial of " + value + " is " + Fac.findFactorial(Long.toString(value)));
                        } else {
                            Help.facToString();
                        }
                    }
                    if (args[i].equals("-e")) {
                        if (value >= 1 && value < 2147483647) {
                            System.out.println("Value of e using " + value + " iterations is " + E.findE((int)value - 1));
                        } else {
                            Help.eToString();
                        }
                    }
                    if (!args[i].equals("-e") && !args[i].equals("-fac") && !args[i].equals("-fib")) {
                        Help.isUnknownInput(args[i]);
                    }

                }
                catch (NumberFormatException ex) {
                    Help.notAnIntegerErrorMessage();
                }
            }
        }
    }
}