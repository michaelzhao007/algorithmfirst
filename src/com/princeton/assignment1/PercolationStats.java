package com.princeton.assignment1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 
 * @author dongyangzhao
 *
 */
public class PercolationStats {
    private double[] frac;
    private int times;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        frac = new double[T];
        times = T;
        for (int i = 0; i < T; i++) {
            Percolation per = new Percolation(N);
            double j = 0;
            while (!per.percolates()) {
                int i1 = StdRandom.uniform(1, N + 1);
                int i2 = StdRandom.uniform(1, N + 1);
                if (!per.isOpen(i1, i2)) {
                    per.open(i1, i2);
                    j++;
                }
            }
            frac[i] = (double) j / ((double) N * (double) N);
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(frac);
    }

    public double stddev() {
        return StdStats.stddev(frac);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats per = new PercolationStats(N, T);
        System.out.println("mean    =" + per.mean());
        System.out.println("stddev      =" + per.stddev());
        System.out.println("95% confidence interval      ="
                + per.confidenceLo() + ", " + per.confidenceHi());
    }
}
