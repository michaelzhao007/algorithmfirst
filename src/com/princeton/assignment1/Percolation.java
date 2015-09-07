import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 
 * @author dongyangzhao
 *
 */
public class Percolation {
    private int[][] open;
    private WeightedQuickUnionUF union;
    private int size = 0;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException(
                    "N cannot be smaller or equal to 0!");
        }
        size = N;
        union = new WeightedQuickUnionUF(N * N + 2);
        open = new int[N][N];
    }

    public void open(int i, int j) {
        if (i < 1 || j < 1 || i > size || j > size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        int ii = i - 1;
        int jj = j - 1;
        open[ii][jj] = 1;
        if (ii == 0) {
            union.union(0, ii * size + jj + 1);
        }
        if (ii == (size - 1)) {
            union.union(ii * size + jj + 1, size * size + 1);
        }
        if ((ii - 1) >= 0 && open[ii - 1][jj] == 1) {
            union.union(ii * size + jj + 1, (ii - 1) * size + jj + 1);
        }
        if ((ii + 1) < size && open[ii + 1][jj] == 1) {
            union.union(ii * size + jj + 1, (ii + 1) * size + jj + 1);
        }
        if ((jj - 1) >= 0 && open[ii][jj - 1] == 1) {
            union.union(ii * size + jj + 1, ii * size + (jj - 1) + 1);
        }
        if ((jj + 1) < size && open[ii][jj + 1] == 1) {
            union.union(ii * size + jj + 1, ii * size + (jj + 1) + 1);
        }
    }

    public boolean isOpen(int i, int j) {
        if (i < 1 || j < 1 || i > size || j > size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        int ii = i - 1;
        int jj = j - 1;
        return open[ii][jj] == 1;
    }

    public boolean isFull(int i, int j) {
        if (i < 1 || j < 1 || i > size || j > size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        int ii = i - 1;
        int jj = j - 1;
        return union.connected(ii * size + jj + 1, 0);
    }

    public boolean percolates() {
        return union.connected(size * size + 1, 0);
    }

    public static void main(String[] args) {
        Percolation per = new Percolation(3);
        per.open(1, 1);

        per.open(2, 1);
        System.out.println(per.isFull(2, 1));

        per.open(2, 2);
        System.out.println(per.isFull(2, 2));
        per.open(3, 2);
        System.out.println(per.percolates());
    }
}
