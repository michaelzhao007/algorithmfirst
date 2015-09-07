package com.princeton.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	int open[][];
	WeightedQuickUnionUF union;
	int size = 0;

	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException(
					"N cannot be smaller or equal to 0!");
		}
		size = N;
		// System.out.println(N * N + N + 2);
		union = new WeightedQuickUnionUF(N * N + 2);
		open = new int[N][N];
	}

	public void open(int i, int j) {
		if (i < 1 || j < 1 || i > size || j > size) {
			throw new IndexOutOfBoundsException("Index out of bound!");
		}
		i = i - 1;
		j = j - 1;
		open[i][j] = 1;
		if (i == 0) {
			union.union(0, i * size + j + 1);
		}
		if (i == (size - 1)) {
			union.union(i * size + j + 1, size * size + 1);
		}
		if ((i - 1) >= 0 && open[i - 1][j] == 1) {
			union.union(i * size + j + 1, (i - 1) * size + j + 1);
		}
		if ((i + 1) < size && open[i + 1][j] == 1) {
			union.union(i * size + j + 1, (i + 1) * size + j + 1);
		}
		if ((j - 1) >= 0 && open[i][j - 1] == 1) {
			union.union(i * size + j + 1, i * size + (j - 1) + 1);
		}
		if ((j + 1) < size && open[i][j + 1] == 1) {
			union.union(i * size + j + 1, i * size + (j + 1) + 1);
		}
	}

	public boolean isOpen(int i, int j) {
		if (i < 1 || j < 1 || i > size || j > size) {
			throw new IndexOutOfBoundsException("Index out of bound!");
		}
		i = i - 1;
		j = j - 1;
		return open[i][j] == 1;
	}

	public boolean isFull(int i, int j) {
		if (i < 1 || j < 1 || i > size || j > size) {
			throw new IndexOutOfBoundsException("Index out of bound!");
		}
		i = i - 1;
		j = j - 1;
		return union.connected(i * size + j + 1, 0);
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
