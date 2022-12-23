import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class E {

    static BigInteger f = BigInteger.ZERO;
    public static BigDecimal findE(int value) {
        BigDecimal r;

        if (value == 0) {
            f = BigInteger.ONE;
            return BigDecimal.ONE;
        }
        r = findE(value - 1);

        //Finds factorials
        f = f.multiply(BigInteger.valueOf(value));

        BigDecimal modF = new BigDecimal(f);

        return (r.add(BigDecimal.ONE.divide(modF, 16, RoundingMode.HALF_UP)));
    }
}