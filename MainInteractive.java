import java.math.BigInteger;
import java.util.*;
import java.util.Scanner;

public class MainInteractive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("🚀 Nuclear Code Recovery System - Interactive Mode");
        System.out.println("==================================================");
        
        // Get N and K from user
        System.out.print("Enter total number of shares (N): ");
        int N = scanner.nextInt();
        
        System.out.print("Enter minimum shares needed (K): ");
        int K = scanner.nextInt();
        
        scanner.nextLine(); // Consume newline
        
        System.out.println("\n📝 Enter " + N + " shares (ID and expression):");
        System.out.println("Format: ID expression (e.g., 1 sum(5,7))");
        System.out.println("Supported operations: sum(a,b), mul(a,b), lcm(a,b), gcd(a,b)");
        System.out.println();
        
        String[][] rawShares = new String[N][2];
        
        for (int i = 0; i < N; i++) {
            System.out.print("Share " + (i+1) + " (ID expression): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            
            if (parts.length != 2) {
                System.out.println("❌ Invalid format! Please use: ID expression");
                i--; // Retry this share
                continue;
            }
            
            rawShares[i][0] = parts[0];
            rawShares[i][1] = parts[1];
        }
        
        System.out.println("\n🔍 Processing shares...");
        
        // Evaluate expressions
        List<int[]> evaluatedShares = new ArrayList<>();
        for (String[] raw : rawShares) {
            try {
                int x = Integer.parseInt(raw[0]);
                BigInteger y = NuclearCodeRecovery.evaluateExpression(raw[1]);
                evaluatedShares.add(new int[]{x, y.intValue()});
                System.out.println("Share " + raw[0] + ": " + raw[1] + " = " + y);
            } catch (Exception e) {
                System.out.println("❌ Error evaluating share " + raw[0] + ": " + e.getMessage());
            }
        }
        
        if (evaluatedShares.size() < K) {
            System.out.println("❌ Not enough valid shares to recover secret!");
            return;
        }
        
        BigInteger primeMod = new BigInteger("1000000007");
        
        System.out.println("\n🔬 Testing all combinations of " + K + " shares...");
        
        Map<BigInteger, Integer> secretFrequency = new HashMap<>();
        List<List<int[]>> combos = SecretRecovery.generateCombinations(evaluatedShares, K);
        
        System.out.println("Total combinations to test: " + combos.size());
        
        for (int i = 0; i < combos.size(); i++) {
            List<int[]> combo = combos.get(i);
            try {
                BigInteger secret = Lagrange.lagrangeInterpolation(combo, primeMod);
                secretFrequency.put(secret, secretFrequency.getOrDefault(secret, 0) + 1);
                
                // Show combination details
                System.out.print("Combination " + (i+1) + " [");
                for (int j = 0; j < combo.size(); j++) {
                    System.out.print("(" + combo.get(j)[0] + "," + combo.get(j)[1] + ")");
                    if (j < combo.size() - 1) System.out.print(" ");
                }
                System.out.println("] → Secret: " + secret);
                
            } catch (Exception e) {
                System.out.println("Combination " + (i+1) + " failed: " + e.getMessage());
            }
        }
        
        if (secretFrequency.isEmpty()) {
            System.out.println("❌ No valid secrets found!");
            return;
        }
        
        // Find the most frequent secret
        BigInteger trueSecret = Collections.max(secretFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
        int frequency = secretFrequency.get(trueSecret);
        
        System.out.println("\n📊 Secret Frequency Analysis:");
        for (Map.Entry<BigInteger, Integer> entry : secretFrequency.entrySet()) {
            System.out.println("Secret " + entry.getKey() + " appears " + entry.getValue() + " times");
        }
        
        System.out.println("\n🎯 RESULT:");
        System.out.println("Recovered Nuclear Code Secret: " + trueSecret);
        System.out.println("Confidence: " + frequency + "/" + combos.size() + " combinations (" + 
                          String.format("%.1f", (double)frequency/combos.size()*100) + "%)");
        
        scanner.close();
    }
} 