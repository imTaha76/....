import java.util.*;

public class WaterJugDFS {
    static int capacity1 = 3, capacity2 = 5, target = 4;
    static Set<String> visited = new HashSet<>();
    static List<String> path = new ArrayList<>();

    static boolean dfs(int jug1, int jug2) {
        String state = jug1 + "," + jug2;
        if (visited.contains(state)) return false;
        visited.add(state);
        path.add("(" + jug1 + ", " + jug2 + ")");
        if (jug1 == target || jug2 == target) return true;

        if (dfs(capacity1, jug2)) return true; // Fill jug 1
        if (dfs(jug1, capacity2)) return true; // Fill jug 2
        if (dfs(0, jug2)) return true;         // Empty jug 1
        if (dfs(jug1, 0)) return true;         // Empty jug 2
        if (dfs(Math.max(0, jug1 - (capacity2 - jug2)),
                Math.min(capacity2, jug1 + jug2))) return true; // Pour 1 to 2
        if (dfs(Math.min(capacity1, jug1 + jug2),
                Math.max(0, jug2 - (capacity1 - jug1)))) return true; // Pour 2 to 1

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        if (dfs(0, 0)) {
            System.out.println("Solution steps:");
            for (String step : path) System.out.println(step);
        } else System.out.println("No solution found.");
    }
}
