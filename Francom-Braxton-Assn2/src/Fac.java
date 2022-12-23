import java.math.BigInteger;
public class Fac {

    public static BigInteger findFactorial(String value) {

        BigInteger num = BigInteger.ONE;
        BigInteger valueToUse = BigInteger.valueOf(Long.parseLong(value));
        int intValueToUse = Integer.parseInt(value);

        if (intValueToUse == 0) {
            return num;
        }
        return valueToUse.multiply(findFactorial(String.valueOf(intValueToUse - 1)));
    }
}