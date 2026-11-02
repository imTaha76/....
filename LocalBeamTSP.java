import java.util.*;

public class LocalBeamTSP {
    static int[][] d = {{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}};

    static int cost(List<Integer> p) {
        int c = 0;
        for (int i = 0; i < p.size() - 1; i++) c += d[p.get(i)][p.get(i + 1)];
        return c + d[p.get(3)][p.get(0)];
    }

    static List<List<Integer>> next(List<Integer> p) {
        List<List<Integer>> a = new ArrayList<>();
        for (int i = 1; i < 4; i++) for (int j = i + 1; j < 4; j++) {
            List<Integer> n = new ArrayList<>(p);
            Collections.swap(n, i, j); a.add(n);
        }
        return a;
    }

    public static void main(String[] args) {
        int k = 2; Random r = new Random();
        List<List<Integer>> beam = new ArrayList<>();
        for (int x = 0; x < k; x++) {
            List<Integer> p = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
            Collections.shuffle(p.subList(1, 4), r); beam.add(p);
        }
        List<Integer> best = null;
        for (int step = 0; step < 50; step++) {
            List<List<Integer>> all = new ArrayList<>(beam);
            for (List<Integer> p : beam) all.addAll(next(p));
            all.sort(Comparator.comparingInt(LocalBeamTSP::cost));
            beam = new ArrayList<>();
            for (List<Integer> p : all) if (!beam.contains(p) && beam.size() < k) beam.add(p);
            if (best == null || cost(beam.get(0)) < cost(best)) best = new ArrayList<>(beam.get(0));
        }
        System.out.println("Tour: " + best + " -> " + best.get(0));
        System.out.println("Cost: " + cost(best));
    }
}
