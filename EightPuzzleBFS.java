import java.util.*;

public class EightPuzzleBFS {
    static int[] target = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    static String key(int[] state) {
        return Arrays.toString(state);
    }

    static List<int[]> bfs(int[] startState) {
        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> visited = new HashMap<>();
        queue.add(startState);
        visited.put(key(startState), null);

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            if (Arrays.equals(state, target)) {
                List<int[]> path = new ArrayList<>();
                while (state != null) {
                    path.add(state);
                    state = visited.get(key(state));
                }
                Collections.reverse(path);
                return path;
            }

            int zero = -1;
            for (int i = 0; i < 9; i++)
                if (state[i] == 0) { zero = i; break; }
            int row = zero / 3, col = zero % 3;

            for (int move : new int[]{-3, 3, -1, 1}) {
                int newPos = zero + move;
                if (newPos < 0 || newPos >= 9) continue;
                int newRow = newPos / 3, newCol = newPos % 3;
                if (Math.abs(row - newRow) + Math.abs(col - newCol) == 1) {
                    int[] neighbor = state.clone();
                    neighbor[zero] = neighbor[newPos];
                    neighbor[newPos] = 0;
                    if (!visited.containsKey(key(neighbor))) {
                        visited.put(key(neighbor), state);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    static void printSolution(List<int[]> path) {
        for (int[] state : path) {
            for (int i = 0; i < 9; i++) {
                System.out.print(state[i] + " ");
                if (i % 3 == 2) System.out.println();
            }
            System.out.println("-----");
        }
    }

    public static void main(String[] args) {
        int[] startState = {1, 3, 0, 6, 8, 4, 7, 5, 2};
        List<int[]> solution = bfs(startState);
        if (solution != null) {
            printSolution(solution);
            System.out.println("Solved in " + (solution.size() - 1) + " moves.");
        } else System.out.println("No solution found.");
    }
}
