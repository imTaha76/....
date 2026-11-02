public class EightQueens {
    static final int N = 8;

    static boolean isSafe(int[][] mat, int row, int col) {
        for (int i = 0; i < row; i++)
            if (mat[i][col] == 1) return false;

        int i = row - 1, j = col - 1;
        while (i >= 0 && j >= 0) {
            if (mat[i][j] == 1) return false;
            i--; j--;
        }

        i = row - 1; j = col + 1;
        while (i >= 0 && j < N) {
            if (mat[i][j] == 1) return false;
            i--; j++;
        }
        return true;
    }

    static boolean placeQueens(int row, int[][] mat) {
        if (row == N) return true;
        for (int col = 0; col < N; col++) {
            if (isSafe(mat, row, col)) {
                mat[row][col] = 1;
                if (placeQueens(row + 1, mat)) return true;
                mat[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = new int[N][N];
        if (placeQueens(0, mat)) {
            for (int[] row : mat) {
                for (int value : row) System.out.print(value + " ");
                System.out.println();
            }
        } else System.out.println("No solution found.");
    }
}
