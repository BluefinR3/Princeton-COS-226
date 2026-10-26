import java.util.Random;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

// Percolation Statistics.
public class PercolationStats {

	// Attributes..
	private double[] thresholds;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0) {
			throw new IllegalArgumentException("n is wrong");
		} else if (trials <= 0) {
			throw new IllegalArgumentException("trials is wrong");
		} else {
			Random random = new Random();
			this.thresholds = new double[trials];
			for (int i = 0; i < trials; i++) {
				Percolation percolation = new Percolation(n);
				int sites = 0;
				while (!percolation.percolates()) {
					int x = random.nextInt(n)  + 1;
					int y = random.nextInt(n) + 1;
					if (!percolation.isOpen(x, y)) {
						percolation.open(x, y);
						sites = sites + 1;
					}
				}
				this.thresholds[i] = sites * 1.0 / (Math.pow(n, 2));
			}
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(thresholds);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(thresholds);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLow() {
		return mean() - (1.96 * stddev() / Math.sqrt(thresholds.length));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHigh() {
		return mean() + (1.96 * stddev() / Math.sqrt(thresholds.length));
	}

	// test client
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		Stopwatch stopwatch = new Stopwatch();
		PercolationStats percStats = new PercolationStats(n, trials);
		StdOut.printf("%-18s = %f%n", "mean()", percStats.mean());
		StdOut.printf("%-18s = %f%n", "stdev()", percStats.stddev());
		StdOut.printf("%-18s = %f%n", "confidenceLow()", percStats.confidenceLow());
		StdOut.printf("%-18s = %f%n", "confidenceHigh()", percStats.confidenceHigh());
		StdOut.printf("%-18s = %f%n", "confidenceHigh()", percStats.confidenceHigh());
		StdOut.printf("%-18s = %f%n", "elapsed time", stopwatch.elapsedTime());
	}
}