import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class ShamirSecretSharing {
    
    public static void main(String[] args) {
        try {
            // Read and process both test cases
            System.out.println("🔍 Processing Shamir's Secret Sharing Assignment");
            System.out.println("===============================================");
            
            // Test Case 1
            System.out.println("\n📋 Test Case 1:");
            processTestCase("testcase1.json");
            
            // Test Case 2  
            System.out.println("\n📋 Test Case 2:");
            processTestCase("testcase2.json");
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void processTestCase(String filename) throws Exception {
        // Read JSON file
        String jsonContent = readFile(filename);
        
        // Parse JSON manually
        Map<String, Object> json = parseJSON(jsonContent);
        
        // Extract keys
        Map<String, Object> keys = (Map<String, Object>) json.get("keys");
        int n = Integer.parseInt(keys.get("n").toString());
        int k = Integer.parseInt(keys.get("k").toString());
        int m = k - 1; // degree of polynomial
        
        System.out.println("n = " + n + ", k = " + k + ", degree = " + m);
        
        // Parse roots and decode Y values
        List<Point> points = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String key = String.valueOf(i);
            if (json.containsKey(key)) {
                Map<String, Object> root = (Map<String, Object>) json.get(key);
                int x = i;
                int base = Integer.parseInt(root.get("base").toString());
                String value = root.get("value").toString();
                
                // Decode Y value from given base
                BigInteger y = decodeFromBase(value, base);
                
                points.add(new Point(x, y));
                System.out.println("Root " + i + ": x=" + x + ", y=" + y + " (decoded from base " + base + ": " + value + ")");
            }
        }
        
        // Find the constant term using Lagrange interpolation
        BigInteger constantTerm = findConstantTerm(points, k);
        System.out.println("🎯 Secret (Constant Term): " + constantTerm);
    }
    
    public static BigInteger decodeFromBase(String value, int base) {
        return new BigInteger(value, base);
    }
    
    public static BigInteger findConstantTerm(List<Point> points, int k) {
        // Generate all combinations of k points
        List<List<Point>> combinations = generateCombinations(points, k);
        
        Map<BigInteger, Integer> secretFrequency = new HashMap<>();
        
        for (List<Point> combo : combinations) {
            try {
                // Use Lagrange interpolation to find the constant term (f(0))
                BigInteger secret = lagrangeInterpolation(combo, 0);
                secretFrequency.put(secret, secretFrequency.getOrDefault(secret, 0) + 1);
            } catch (Exception e) {
                // Skip if interpolation fails
            }
        }
        
        if (secretFrequency.isEmpty()) {
            throw new RuntimeException("No valid secrets found!");
        }
        
        // Return the most frequent secret
        return Collections.max(secretFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    public static BigInteger lagrangeInterpolation(List<Point> points, int x) {
        BigInteger result = BigInteger.ZERO;
        BigInteger primeMod = new BigInteger("1000000007"); // Large prime for modular arithmetic
        
        for (int i = 0; i < points.size(); i++) {
            BigInteger xi = BigInteger.valueOf(points.get(i).x);
            BigInteger yi = points.get(i).y;
            
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            
            for (int j = 0; j < points.size(); j++) {
                if (i == j) continue;
                
                BigInteger xj = BigInteger.valueOf(points.get(j).x);
                BigInteger xTarget = BigInteger.valueOf(x);
                
                // numerator *= (x - xj)
                numerator = numerator.multiply(xTarget.subtract(xj)).mod(primeMod);
                
                // denominator *= (xi - xj)  
                denominator = denominator.multiply(xi.subtract(xj)).mod(primeMod);
            }
            
            // Calculate modular inverse of denominator
            BigInteger invDenominator = denominator.modInverse(primeMod);
            
            // Calculate term: yi * numerator * invDenominator
            BigInteger term = yi.multiply(numerator).mod(primeMod);
            term = term.multiply(invDenominator).mod(primeMod);
            
            result = result.add(term).mod(primeMod);
        }
        
        return result;
    }
    
    public static List<List<Point>> generateCombinations(List<Point> points, int k) {
        List<List<Point>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), points, k, 0);
        return result;
    }
    
    private static void backtrack(List<List<Point>> result, List<Point> temp, List<Point> points, int k, int start) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < points.size(); i++) {
            temp.add(points.get(i));
            backtrack(result, temp, points, k, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
    
    public static String readFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    // Simple JSON parser for this specific format
    public static Map<String, Object> parseJSON(String json) {
        Map<String, Object> result = new HashMap<>();
        json = json.trim();
        
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
            String[] pairs = splitJSONPairs(json);
            
            for (String pair : pairs) {
                String[] keyValue = pair.split(":", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().replaceAll("\"", "");
                    String value = keyValue[1].trim();
                    
                    if (value.startsWith("{") && value.endsWith("}")) {
                        // Nested object
                        result.put(key, parseJSON(value));
                    } else if (value.startsWith("\"") && value.endsWith("\"")) {
                        // String value
                        result.put(key, value.substring(1, value.length() - 1));
                    } else {
                        // Number value
                        result.put(key, value);
                    }
                }
            }
        }
        
        return result;
    }
    
    private static String[] splitJSONPairs(String json) {
        List<String> pairs = new ArrayList<>();
        int braceCount = 0;
        int start = 0;
        
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') braceCount++;
            else if (c == '}') braceCount--;
            else if (c == ',' && braceCount == 0) {
                pairs.add(json.substring(start, i));
                start = i + 1;
            }
        }
        
        if (start < json.length()) {
            pairs.add(json.substring(start));
        }
        
        return pairs.toArray(new String[0]);
    }
    
    static class Point {
        int x;
        BigInteger y;
        
        Point(int x, BigInteger y) {
            this.x = x;
            this.y = y;
        }
    }
} 