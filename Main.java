import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int N = 5;
        int K = 3;

        // ID, expression (one has incorrect value)
        String[][] rawShares = {
            {"1", "sum(5,7)"},      // 12
            {"2", "mul(3,4)"},      // 12
            {"3", "lcm(6,8)"},      // 24
            {"4", "sum(10,2)"},     // 12 (fake one?)
            {"5", "mul(2,6)"}       // 12
        };

        List<int[]> evaluatedShares = new ArrayList<>();
        for (String[] raw : rawShares) {
            int x = Integer.parseInt(raw[0]);
            BigInteger y = NuclearCodeRecovery.evaluateExpression(raw[1]);
            evaluatedShares.add(new int[]{x, y.intValue()});
        }

        BigInteger primeMod = new BigInteger("1000000007");

        Map<BigInteger, Integer> secretFrequency = new HashMap<>();
        List<List<int[]>> combos = SecretRecovery.generateCombinations(evaluatedShares, K);

        for (List<int[]> combo : combos) {
            try {
                BigInteger secret = Lagrange.lagrangeInterpolation(combo, primeMod);
                secretFrequency.put(secret, secretFrequency.getOrDefault(secret, 0) + 1);
            } catch (Exception e) {
                // skip if mod inverse fails
            }
        }

        BigInteger trueSecret = Collections.max(secretFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println("Recovered Nuclear Code Secret: " + trueSecret);
    }
}