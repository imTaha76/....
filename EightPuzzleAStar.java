import java.util.*;

public class EightPuzzleAStar {
    static final String GOAL = "123456780";

    static class Node {
        String s; int g; Node parent;
        Node(String s, int g, Node p) { this.s = s; this.g = g; parent = p; }
    }

    static int h(String s) {
        int d = 0;
        for (int i = 0; i < 9; i++) if (s.charAt(i) != '0') {
            int v = s.charAt(i) - '1';
            d += Math.abs(i / 3 - v / 3) + Math.abs(i % 3 - v % 3);
        }
        return d;
    }

    static void solve(String start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.g + h(n.s)));
        Map<String, Integer> best = new HashMap<>();
        pq.add(new Node(start, 0, null)); best.put(start, 0);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.g != best.get(cur.s)) continue;
            if (cur.s.equals(GOAL)) {
                List<String> path = new ArrayList<>();
                for (Node n = cur; n != null; n = n.parent) path.add(n.s);
                Collections.reverse(path);
                for (String s : path) System.out.println(s);
                System.out.println("Moves: " + (path.size() - 1));
                return;
            }

            int z = cur.s.indexOf('0');
            for (int m : new int[]{-3, 3, -1, 1}) {
                int n = z + m;
                if (n < 0 || n > 8 || Math.abs(z / 3 - n / 3) + Math.abs(z % 3 - n % 3) != 1) continue;
                char[] a = cur.s.toCharArray(); a[z] = a[n]; a[n] = '0';
                String t = new String(a); int ng = cur.g + 1;
                if (!best.containsKey(t) || ng < best.get(t)) {
                    best.put(t, ng); pq.add(new Node(t, ng, cur));
                }
            }
        }
        System.out.println("No solution");
    }

    public static void main(String[] args) { solve("123406758"); }
}
