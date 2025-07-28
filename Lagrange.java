import java.math.BigInteger;
import java.util.List;

public class Lagrange {

    public static BigInteger lagrangeInterpolation(List<int[]> shares, BigInteger primeMod) {
        BigInteger secret = BigInteger.ZERO;

        for (int i = 0; i < shares.size(); i++) {
            BigInteger xi = BigInteger.valueOf(shares.get(i)[0]);
            BigInteger yi = new BigInteger(String.valueOf(shares.get(i)[1]));

            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int j = 0; j < shares.size(); j++) {
                if (i == j) continue;
                BigInteger xj = BigInteger.valueOf(shares.get(j)[0]);
                numerator = numerator.multiply(xj.negate()).mod(primeMod);
                denominator = denominator.multiply(xi.subtract(xj)).mod(primeMod);
            }

            BigInteger invDenominator = denominator.modInverse(primeMod);
            BigInteger term = yi.multiply(numerator).mod(primeMod);
            term = term.multiply(invDenominator).mod(primeMod);

            secret = secret.add(term).mod(primeMod);
        }

        return secret;
    }
}