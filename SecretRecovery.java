import java.util.*;

public class SecretRecovery {

    public static List<List<int[]>> generateCombinations(List<int[]> list, int k) {
        List<List<int[]>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), list, k, 0);
        return result;
    }

    private static void backtrack(List<List<int[]>> result, List<int[]> temp, List<int[]> list, int k, int start) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < list.size(); i++) {
            temp.add(list.get(i));
            backtrack(result, temp, list, k, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}