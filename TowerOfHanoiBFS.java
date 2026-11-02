import java.util.*;

public class TowerOfHanoiBFS {
    static class Node {
        List<Integer>[] t; Node p; String move;
        Node(List<Integer>[] t, Node p, String m) { this.t = t; this.p = p; move = m; }
        String key() { return t[0] + "|" + t[1] + "|" + t[2]; }
    }

    static List<Integer>[] copy(List<Integer>[] t) {
        List<Integer>[] a = new List[3];
        for (int i = 0; i < 3; i++) a[i] = new ArrayList<>(t[i]);
        return a;
    }

    static void solve() {
        List<Integer>[] t = new List[]{new ArrayList<>(Arrays.asList(3, 2, 1)),
                new ArrayList<>(), new ArrayList<>()};
        Queue<Node> q = new LinkedList<>(); Set<String> seen = new HashSet<>();
        Node start = new Node(t, null, null); q.add(start); seen.add(start.key());

        while (!q.isEmpty()) {
            Node c = q.poll();
            if (c.t[2].size() == 3) {
                List<String> path = new ArrayList<>();
                for (Node n = c; n.p != null; n = n.p) path.add(n.move);
                Collections.reverse(path);
                for (String s : path) System.out.println(s);
                System.out.println("Total moves: " + path.size());
                return;
            }
            for (int i = 0; i < 3; i++) if (!c.t[i].isEmpty())
                for (int j = 0; j < 3; j++) if (i != j) {
                    int d = c.t[i].get(c.t[i].size() - 1);
                    if (!c.t[j].isEmpty() && c.t[j].get(c.t[j].size() - 1) < d) continue;
                    List<Integer>[] a = copy(c.t);
                    a[i].remove(a[i].size() - 1); a[j].add(d);
                    Node next = new Node(a, c, "Move disk " + d + " from " + (i + 1) + " to " + (j + 1));
                    if (seen.add(next.key())) q.add(next);
                }
        }
    }

    public static void main(String[] args) { solve(); }
}
