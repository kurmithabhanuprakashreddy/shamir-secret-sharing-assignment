import java.math.BigInteger;

public class NuclearCodeRecovery {

    public static BigInteger evaluateExpression(String expr) {
        expr = expr.replaceAll(" ", "");
        if (expr.startsWith("sum(")) {
            String[] nums = expr.substring(4, expr.length() - 1).split(",");
            return new BigInteger(nums[0]).add(new BigInteger(nums[1]));
        } else if (expr.startsWith("mul(")) {
            String[] nums = expr.substring(4, expr.length() - 1).split(",");
            return new BigInteger(nums[0]).multiply(new BigInteger(nums[1]));
        } else if (expr.startsWith("lcm(")) {
            String[] nums = expr.substring(4, expr.length() - 1).split(",");
            return lcm(new BigInteger(nums[0]), new BigInteger(nums[1]));
        } else if (expr.startsWith("gcd(")) {
            String[] nums = expr.substring(4, expr.length() - 1).split(",");
            return new BigInteger(nums[0]).gcd(new BigInteger(nums[1]));
        }
        return new BigInteger(expr);
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }
}